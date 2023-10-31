# inditex

## Descripción

Aplicación para seleccionar la tarifa correcta para un producto de una determinada marca de Inditex en una fecha determinada usando Spring Boot, Spring Web y una base de datos H2.

## Estructura de la base de datos

La base de datos consta de dos tablas, **BRAND** y **PRICE**.

### BRAND

La tabla **BRAND** consta de dos campos, **ID** y **NAME**, y representa una marca de Inditex. El campo **NAME** es *unique*.

![Tabla BRAND](http://bracers.dev/wp-content/uploads/2023/10/Screenshot-from-2023-10-31-09-00-15.png)

### PRICE

La tabla **PRICE** representa un precio dentro de una tarifa y fechas determinadas para un producto de una marca determinada, y consta de los campos **ID** (*primary key*), **BRAND_ID** (marca, *foreing key* a la tabla **BRAND**), **START_DATE** (fecha de inicio de la oferta), **END_DATE** (fecha final de la oferta), **PRICE_LIST** (identificador de la tarifa), **PRODUCT_ID** (identificador del producto), **PRIORITY** (prioridad de la oferta), **PRICE** (precio de la oferta) y **CURR** (moneda en la que se especifica el precio de la oferta). Hay un *unique constrain* que incluye los campos **BRAND_ID**, **START_DATE**, **END_DATE**, **PRICE_LIST**, **PRODUCT_ID** y **PRIORITY**.

![Tabla PRICE](http://bracers.dev/wp-content/uploads/2023/10/Screenshot-from-2023-10-31-08-43-18.png)

## Puerto predeterminado

El puerto predeterminado es el **9970**.

## Carga de datos

Se ha implementado mediante una implementación de un **CommandLineRunner** que carga los datos al iniciarse el programa. La implementación se encuentra en la clase **LoadData**.

## Endpoints

Se puede ver la documentación de la API con Swagger en **http://localhost:9970/swagger-ui/index.html**.

### /api/v1/brand

#### Parámetros

- **brand**: identificador de la marca

#### Respuesta

- **id**: identificador de la marca
- **name**: nombre de la marca

El endpoint devuelve 200 si se ha encontrado la marca o 404 si no existe en base de datos.

### /api/v1/price

#### Parámetros

- **date**: fecha en la que se solicita el precio de un producto, especificada en formato ISO (ejemplo: **2020-06-14T10:00:00** para el día 14 de Junio de 2020 a las 10:00)
- **product**: identificador del producto
- **brand**: identificador de la marca

#### Respuesta

- **productId**: identificador del producto
- **brandId**: identificador de la marca
- **priceList**: identificador de la tarifa
- **startDate**: fecha de inicio de la tarifa especificada en formato ISO
- **endDate**: fecha final de la tarifa especificada en formato ISO
- **price**: precio de la tarifa

El endpoint devuelve 200 si se ha encontrado el precio o 404 si no existe en base de datos.

## Tests unitarios

La aplicación contiene test unitarios para los repositorios (**PriceRepositoryTest**), servicios (**BrandServiceImplTest** y **PriceServiceImplTest**) y controladores (**BrandControllerTest** y **PriceControllerTest**).

## Tests al endpoint con Postman

Para los tests, se ha utilizado Postman. Hay una colección con un total de 9 tests; 6 para el endpoint **api/v1/price** y 3 para el endpoint **api/v1/brand**:

- Test0 (**api/v1/price?date=2020-06-13T00:00:00&product=35455&brand=1**): 13 de Junio de 2020 a las 00:00, producto 35455 y marca 1. Se comprueba que no hay resultados y que se devuelve un código 404.
- Test1 (**api/v1/price?date=2020-06-14T10:00:00&product=35455&brand=1**): 14 de Junio de 2020 a las 10:00, producto 35455 y marca 1. Se comprueba que se devuelve un código 200 y que los resultados son los correctos.
- Test2 (**api/v1/price?date=2020-06-14T16:00:00&product=35455&brand=1**): 14 de Junio de 2020 a las 16:00, producto 35455 y marca 1. Se comprueba que se devuelve un código 200 y que los resultados son los correctos.
- Test3 (**api/v1/price?date=2020-06-14T21:00:00&product=35455&brand=1**): 14 de Junio de 2020 a las 21:00, producto 35455 y marca 1. Se comprueba que se devuelve un código 200 y que los resultados son los correctos.
- Test4 (**api/v1/price?date=2020-06-15T10:00:00&product=35455&brand=1**): 15 de Junio de 2020 a las 10:00, producto 35455 y marca 1. Se comprueba que se devuelve un código 200 y que los resultados son los correctos.
- Test5 (**api/v1/price?date=2020-06-16T21:00:00&product=35455&brand=1**): 16 de Junio de 2020 a las 21:00, producto 35455 y marca 1. Se comprueba que se devuelve un código 200 y que los resultados son los correctos.
- Test6 (**api/v1/brand?brand=0**): se comprueba que la marca no existe y que se devuelve un código 404.
- Test7 (**api/v1/brand?brand=1**): se comprueba que la marca existe, que se devuelve un código 200 y que la marca es la correcta (*ZARA*).
- Test8 (**api/v1/brand?brand=1**): se comprueba que la marca existe, que se devuelve un código 200 y que la marca es la correcta (*BERSHKA*).
