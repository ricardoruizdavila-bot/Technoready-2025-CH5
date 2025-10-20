@echo off
set SPRING_PROFILES_ACTIVE=dev
set PORT=8081
call mvnw.cmd spring-boot:run

