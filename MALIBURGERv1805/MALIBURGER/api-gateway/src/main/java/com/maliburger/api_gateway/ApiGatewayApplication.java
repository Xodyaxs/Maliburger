package com.maliburger.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	// --- EL CÓDIGO INQUEBRANTABLE DE RUTAS ---
	@Bean
	public RouteLocator configuracionRutas(RouteLocatorBuilder builder) {
		return builder.routes()

				// --- 1. RUTA DEL CATÁLOGO ---
				.route("catalogo-route", r -> r
						.path("/api/maliburger", "/api/maliburger/**")
						.uri("lb://MALIBURGER"))

				// --- 2. RUTA DEL CARRITO ---
				.route("carrito-route", r -> r
						.path("/api/carrito", "/api/carrito/**")
						.uri("lb://MS-CARRITO"))

				// --- 3. RUTA DE VENTAS ---
				.route("venta-route", r -> r
						.path("/api/venta", "/api/venta/**")
						.uri("lb://MS-VENTA"))

				// --- 4. RUTA DEL INVENTARIO ---
				.route("inventario-route", r -> r
						.path("/api/inventario", "/api/inventario/**")
						.uri("lb://MS-INVENTARIO"))

				// --- 5. RUTA DE SEGURIDAD (LOGIN) ---
				.route("seguridad-route", r -> r
						.path("/api/auth", "/api/auth/**")
						.uri("lb://MS-SEGURIDAD"))

				// ¡El build() siempre va al final para cerrar el paquete!
				.build();
	}
}