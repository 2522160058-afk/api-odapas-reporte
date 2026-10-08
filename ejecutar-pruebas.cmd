@echo off
REM Ejecuta las pruebas automaticas de los controladores
call "%~dp0mvnw.cmd" test
