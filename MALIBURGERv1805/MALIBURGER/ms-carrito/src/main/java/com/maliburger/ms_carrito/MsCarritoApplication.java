package com.maliburger.ms_carrito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {
		org.springdoc.core.configuration.SpringDocHateoasConfiguration.class
})
@EnableDiscoveryClient
public class MsCarritoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCarritoApplication.class, args);
	}

	@Bean
	public org.springframework.web.filter.ForwardedHeaderFilter forwardedHeaderFilter() {
		return new org.springframework.web.filter.ForwardedHeaderFilter();
	}
}
