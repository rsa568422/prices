# <span style="color: green;">Prices API</span>

Este proyecto contiene una API REST para consultar el precio de un producto para una fecha determinada.

Siguiendo una arquitectura hexagonal, se ha dividido el proyecto en tres módulos: dominio, infraestructura y aplicación.

___

## <span style="color: green;">Docker Compose</span>

Comando para compilar el proyecto:
```bash
docker-compose build
```

Comando para levantar el proyecto:
```bash
docker-compose up -d
```

Comando para parar el proyecto:
```bash
docker compose down
```
___

## <span style="color: green;">Swagger</span>

[URL de Swagger](http://localhost:8080/swagger-ui/index.html)

En dicha url se podrá comprobar el funcionamiento de los endpoints:

| Tipo de método | End point |
|----------------|-----------|
| GET            | /prices   |

___

## <span style="color: green;">Llamadas a la API</span>

### Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)

Para Windows (PowerShell):
```shell
wget "http://localhost:8080/prices?date=2020-06-14T10:00:00.000Z&productId=35455&brandId=1" -Headers @{"accept"="application/json"}
```

Para sistemas Unix (bash):
```bash
curl --location 'http://localhost:8080/prices?date=2020-06-14T10%3A00%3A00.000Z&productId=35455&brandId=1'
```

### Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
Para Windows (PowerShell):
```shell
wget "http://localhost:8080/prices?date=2020-06-14T16:00:00.000Z&productId=35455&brandId=1" -Headers @{"accept"="application/json"}
```

Para sistemas Unix (bash):
```bash
curl --location 'http://localhost:8080/prices?date=2020-06-14T16%3A00%3A00.000Z&productId=35455&brandId=1'
```

### Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
Para Windows (PowerShell):
```shell
wget "http://localhost:8080/prices?date=2020-06-14T21:00:00.000Z&productId=35455&brandId=1" -Headers @{"accept"="application/json"}
```

Para sistemas Unix (bash):
```bash
curl --location 'http://localhost:8080/prices?date=2020-06-14T21%3A00%3A00.000Z&productId=35455&brandId=1'
```

### Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
Para Windows (PowerShell):
```shell
wget "http://localhost:8080/prices?date=2020-06-15T10:00:00.000Z&productId=35455&brandId=1" -Headers @{"accept"="application/json"}
```

Para sistemas Unix (bash):
```bash
curl --location 'http://localhost:8080/prices?date=2020-06-15T10%3A00%3A00.000Z&productId=35455&brandId=1'
```

### Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
Para Windows (PowerShell):
```shell
wget "http://localhost:8080/prices?date=2020-06-16T21:00:00.000Z&productId=35455&brandId=1" -Headers @{"accept"="application/json"}
```

Para sistemas Unix (bash):
```bash
curl --location 'http://localhost:8080/prices?date=2020-06-16T21%3A00%3A00.000Z&productId=35455&brandId=1'
```

### Test 6: petición para producto 35455 y la brand 1 (ZARA) pero en fecha sin precios
Para Windows (PowerShell):
```shell
wget "http://localhost:8080/prices?date=2024-06-16T21:00:00.000Z&productId=35455&brandId=1" -Headers @{"accept"="application/json"}
```

Para sistemas Unix (bash):
```bash
curl --location 'http://localhost:8080/prices?date=2024-06-16T21%3A00%3A00.000Z&productId=35455&brandId=1'
```

### Test 7: petición para producto 35455 y la brand 1 (ZARA) pero en fecha incorrecta
Para Windows (PowerShell):
```shell
wget "http://localhost:8080/prices?date=2024-06-00Z&productId=35455&brandId=1" -Headers @{"accept"="application/json"}
```

Para sistemas Unix (bash):
```bash
curl --location 'http://localhost:8080/prices?date=2024-06-00Z&productId=35455&brandId=1'
```

___

## <span style="color: green;">Informe Cobertura y mutaciones</span>

Se agrega el informe generado mediante la herramienta PIT para obtener la cobertura del proyecto y mutaciones.
Las mutaciones son cambios generados en nuestro código por dicha herramienta que deben ser detectados por nuestros tests.

[Informe de PIT](./report/index.html)

| Paquete                                         | Número de clases | Cobertura de líneas | Cobertura de mutaciones | Calidad de los tests |
|-------------------------------------------------|------------------|---------------------|-------------------------|----------------------|
| com.inditex.prices.application.controller       | 2                | 100% (14/14)        | 100% (9/9)              | 100% (9/9)           |
| com.inditex.prices.application.controller.api   | 2                | 10% (2/20)          | 0% (0/8)                | 100% (0/0)           |
| com.inditex.prices.application.controller.model | 2                | 58% (65/112)        | 38% (21/56)             | 66% (21/32)          |
| com.inditex.prices.application.mapper           | 1                | 100% (13/13)        | 100% (10/10)            | 100% (10/10)         |
| com.inditex.prices.domain.model                 | 1                | 100% (11/11)        | 56% (43/77)             | 70% (43/61)          |
| com.inditex.prices.domain.service               | 1                | 100% (4/4)          | 100% (1/1)              | 100% (1/1)           |
| com.inditex.prices.infrastructure.adapter       | 1                | 100% (5/5)          | 100% (1/1)              | 100% (1/1)           |
| com.inditex.prices.infrastructure.entity        | 3                | 100% (21/21)        | 75% (9/12)              | 75% (9/12)           |
| com.inditex.prices.infrastructure.mapper        | 1                | 94% (29/31)         | 88% (14/16)             | 100% (14/14)         |
