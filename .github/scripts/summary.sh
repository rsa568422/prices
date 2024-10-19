#!/bin/bash

set -e  # Detiene la ejecución si hay algún error

TOTAL_LINES=0
COVERED_LINES=0

for MODULE in prices-domain prices-infrastructure prices-application; do
    echo "Buscando informes para el módulo: $MODULE"
    REPORT_DIR="./$MODULE/target/pit-reports"
    echo "Buscando en el directorio: $REPORT_DIR"

    if [ ! -d "$REPORT_DIR" ]; then
        echo "El directorio $REPORT_DIR no existe"
        continue
    fi

    # Buscar todos los archivos index.html
    REPORT_FILES=$(find "$REPORT_DIR" -name "index.html")

    for REPORT_PATH in $REPORT_FILES; do
        echo "Procesando el informe: $REPORT_PATH"
        COVERAGE_LEGEND=$(grep -oP '<div class="coverage_legend">(\d+)/(\d+)</div>' "$REPORT_PATH" | head -n 1)
        echo "Leyenda de cobertura encontrada: $COVERAGE_LEGEND"

        COVERED_LINES_FILE=$(echo "$COVERAGE_LEGEND" | grep -oP '(\d+)/' | sed 's/\///')
        TOTAL_LINES_FILE=$(echo "$COVERAGE_LEGEND" | grep -oP '/(\d+)' | sed 's/\///')

        echo "Archivo $REPORT_PATH: $COVERED_LINES_FILE / $TOTAL_LINES_FILE"

        if [ -n "$COVERED_LINES_FILE" ] && [ -n "$TOTAL_LINES_FILE" ]; then
            TOTAL_LINES=$((TOTAL_LINES + TOTAL_LINES_FILE))
            COVERED_LINES=$((COVERED_LINES + COVERED_LINES_FILE))
        fi
    done
done

echo "Total de líneas: $TOTAL_LINES"
echo "Líneas cubiertas: $COVERED_LINES"

if [ $TOTAL_LINES -gt 0 ]; then
    COVERAGE=$(echo "scale=2; $COVERED_LINES * 100 / $TOTAL_LINES" | bc)
    echo "Cobertura de líneas total: $COVERAGE%"
    if (( $(echo "$COVERAGE < 80" | bc -l) )); then
        echo "$COVERAGE% < 80%"
        exit 1
    fi
else
    echo "No se encontraron líneas para calcular la cobertura"
    exit 1
fi