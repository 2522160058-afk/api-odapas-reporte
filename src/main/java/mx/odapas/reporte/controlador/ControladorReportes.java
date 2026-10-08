package mx.odapas.reporte.controlador;

import mx.odapas.reporte.modelo.Reporte;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador 2: Reportes ciudadanos de fugas, falta de agua y baja presion.
 * Todas las rutas de esta clase empiezan con /api/reportes
 *
 * Datos de prueba ficticios.
 */
@RestController
@RequestMapping("/api/reportes")
public class ControladorReportes {

    private final List<Reporte> reportes = List.of(
            new Reporte("R-000123", "fuga", "Fuga frente al domicilio, el agua corre por la banqueta",
                    "Las Flores", "Av. Jacarandas 45", "ZN", 19.4521, -99.1310,
                    "asignado", "alta", "2026-10-07T09:15:00-06:00"),
            new Reporte("R-000124", "baja_presion", "Muy poca presión por las mañanas",
                    "Centro", "Calle Hidalgo 12", "ZC", 19.4327, -99.1332,
                    "en_revision", "media", "2026-10-07T10:02:00-06:00"),
            new Reporte("R-000125", "falta_agua", "Sin agua desde ayer en toda la cuadra",
                    "Santa María", "Calle Pino S/N", "ZS", null, null,
                    "recibido", null, "2026-10-07T11:40:00-06:00")
    );

    /**
     * GET http://localhost:8080/api/reportes
     * GET http://localhost:8080/api/reportes?tipo=fuga   (filtro opcional por tipo)
     * GET http://localhost:8080/api/reportes?zona=ZN     (filtro opcional por zona)
     */
    @GetMapping
    public List<Reporte> listarReportes(@RequestParam(required = false) String tipo,
                                        @RequestParam(required = false) String zona) {
        return reportes.stream()
                .filter(reporte -> tipo == null || tipo.isBlank() || reporte.tipo().equalsIgnoreCase(tipo))
                .filter(reporte -> zona == null || zona.isBlank() || reporte.zona().equalsIgnoreCase(zona))
                .toList();
    }

    /**
     * GET http://localhost:8080/api/reportes/R-000123
     * Devuelve un reporte por su folio. Si no existe responde 404 (no encontrado).
     */
    @GetMapping("/{folio}")
    public ResponseEntity<Reporte> obtenerReporte(@PathVariable String folio) {
        return reportes.stream()
                .filter(reporte -> reporte.folio().equalsIgnoreCase(folio))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
