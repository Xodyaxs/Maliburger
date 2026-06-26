package com.maliburger.ms_venta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {org.springdoc.core.configuration.SpringDocHateoasConfiguration.class})
public class MsVentaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsVentaApplication.class, args);
	}

	@Bean
	public org.springframework.web.filter.ForwardedHeaderFilter forwardedHeaderFilter() {
		return new org.springframework.web.filter.ForwardedHeaderFilter();
	}
}
