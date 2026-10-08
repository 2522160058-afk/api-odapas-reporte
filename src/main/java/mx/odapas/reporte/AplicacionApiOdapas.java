package mx.odapas.reporte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la API REST de ODAPAS Reporte.
 * Al ejecutarla, Spring Boot levanta un servidor en http://localhost:8080
 * y registra automaticamente los controladores del paquete "controlador".
 */
@SpringBootApplication
public class AplicacionApiOdapas {

    public static void main(String[] argumentos) {
        SpringApplication.run(AplicacionApiOdapas.class, argumentos);
    }
}
