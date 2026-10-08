package mx.odapas.reporte.modelo;

import java.util.List;

/**
 * Zona de atencion: division operativa del territorio que atiende el organismo.
 * Cubre una o varias colonias y tiene trabajadores adscritos.
 *
 * Se usa un "record" de Java: una clase de solo datos que ya incluye
 * constructor y metodos para leer cada campo.
 */
public record Zona(
        String codigo,
        String nombre,
        String descripcion,
        boolean activa,
        List<String> colonias,
        int trabajadoresDisponibles
) {
}
