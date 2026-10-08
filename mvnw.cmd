@echo off
REM ------------------------------------------------------------------
REM  Maven Wrapper simple para Windows.
REM  Si Maven no esta instalado, descarga Maven 3.9.9 una sola vez en
REM  %USERPROFILE%\.m2\wrapper y lo usa. Solo necesitas tener Java 17+.
REM  Uso:  mvnw.cmd spring-boot:run
REM ------------------------------------------------------------------
setlocal
set MVN_VERSION=3.9.9
set MVN_DIR=%USERPROFILE%\.m2\wrapper\apache-maven-%MVN_VERSION%
set MVN_URL=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/%MVN_VERSION%/apache-maven-%MVN_VERSION%-bin.zip

if not exist "%MVN_DIR%\bin\mvn.cmd" (
  echo Descargando Maven %MVN_VERSION% ^(solo la primera vez^)...
  powershell -NoProfile -ExecutionPolicy Bypass -Command ^
    "$ErrorActionPreference='Stop';" ^
    "$zip=Join-Path $env:TEMP 'maven-%MVN_VERSION%.zip';" ^
    "[Net.ServicePointManager]::SecurityProtocol=[Net.SecurityProtocolType]::Tls12;" ^
    "Invoke-WebRequest -Uri '%MVN_URL%' -OutFile $zip;" ^
    "New-Item -ItemType Directory -Force -Path '%USERPROFILE%\.m2\wrapper' | Out-Null;" ^
    "Expand-Archive -Path $zip -DestinationPath '%USERPROFILE%\.m2\wrapper' -Force;" ^
    "Remove-Item $zip"
  if errorlevel 1 (
    echo No se pudo descargar Maven. Revisa tu conexion a internet.
    exit /b 1
  )
)

call "%MVN_DIR%\bin\mvn.cmd" %*
endlocal
