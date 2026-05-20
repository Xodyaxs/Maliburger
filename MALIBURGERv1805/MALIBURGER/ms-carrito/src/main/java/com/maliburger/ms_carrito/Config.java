package com.maliburger.ms_carrito;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    //herramienta que permite que los microservicios se comuniquen entre ellos.
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}