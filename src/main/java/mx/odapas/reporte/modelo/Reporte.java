package mx.odapas.reporte.modelo;

/**
 * Reporte ciudadano de un incidente de agua.
 *
 * tipo:      "fuga", "falta_agua" o "baja_presion".
 * zona:      la determina el sistema a partir de la colonia del incidente.
 * latitud y longitud: opcionales (pueden ser null si el ciudadano no compartio su GPS).
 *
 * No incluye datos personales del ciudadano (correo, telefono ni domicilio).
 */
public record Reporte(
        String folio,
        String tipo,
        String descripcion,
        String colonia,
        String calle,
        String zona,
        Double latitud,
        Double longitud,
        String estado,
        String prioridad,
        String fechaCreacion
) {
}
