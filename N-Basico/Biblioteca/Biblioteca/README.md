# API de Biblioteca

Esta es una API REST desarrollada con Spring Boot que proporciona operaciones CRUD para gestionar libros en una biblioteca.

## Características

- Operaciones CRUD asíncronas usando `CompletableFuture`
- Arquitectura en capas (Controller, Service, Repository)
- Endpoints RESTful

## Estructura del Modelo

### Libro
```json
{
    "id": Long,
    "titulo": String,
    "autor": String
}
```

## Endpoints

### Listar todos los libros
- **URL**: `/libros`
- **Método**: `GET`
- **Respuesta exitosa**: 
  - Código: 200
  - Contenido: Array de objetos Libro
```json
[
    {
        "id": 1,
        "titulo": "Don Quijote",
        "autor": "Miguel de Cervantes"
    },
    // ...
]
```

### Obtener un libro por ID
- **URL**: `/libros/{id}`
- **Método**: `GET`
- **Parámetros URL**: `id=[Long]`
- **Respuesta exitosa**:
  - Código: 200
  - Contenido: Objeto Libro
```json
{
    "id": 1,
    "titulo": "Don Quijote",
    "autor": "Miguel de Cervantes"
}
```

### Crear nuevo libro
- **URL**: `/libros`
- **Método**: `POST`
- **Cuerpo de la petición**:
```json
{
    "titulo": "Título del libro",
    "autor": "Nombre del autor"
}
```
- **Respuesta exitosa**:
  - Código: 201
  - Contenido: Objeto Libro creado

### Actualizar libro existente
- **URL**: `/libros/{id}`
- **Método**: `PUT`
- **Parámetros URL**: `id=[Long]`
- **Cuerpo de la petición**:
```json
{
    "titulo": "Nuevo título",
    "autor": "Nuevo autor"
}
```
- **Respuesta exitosa**:
  - Código: 200
  - Contenido: Objeto Libro actualizado

### Eliminar libro
- **URL**: `/libros/{id}`
- **Método**: `DELETE`
- **Parámetros URL**: `id=[Long]`
- **Respuesta exitosa**:
  - Código: 204
  - Sin contenido

## Características Técnicas

- La API utiliza procesamiento asíncrono para mejorar el rendimiento
- Todas las operaciones devuelven `CompletableFuture` para manejo asíncrono
- Implementación de patrones Repository y Service
- Uso de inyección de dependencias
- CORS habilitado para desarrollo local con Angular (http://localhost:4200) y http://127.0.0.1:5500/index.html

## Ejecución

Para ejecutar el proyecto:

1. Asegúrate de tener Java instalado
2. Clona el repositorio
3. Ejecuta `./mvnw spring-boot:run` en la raíz del proyecto
4. La API estará disponible en `http://localhost:8080`

## Notas
- Todas las respuestas son asíncronas
- Los IDs son generados automáticamente
- La API maneja errores comunes y devuelve códigos de estado HTTP apropiados
