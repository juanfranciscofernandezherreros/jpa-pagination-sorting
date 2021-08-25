# spring-boot-pagination-sorting

Buscador avanzado con múltiples filtros y con posibilidad de ordenar todos los campos.

1 ) Se puede obtener un listado con todos los elementos un simple "/"

``curl -X GET "http://localhost:8092/products/"``

2) Los parámetros para filtrar son los siguientes :


   ``/products?name=1``

  `` /products?surname=1``

  `` /products?quantity=5``

  `` /products?priceFrom=29027&priceTo=29029``
   
   Se pueden unificar todos los parámetros , también ordenar y ordenar de forma ascendente y descendente.

3) Para ordenar sería de la siguiente forma :

  ``/products?name=alejandro&sort=name,asc,surname,desc``

Como se puede ver es un buscador bastante avanzado.

Lo interestante de este buscador es que podemos ver distintos filtros como int , BigDecimal y String. (Podemos mirarlo en la entity Product)