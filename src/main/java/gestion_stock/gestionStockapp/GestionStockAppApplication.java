package gestion_stock.gestionStockapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GestionStockAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionStockAppApplication.class, args);
	}

}
