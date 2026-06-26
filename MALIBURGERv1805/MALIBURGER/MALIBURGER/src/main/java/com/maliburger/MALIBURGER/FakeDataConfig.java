package com.maliburger.MALIBURGER;

import com.github.javafaker.Faker;
import com.maliburger.MALIBURGER.Model.Malibur;
import com.maliburger.MALIBURGER.Repository.MaliRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakeDataConfig {

    @Bean
    public CommandLineRunner poblarBaseDeDatos(MaliRepository maliRepository) {
        return args -> {
            // Solo creamos datos si la tabla en la base de datos está completamente vacía
            if (maliRepository.count() == 0) {
                Faker faker = new Faker(new java.util.Locale("ES"));

                System.out.println("¡Iniciando la parrilla! Creando menú falso...");

                for (int i = 0; i < 10; i++) {
                    Malibur nuevaBurger = new Malibur();

                    nuevaBurger.setNombre(faker.food().dish());
                    nuevaBurger.setDescripcion(faker.food().ingredient());

                    // Faker genera un precio entero entre 5000 y 12000
                    nuevaBurger.setPrecio(faker.number().numberBetween(5000, 12000));

                    // Elegimos una proteína al azar para cumplir con el nullable=false
                    nuevaBurger.setProteina(faker.options().option("Carne de Res", "Pollo Crispy", "Soya", "Cola de Chihuahua", "Cerdo"));

                    maliRepository.save(nuevaBurger);
                }

                System.out.println("¡10 Hamburguesas creadas con éxito en la base de datos!");
            } else {
                System.out.println("La base de datos ya tiene hamburguesas. Saltando Data Fake.");
            }
        };
    }
}