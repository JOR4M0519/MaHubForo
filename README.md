
## Descripción del Proyecto
MaHubForo es una plataforma interactiva de preguntas y respuestas. 
El proyecto consiste en desarrollar una API REST que permite gestionar temas y preguntas mediante operaciones CRUD.
Cuenta con un sistema de autenticación y autorización para proteger el acceso a la información, además de utilizar una base de datos para asegurar la 
persistencia de los datos, siguiendo las mejores prácticas del diseño REST.

## Tecnologías
- **Marco de desarrollo** Spring Boot 3
- **Lenguaje utilizado** Java 17
- **Motor de datos** MySQL 8.0
- **Autenticación** JWT 
- **Administrador de dependencias** Maven 4.0.0
- **Dependencias** Spring Web, Spring Data JPA, Spring Security, MySQL Driver, Validation, Lombok, Flyway Migration, Auth0, SpringDocs

## Instalación

1. Java JDK 21 o superior
2. MySQL 8.0: Descarga y configurar tu base de datos.
3. Clonar repositorio:
````
https://github.com/JOR4M0519/MaHubForo

````
Navega hasta la carpeta del proyecto.
````
cd Foro-Hub

````
4. Configura los datos en tu application.properties.
````
DATASOURCE_URL=jdbc:mysql://localhost:tu_base_de_datos
DATASOURCE_USERNAME=tu_nombre_de_usuario
DATASOURCE_PASSWORD=tu_contraseña
api.security.secret=tu_clave_secreta
````

5. Documentación Swagger:

   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui/index.html)


## Funcionalidades

- Ingreso y permisos: Los usuarios se registran, acceden al sistema y reciben un token JWT para ingresar a las áreas restringidas.

- Administración de temas y respuestas: Los usuarios tienen la capacidad de añadir, modificar, eliminar temas y responder preguntas.

- Acceso a información: Los usuarios pueden ver temas y respuestas a través de su identificador único o utilizando las rutas /topicos y /respuestas.

- Condiciones de validación: Se asegura que los campos no estén vacíos, que no existan duplicados y que cada usuario no pueda crear más de 10 temas.