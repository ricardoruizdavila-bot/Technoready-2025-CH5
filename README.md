# Technoready-2025-CH5
# CH5Techno — Order API (Spring Boot 3, Java 17)

REST API to manage **orders** for an online store.  
Includes CRUD endpoints, validation, environment profiles, H2 for development, Postman collection, startup scripts, and JavaDoc.

> **Tech stack:** Java 17 · Spring Boot 3.5.x · Spring Web · Spring Data JPA · Validation · DevTools · H2 (dev) / PostgreSQL (prod)

---

## Table of Contents
- [Prerequisites](#prerequisites)
- [Quick Start](#quick-start)
- [Profiles & Config](#profiles--config)
- [API Endpoints](#api-endpoints)
- [JavaDoc](#javadoc)
- [Decisions Log](#decisions-log)
- [Project Structure](#project-structure)

---

## Prerequisites
- **Java 17+**  
  Verify: `java -version`
- (Optional) **Git** to clone the repository

---

## Quick Start

### Windows (CMD)
```bat
start-dev.bat
```
### Git Bash / macOS / Linux
```
cd “file folder path”

option with variables
export SPRING_PROFILES_ACTIVE=dev
./mvnw spring-boot:run


Option without variables
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

If you are denied execution, apply this
chmod +x mvnw
and then run again

```
### Profiles & Config
dev (default)
DB: H2 in-memory
H2 Console: http://localhost:8081/h2-console
JDBC: jdbc:h2:mem:orders
User: sa | Password: (blank)

### API Endpoints
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
