Botiga - Plataforma de Comercio Electrónico

Esta práctica cuenta con las siguientes características, tecnologías y funcionalidades: 

Características: 
1. Buscar y explorar productos por nombre y categoría.
2.  Crear nuevos productos y categorías.
3.  Eliminar productos existentes.

Tecnologías Utilizadas: 
1. Java 17
2. Spring Boot
3. Thymeleaf
3. Hibernate ORM
4. MariaDB
5. HTML
6. CSS

Funcionalidades: 

Antes que nada que he de decir que el IDE que usamos para trabajar en esta aplicación, fue IntellIJ, ya que en opinión propia, es el mejor IDE para trabajar con JAVA.

Para comenzar con está práctica comenzaremos creando los modelos de la base de datos, para esto necesitamos una base de datos ya creada, y crear los modelos desde la carpeta modelos.

Para ver los modelos creados, se necesitará acceder a la carpeta modelos y ver cada uno de los modelos creados. Finalmente al ejecutar la aplicación, se nos creará las tablas en nuestra base de datos.

![alt text](<Imagen de WhatsApp 2024-05-31 a las 23.27.51_4e4b7c22.jpg>)

Una vez creados los modelos, podemos comenzar creando los repositorios, servicios y implementación de estos mismos. 

Repositorios de los modelos:

CategoryRepository:

![alt text](image-7.png)

SubCategoryRepository:

![alt text](image-8.png)

ProductRepository:

![alt text](image-9.png)

Lo siguiente que se hace es crear los servicios de estos repositorios:

CategoryService:

![alt text](image-10.png)

SubCategoryService:

![alt text](image-11.png)

ProductService: 

![alt text](image-12.png)

Continuaremos implementando estos repositorios y servicios.

CategoryServiceImpl:

![alt text](image-13.png)

ProductServiceImpl:

![alt text](image-14.png)

SubCategoryServiceImpl

![alt text](image-15.png)

Al tener todos los métodos implementados, proseguiremos con llamarlos en el WebController.

Definimos el controlador y asignamos variables a ProductService y SubCategoryService.

![alt text](image-16.png)

Este método nos devuelve un listado de productos

![alt text](image-17.png)

![alt text](<Imagen de WhatsApp 2024-05-31 a las 23.45.26_e885714f.jpg>)

Con este podemos ver un filtrado por nombre de la aplicación 

![alt text](image-18.png)

![alt text](<Imagen de WhatsApp 2024-05-31 a las 23.46.52_36933815.jpg>)

Con el siguiente método se creara un nuevo producto y nos redirigirá a otro html con la correcto insertado del nuevo producto. 

![alt text](image-19.png)

![alt text](<Imagen de WhatsApp 2024-05-31 a las 23.49.07_c0611005.jpg>)

![alt text](<Imagen de WhatsApp 2024-05-31 a las 23.49.25_affb1769.jpg>)

Y finalmente tenemos la eliminación de un producto, que si bien no está implementado, esta listo para que si. 

![alt text](image-20.png)

Podemos comprobar todo desde la terminal de la base de datos.

![alt text](<Imagen de WhatsApp 2024-05-31 a las 23.51.18_9dab066d.jpg>)