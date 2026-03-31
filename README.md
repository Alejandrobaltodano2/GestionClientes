# API REST para Gestión de Clientes


Este proyecto es una API REST desarrollada en Java utilizando Spring Boot para la gestión de clientes.
La API permite realizar las siguientes operaciones Crear y Listar.

## Características
- Crear un nuevo cliente: Permite agregar un nuevo cliente a la base de datos.
- Obtener todos los clientes: Permite recuperar una lista de todos los clientes almacenados en la base de datos.

## Tecnologías Utilizadas
- Java 21
- Spring Web 
- Spring Data JPA
- H2 Database (base de datos en memoria)
- Maven (gestión de dependencias)
- Lombok 
- JUnit (para pruebas unitarias)
- Mockito (para pruebas unitarias)
- Swagger (para documentación de la API)
- MapStruct (para mapeo de objetos)
- Spring Validation (para validación de datos)

## Configuración
1. Clona el repositorio:
   git clone https://github.com/Alejandrobaltodano2/GestionClientes.git
2. Navega al directorio del proyecto:
    cd GestionClientes
3. Ejecuta la aplicación:
    mvn spring-boot:run
4. La API estará disponible en `http://localhost:8082/clientes`.
## Endpoints
- `POST /api/clientes`: Crea un nuevo cliente.
- `GET /api/clientes`: Obtiene una lista de todos los clientes.


