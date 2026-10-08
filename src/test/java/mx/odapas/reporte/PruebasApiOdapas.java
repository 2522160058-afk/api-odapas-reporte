package mx.odapas.reporte;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Pruebas automaticas de los dos controladores.
 * Se ejecutan con: mvnw.cmd test
 */
@SpringBootTest
@AutoConfigureMockMvc
class PruebasApiOdapas {

    @Autowired
    private MockMvc simuladorPeticiones;

    @Test
    void listaTresZonas() throws Exception {
        simuladorPeticiones.perform(get("/api/zonas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }

    @Test
    void obtieneZonaPorCodigo() throws Exception {
        simuladorPeticiones.perform(get("/api/zonas/ZN"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Zona Norte"));
    }

    @Test
    void filtraReportesPorTipo() throws Exception {
        simuladorPeticiones.perform(get("/api/reportes").param("tipo", "fuga"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].folio").value("R-000123"));
    }

    @Test
    void obtieneReportePorFolio() throws Exception {
        simuladorPeticiones.perform(get("/api/reportes/R-000124"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.zona").value("ZC"));
    }

    @Test
    void reporteInexistenteResponde404() throws Exception {
        simuladorPeticiones.perform(get("/api/reportes/R-999999"))
                .andExpect(status().isNotFound());
    }
}
