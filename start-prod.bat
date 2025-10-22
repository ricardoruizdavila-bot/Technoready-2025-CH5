@echo off
set SPRING_PROFILES_ACTIVE=prod
set DB_URL=jdbc:postgresql://localhost:5432/orders
set DB_USER=postgres
set DB_PASS=techno2025
set PORT=8082
call mvnw.cmd spring-boot:run

