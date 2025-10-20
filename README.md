# Technoready-2025-CH5
# CH5Techno — Order API (Spring Boot 3, Java 17)

REST API to manage **orders** for an online store.  
Includes CRUD endpoints, validation, environment profiles, H2 for development, Postman collection, startup scripts, and JavaDoc.

> **Tech stack:** Java 17 · Spring Boot 3.5.x · Spring Web · Spring Data JPA · Validation · DevTools · H2 (dev) / PostgreSQL (prod)

---

## Table of Contents
- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Perfiles y Configuración](#perfiles-y-configuración)
- [API Endpoints](#api-endpoints)
- [JavaDoc](#javadoc)
- [Decisions Log](#decisions-log)
- [Project Structure](#project-structure)

## Prerequisites
- **Java 17+**  
  Verify: `java -version`
- **PostgreSQL** (para producción)
  - Base de datos: orders
  - Usuario: postgres
  - Contraseña: techno2025
- (Optional) **Git** to clone the repository

## Quick Start

### Usando los scripts de inicio
```bat
# Para desarrollo (H2 en memoria)
start-dev.bat

# Para producción (PostgreSQL)
start-prod.bat
```

## Perfiles y Configuración

### Perfiles Disponibles

1. **dev** (desarrollo)
   - Base de datos H2 en memoria
   - Puerto por defecto: 8082
   - Consola H2: http://localhost:8082/h2-console
   - DDL-auto: update (crea/actualiza tablas automáticamente)

2. **test** (pruebas)
   - Base de datos H2 en memoria
   - Puerto por defecto: 8081
   - DDL-auto: create-drop (recrea tablas en cada inicio)
   - Logging: DEBUG

3. **prod** (producción)
   - PostgreSQL
   - Puerto por defecto: 8081
   - DDL-auto: create (temporalmente para crear tablas)
   - Logging: INFO

### Variables de Entorno

#### Desarrollo (dev)
No requiere variables de entorno específicas.

#### Pruebas (test)
```bat
TEST_DB_URL=jdbc:h2:mem:testdb
TEST_DB_USER=sa
TEST_DB_PASS=
PORT=8081
```

#### Producción (prod)
```bat
DB_URL=jdbc:postgresql://localhost:5432/orders
DB_USER=postgres
DB_PASS=techno2025
PORT=8081
```

### Archivos de Configuración
- `application.yml` - Configuración base
- `application-dev.yml` - Perfil de desarrollo
- `application-test.yml` - Perfil de pruebas
- `application-prod.yml` - Perfil de producción

### Scripts de Inicio
1. `start-dev.bat` - Inicia en modo desarrollo
2. `start-prod.bat` - Inicia en modo producción

### Cómo cambiar entre perfiles

#### Usando variables de entorno
```bat
set SPRING_PROFILES_ACTIVE=dev|test|prod
```

#### Usando parámetros de Java
```bat
mvnw spring-boot:run -Dspring-boot.run.profiles=dev|test|prod
```

## Seguridad y Buenas Prácticas

1. **Credenciales**
   - No almacenar contraseñas en archivos de configuración
   - Usar variables de entorno o gestores de secretos en producción

2. **Base de Datos**
   - Desarrollo: H2 en memoria
   - Producción: PostgreSQL con credenciales seguras
   - Realizar respaldos regulares en producción

3. **Logs**
   - Desarrollo: Nivel DEBUG para más detalle
   - Producción: Nivel INFO para rendimiento óptimo

## Notas Importantes

1. La configuración actual de producción tiene `ddl-auto: create` temporalmente para crear las tablas. Cambiar a `validate` después de la primera ejecución.

2. Asegurarse de que PostgreSQL esté corriendo antes de iniciar en modo producción.

3. Los puertos configurados son:
   - Desarrollo: 8082
   - Pruebas/Producción: 8081

---

## API Endpoints
````
| Method | Path                                  | Description                        |
| :----: | ------------------------------------- | ---------------------------------- |
|  POST  | `/api/orders`                         | Create an order (**201 Created**)  |
|   GET  | `/api/orders`                         | List all orders                    |
|   GET  | `/api/orders/{id}`                    | Get order by id                    |
|   PUT  | `/api/orders/{id}`                    | Update customer name & total       |
|  PATCH | `/api/orders/{id}/status?status=PAID` | Change status (`CREATED/PAID/...`) |
| DELETE | `/api/orders/{id}`                    | Delete order                       |
````

### JavaDoc

Generate and open the HTML site:

Git Bash / macOS / Linux
````
./mvnw javadoc:javadoc
explorer.exe target/site/apidocs/index.html   # opens on Windows
````
### Decisions Log

See DECISIONS.md
 for key decisions and justifications (DB choices, DTO vs Entity, enum statuses, no Lombok in S1, port selection, etc.).

 ### Project structure
 ````
ch5techno/
├─ src/
│  ├─ main/
│  │  ├─ java/com/ch5/ch5techno/
│  │  │  ├─ Ch5TechnoApplication.java
│  │  │  └─ order/
│  │  │     ├─ Order.java
│  │  │     ├─ OrderRequest.java
│  │  │     ├─ OrderStatus.java
│  │  │     ├─ OrderRepository.java
│  │  │     ├─ OrderService.java
│  │  │     └─ OrderController.java
│  └─ └─ resources/
│        └─application.yml
├─ start-dev.sh | start-dev.bat | start-dev.ps1
├─ DECISIONS.md
├─ pom.xml
└─ README.md
````

# CH5Techno - Perfiles y Variables de Entorno

## Perfiles de entorno
Se usan perfiles Spring: dev, test, prod. El perfil activo se determina por la variable:
- SPRING_PROFILES_ACTIVE

Por defecto (si no se especifica) se usa `dev`.

## Archivos de configuración
- src/main/resources/application.yml        -> configuración base y fallback
- src/main/resources/application-dev.yml    -> configuración de desarrollo
- src/main/resources/application-test.yml   -> configuración de pruebas
- src/main/resources/application-prod.yml   -> configuración de producción (no incluir secretos)

## Variables de entorno (ejemplo)
Ver `.env.example` en la raíz. Las variables críticas:
- SPRING_PROFILES_ACTIVE (dev|test|prod)
- PORT
- PROD_DB_URL, PROD_DB_USER, PROD_DB_PASS (MUST set en producción)
- PEER_REVIEW_LEVEL (low|medium|high)

### Cómo cambiar de perfil
- Usando variable de entorno (recomendado):
  - Windows (PowerShell): $env:SPRING_PROFILES_ACTIVE="prod"
  - Linux/macOS: export SPRING_PROFILES_ACTIVE=prod
- O al iniciar la JVM:
  - java -jar app.jar --spring.profiles.active=prod
  - o: java -Dspring.profiles.active=prod -jar app.jar

## Seguridad y secretos
- No almacenar contraseñas ni claves en los ficheros del repositorio.
- En producción, configurar variables de entorno en el servidor/CI o usar un gestor de secretos (Vault, AWS Secrets Manager, Azure Key Vault).

## Revisión parcial (peer reviews automáticas)
Se incluye un script ligero: tools/peer_review_check.py  
- Escanea TODO/FIXME y comprueba reglas simples.
- Registra resultados en tools/logs/peer_review.log
- Ejecutar: python tools/peer_review_check.py
- Controlar nivel con PEER_REVIEW_LEVEL.

# CH5Techno — Order API (notas de despliegue)

## Despliegue en producción (resumen rápido)

Requisitos mínimos para que la app arranque con perfil `prod`:
1. Dependencia JDBC de PostgreSQL en pom.xml:
   - Añadir (si no está): 
     <dependency>
       <groupId>org.postgresql</groupId>
       <artifactId>postgresql</artifactId>
       <version>42.6.0</version>
     </dependency>
2. Crear la base de datos y el usuario en PostgreSQL (ejemplo):
   - psql -U postgres
   - CREATE DATABASE orders;
   - CREATE USER app WITH ENCRYPTED PASSWORD 'app123';
   - GRANT ALL PRIVILEGES ON DATABASE orders TO app;
3. Variables de entorno (usar los nombres que la configuración espera):
   - SPRING_PROFILES_ACTIVE=prod
   - DB_URL=jdbc:postgresql://<host>:5432/orders
   - DB_USER=<user>
   - DB_PASS=<password>
   - PORT=8081
4. Arrancar:
   - Windows PowerShell:
     $env:SPRING_PROFILES_ACTIVE="prod"
     $env:DB_URL="jdbc:postgresql://localhost:5432/orders"
     $env:DB_USER="app"
     $env:DB_PASS="app123"
     $env:PORT="8081"
     .\mvnw.cmd spring-boot:run

Notas:
- Si la app falla por Driver Class not found, añade la dependencia org.postgresql:postgresql en pom.xml y vuelve a construir.
- Asegúrate de que la DB existe y las credenciales son correctas; si usas Docker/containers espera a que el servicio DB esté listo antes de arrancar la app.
- No guardes credenciales reales en el repositorio; usa secrets manager o variables en el entorno/CI.
