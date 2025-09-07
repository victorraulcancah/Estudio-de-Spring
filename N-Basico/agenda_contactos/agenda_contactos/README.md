# Agenda de Contactos

API REST para una agenda de contactos, desarrollada con Spring Boot.

## API Endpoints

La URL base para todos los endpoints es `/api`.

### Contactos

- `POST /contactos`: Crea un nuevo contacto.
- `GET /contactos`: Obtiene todos los contactos.
- `GET /contactos/{id}`: Obtiene un contacto por su ID.
- `PUT /contactos/{id}`: Actualiza un contacto existente.
- `DELETE /contactos/{id}`: Elimina un contacto.

### Grupos

- `POST /grupos`: Crea un nuevo grupo.
- `GET /grupos`: Obtiene todos los grupos.
- `GET /grupos/nombre/{nombre}`: Busca un grupo por su nombre.
- `PUT /grupos/{idGrupo}`: Actualiza un grupo existente.
- `DELETE /grupos/{idGrupo}`: Elimina un grupo.

### Contacto-Grupo

- `POST /contacto-grupo`: Agrega un contacto a un grupo.
- `GET /contacto-grupo/contacto/{idContacto}`: Obtiene los grupos de un contacto.
- `GET /contacto-grupo/grupo/{idGrupo}`: Obtiene los contactos de un grupo.
- `DELETE /contacto-grupo`: Elimina un contacto de un grupo.

### Correos

- `POST /correos/{idContacto}`: Agrega un correo a un contacto.
- `GET /correos/contacto/{idContacto}`: Obtiene los correos de un contacto.
- `PUT /correos/{idCorreo}`: Actualiza un correo existente.
- `DELETE /correos/{idCorreo}`: Elimina un correo.

### Teléfonos

- `POST /telefonos/{idContacto}`: Agrega un teléfono a un contacto.
- `GET /telefonos/contacto/{idContacto}`: Obtiene los teléfonos de un contacto.
- `PUT /telefonos/{idTelefono}`: Actualiza un teléfono existente.
- `DELETE /telefonos/{idTelefono}`: Elimina un teléfono.

## Cómo ejecutar el proyecto

1. Clonar el repositorio: `git clone https://github.com/tu-usuario/agenda-contactos.git`
2. Navegar al directorio del proyecto: `cd agenda-contactos`
3. Ejecutar la aplicación: `./mvnw spring-boot:run`

La aplicación estará disponible en `http://localhost:8080`.

## Tecnologías utilizadas

- Java 21
- Spring Boot 3.3.5
- Maven
- MySQL
- Springdoc OpenAPI (para la documentación de la API)

## Análisis y Diseño del Sistema

El sistema está diseñado como una aplicación web de tres capas:

1.  **Capa de Presentación (Frontend):** Una aplicación de una sola página (SPA) que consume la API REST.
2.  **Capa de Negocio (Backend):** La API REST desarrollada con Spring Boot, que contiene la lógica de negocio.
3.  **Capa de Datos:** Una base de datos MySQL para persistir los datos.

## Lógica de Negocio

La lógica de negocio se encuentra en la capa de servicios del backend. Las operaciones principales son:

- **Gestión de Contactos:** Crear, leer, actualizar y eliminar (CRUD) contactos.
- **Gestión de Grupos:** Operaciones CRUD para grupos.
- **Asociación Contacto-Grupo:** Asociar y desasociar contactos de grupos.
- **Gestión de Información de Contacto:** Operaciones CRUD para correos y teléfonos asociados a un contacto.

## Diseño de la Interfaz de Usuario (UI)

Actualmente, el proyecto solo incluye el backend (API REST). La interfaz de usuario no está desarrollada.

Se prevé una interfaz de usuario sencilla e intuitiva que permita a los usuarios realizar las siguientes acciones:

- Ver una lista de todos los contactos.
- Agregar, editar y eliminar contactos.
- Crear y administrar grupos.
- Asignar contactos a grupos.
- Buscar contactos.