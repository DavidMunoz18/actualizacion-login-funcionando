package edu.proyectoApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal para iniciar la aplicación Spring Boot.
 * Esta clase arranca la aplicación mediante el método {@code main}.
 * <p>
 * La anotación {@link SpringBootApplication} combina tres anotaciones esenciales de Spring:
 * {@code @Configuration}, {@code @EnableAutoConfiguration}, y {@code @ComponentScan}.
 * Esto permite que la aplicación configure sus componentes automáticamente y escanee
 * los paquetes para encontrar los beans de Spring.
 * </p>
 */

@SpringBootApplication

public class DemoApplication {
	
	  /**
     * Método principal que inicia la aplicación Spring Boot.
     *
     * @param args argumentos de la línea de comandos
     */

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
