package mx.odapas.reporte.controlador;

import mx.odapas.reporte.modelo.Zona;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador 1: Zonas de atencion.
 * Todas las rutas de esta clase empiezan con /api/zonas
 *
 * Datos de prueba ficticios: no representan limites reales de ODAPAS.
 */
@RestController
@RequestMapping("/api/zonas")
public class ControladorZonas {

    private final List<Zona> zonas = List.of(
            new Zona("ZN", "Zona Norte", "Colonias del norte del municipio", true,
                    List.of("Las Flores", "San Miguel"), 2),
            new Zona("ZC", "Zona Centro", "Primer cuadro y colonias aledañas", true,
                    List.of("Centro", "La Paz"), 3),
            new Zona("ZS", "Zona Sur", "Colonias del sur del municipio", true,
                    List.of("Santa María"), 0)
    );

    /**
     * GET http://localhost:8080/api/zonas
     * Devuelve todas las zonas de atencion con las colonias que cubren.
     */
    @GetMapping
    public List<Zona> listarZonas() {
        return zonas;
    }

    /**
     * GET http://localhost:8080/api/zonas/ZN
     * Devuelve una zona por su codigo. Si no existe responde 404 (no encontrado).
     */
    @GetMapping("/{codigo}")
    public ResponseEntity<Zona> obtenerZona(@PathVariable String codigo) {
        return zonas.stream()
                .filter(zona -> zona.codigo().equalsIgnoreCase(codigo))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
