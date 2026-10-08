@echo off
REM Inicia la API de ODAPAS Reporte en http://localhost:8080
call "%~dp0mvnw.cmd" spring-boot:run
