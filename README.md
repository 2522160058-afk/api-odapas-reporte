# API ODAPAS Reporte — API REST con Spring Boot

API REST de **ODAPAS Reporte**, sistema para reportar **fugas, falta de agua y baja presión** y darles seguimiento por **zona de atención**.
Esta versión tiene **2 controladores** con rutas `GET` que devuelven **datos de prueba** en formato JSON (sin base de datos).

> Materia: Desarrollo Web Profesional · Actividad: Desarrollo de una API REST con Spring Boot
> Alumno: Santiago Osorio José Manuel
>
> Proyecto académico. Los datos de prueba son ficticios y no representan zonas, colonias ni procesos reales de ODAPAS.

---

## Herramientas

| Herramienta | Versión |
|---|---|
| Java (JDK) | 17 o superior |
| Spring Boot | 3.3.5 (`spring-boot-starter-web`) |
| Maven | 3.9.x (se descarga solo la primera vez) |
| Postman | cualquier versión reciente |

---

## Estructura del proyecto

```
api-odapas-reporte/
├── iniciar-api.cmd                         # Doble clic para iniciar la API
├── ejecutar-pruebas.cmd                    # Doble clic para correr las pruebas automáticas
├── pom.xml                                 # Dependencias del proyecto (Maven)
├── mvnw / mvnw.cmd                         # Descarga Maven si no está instalado
├── src/main/java/mx/odapas/reporte/
│   ├── AplicacionApiOdapas.java            # Clase principal (inicia el servidor en el puerto 8080)
│   ├── controlador/
│   │   ├── ControladorZonas.java           # Controlador 1 → /api/zonas
│   │   └── ControladorReportes.java        # Controlador 2 → /api/reportes
│   └── modelo/
│       ├── Zona.java                       # Datos de una zona de atención
│       └── Reporte.java                    # Datos de un reporte ciudadano
├── src/main/resources/application.properties   # Configuración: puerto y formato del JSON
├── src/test/java/mx/odapas/reporte/PruebasApiOdapas.java  # Pruebas automáticas
├── coleccion-postman/coleccion-api-odapas.json            # Colección para importar en Postman
└── evidencias/
    ├── navegador/                          # Capturas de las pruebas en el navegador
    ├── postman/                            # Capturas de las pruebas en Postman
    └── Pruebas-API-ODAPAS-Reporte.pdf      # PDF con todas las pruebas
```

> Los nombres `pom.xml`, `mvnw`, `src/main/java`, `src/main/resources`, `src/test/java`, `application.properties` y `README.md`
> son obligatorios para Maven, Spring Boot y GitHub, por eso no se tradujeron.

---

## Cómo ejecutar

1. Verificar que Java 17 o superior esté instalado:
   ```
   java -version
   ```
2. Clonar el repositorio y entrar a la carpeta:
   ```
   git clone https://github.com/<tu-usuario>/api-odapas-reporte.git
   cd api-odapas-reporte
   ```
3. Iniciar la API (cualquiera de estas opciones):
   - Doble clic en **`iniciar-api.cmd`**
   - En PowerShell: `.\mvnw.cmd spring-boot:run`
4. Cuando la consola muestre `Tomcat started on port 8080`, la API está lista en **http://localhost:8080**.

Pruebas automáticas: doble clic en **`ejecutar-pruebas.cmd`** o `.\mvnw.cmd test`.

---

## Rutas de la API (todas con el método `GET`)

### Controlador 1 — `ControladorZonas` (`/api/zonas`)

| Método | URL | Descripción |
|---|---|---|
| GET | `http://localhost:8080/api/zonas` | Lista las zonas de atención con sus colonias |
| GET | `http://localhost:8080/api/zonas/ZN` | Una zona por su código (404 si no existe) |

Respuesta de ejemplo:
```json
{
  "codigo" : "ZN",
  "nombre" : "Zona Norte",
  "descripcion" : "Colonias del norte del municipio",
  "activa" : true,
  "colonias" : [ "Las Flores", "San Miguel" ],
  "trabajadoresDisponibles" : 2
}
```

### Controlador 2 — `ControladorReportes` (`/api/reportes`)

| Método | URL | Descripción |
|---|---|---|
| GET | `http://localhost:8080/api/reportes` | Lista todos los reportes |
| GET | `http://localhost:8080/api/reportes?tipo=fuga` | Filtra por tipo (`fuga`, `falta_agua`, `baja_presion`) |
| GET | `http://localhost:8080/api/reportes?zona=ZC` | Filtra por zona |
| GET | `http://localhost:8080/api/reportes/R-000123` | Un reporte por su folio (404 si no existe) |

Respuesta de ejemplo:
```json
{
  "folio" : "R-000123",
  "tipo" : "fuga",
  "descripcion" : "Fuga frente al domicilio, el agua corre por la banqueta",
  "colonia" : "Las Flores",
  "calle" : "Av. Jacarandas 45",
  "zona" : "ZN",
  "latitud" : 19.4521,
  "longitud" : -99.131,
  "estado" : "asignado",
  "prioridad" : "alta",
  "fechaCreacion" : "2026-10-07T09:15:00-06:00"
}
```

Los reportes no muestran datos personales del ciudadano (correo, teléfono ni domicilio).

---

## Pruebas realizadas

### Navegador
Con la API iniciada se abrió cada URL en el navegador; el navegador hace una petición `GET` y muestra el JSON.
Capturas en [`evidencias/navegador`](evidencias/navegador).

### Postman
1. **Import** → `coleccion-postman/coleccion-api-odapas.json` (incluye la variable `urlBase = http://localhost:8080`).
2. **Send** en cada petición; la pestaña *Test Results* comprueba el estado y el contenido.
3. **Run collection** para ejecutar las 7 peticiones juntas.

Capturas en [`evidencias/postman`](evidencias/postman).

### PDF de las pruebas
Todas las evidencias están en [`evidencias/Pruebas-API-ODAPAS-Reporte.pdf`](evidencias/Pruebas-API-ODAPAS-Reporte.pdf).
