# Spring Boot Security & JWT - MongoDB Atlas Edition 🚀

Este proyecto es una implementación robusta de seguridad para aplicaciones web modernas, diseñada para actuar como un backend seguro y escalable, integrando autenticación de doble token y persistencia en la nube.

## 🛠️ Tecnologías Utilizadas
*   **Java 17** con **Spring Boot 3.5.12**
*   **Spring Security 6**: Configuración basada en `SecurityFilterChain` (estilo funcional/lambda).
*   **JWT (JSON Web Token)**: Implementación de Access Tokens y Refresh Tokens.
*   **MongoDB Atlas**: Persistencia de datos en la nube (NoSQL).
*   **Lombok**: Para reducir el código repetitivo.
*   **JJWT (Java JWT)**: Para la generación y validación de tokens.

## 🔐 Características de Seguridad
- **Autenticación Stateless**: Las sesiones no se guardan en el servidor, todo se valida mediante tokens Bearer.
- **Refresh Token Pattern**: Permite obtener un nuevo Access Token sin que el usuario tenga que re-loguearse.
- **RBAC (Role-Based Access Control)**: Manejo de roles `USER` y `ADMIN` para proteger endpoints específicos.
- **Manejo de Errores Custom**: Respuestas JSON estructuradas para errores 401 (No autorizado) y 403 (Prohibido).
- **Seguridad en variables de entorno**: Configuración protegida mediante `${MONGO_URI}` y `${JWT_SECRET}`.

## 🚀 Instalación y Uso Local

1.  **Clonar el repositorio**.
2.  **Configurar variables de entorno**:
    Define las siguientes variables en tu sistema antes de ejecutar la aplicación:
    *   `MONGO_URI`: Tu cadena de conexión de MongoDB Atlas.
    *   `JWT_SECRET`: Una clave hexadecimal de 64 caracteres.
3.  **Ejecutar la aplicación**:
    ```bash
    ./mvnw spring-boot:run
    ```

## 🛤️ Endpoints Principales

### Autenticación (Públicos)
*   `POST /api/v1/auth/register`: Registra un usuario y devuelve tokens.
*   `POST /api/v1/auth/authenticate`: Login tradicional.
*   `POST /api/v1/auth/refresh-token`: Usa el refresh token para obtener un nuevo access token.

### Rutas Protegidas (Ejemplos)
*   `GET /api/v1/user/profile`: Accesible por usuarios con rol `USER` o `ADMIN`.
*   `GET /api/v1/admin/dashboard`: Accesible **únicamente** por usuarios con rol `ADMIN`.

## 📄 Licencia
Este proyecto es de uso libre para fines educativos y de desarrollo profesional.
