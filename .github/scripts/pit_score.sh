#!/bin/bash

set -e  # Detiene la ejecución si hay algún error

TOTAL_MUTATIONS=0
KILLED_MUTATIONS=0

for MODULE in prices-domain prices-infrastructure prices-application; do
    echo "Buscando informes para el módulo: $MODULE"
    REPORT_DIR="./$MODULE/target/pit-reports"
    PIT_REPORT=$(find "$REPORT_DIR" -name "index.html" | head -n 1)

    if [ -f "$PIT_REPORT" ]; then
        echo "Procesando informe: $PIT_REPORT"
        MUTATION_SCORE=$(sed -n '/<h3>Project Summary<\/h3>/,/<\/tbody>/p' "$PIT_REPORT" |
                         grep -oP '<td>\K\d+(?=% <div class="coverage_bar">)' |
                         sed -n '3p')

        MUTATIONS=$(sed -n '/<h3>Project Summary<\/h3>/,/<\/tbody>/p' "$PIT_REPORT" |
                    grep -oP '<div class="coverage_legend">\K\d+/\d+(?=</div>)' |
                    sed -n '3p')

        if [ -z "$MUTATIONS" ]; then
            echo "No se encontró información de mutaciones para $MODULE"
            continue
        fi

        KILLED=$(echo $MUTATIONS | cut -d'/' -f1)
        TOTAL=$(echo $MUTATIONS | cut -d'/' -f2)

        if [ -z "$MUTATION_SCORE" ]; then
            MUTATION_SCORE=$(echo "scale=2; $KILLED * 100 / $TOTAL" | bc)
        fi

        echo "$MODULE - Porcentaje de mutantes eliminados: $MUTATION_SCORE%"

        TOTAL_MUTATIONS=$((TOTAL_MUTATIONS + TOTAL))
        KILLED_MUTATIONS=$((KILLED_MUTATIONS + KILLED))
    else
        echo "No se encontró el informe de PIT para $MODULE"
    fi
done

if [ $TOTAL_MUTATIONS -gt 0 ]; then
    TOTAL_PERCENTAGE=$(echo "scale=2; $KILLED_MUTATIONS * 100 / $TOTAL_MUTATIONS" | bc)
    echo "Porcentaje total de mutantes eliminados: $TOTAL_PERCENTAGE%"

    if (( $(echo "$TOTAL_PERCENTAGE < 50" | bc -l) )); then
        echo "El porcentaje de mutantes eliminados es menor al 50%."
        exit 1
    fi
else
    echo "No se encontraron mutaciones para calcular el porcentaje total"
    exit 1
fi