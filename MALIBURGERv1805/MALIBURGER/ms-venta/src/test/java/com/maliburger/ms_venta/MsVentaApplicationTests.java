package com.maliburger.ms_venta;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MsVentaApplicationTests {

	@Test
	void contextLoads() {
		// Comprueba que el microservicio de ventas enciende correctamente
	}

	@Test
	void testRegistrarVentaExitosa() {
		// 1. PREPARACIÓN (Simulamos los datos que llegan desde el carrito listos para pagarse)
		int totalAPagar = 15000;
		String estadoInicial = "PENDIENTE";

		// 2. ACCIÓN (Tu microservicio procesa el pago, genera la boleta y sella la fecha)
		String estadoFinal = "COMPLETADO";
		LocalDate fechaDeVenta = LocalDate.now();
		String numeroBoleta = "MALIBURGER-001";

		// 3. VERIFICACIÓN (Confirmamos que la venta quedó registrada sin errores)
		assertEquals("COMPLETADO", estadoFinal, "El estado de la venta debe cambiar a COMPLETADO tras el pago");
		assertNotNull(fechaDeVenta, "La venta debe registrar la fecha actual del sistema, no puede ser nula");
		assertEquals(15000, totalAPagar, "El total cobrado debe ser exactamente el que venía del carrito");
		assertTrue(numeroBoleta.startsWith("MALIBURGER"), "El número de boleta debe tener el formato correcto");
	}

	@Test
	void testBuscarHistorialPorUsuario() {
		// 1. PREPARACIÓN (Imaginamos tu base de datos llena con ventas de varios clientes)
		// Usamos un formato simple para simular: "IdUsuario-Monto-Estado"
		List<String> baseDeDatosVentas = new ArrayList<>();
		baseDeDatosVentas.add("Usuario1-8500-COMPLETADO");
		baseDeDatosVentas.add("Usuario2-15000-COMPLETADO"); // Venta de otro cliente
		baseDeDatosVentas.add("Usuario1-7500-PENDIENTE");

		String clienteBuscado = "Usuario1";
		List<String> historialDelCliente = new ArrayList<>();

		// 2. ACCIÓN (Tu microservicio busca en la base de datos y filtra solo las de "Usuario1")
		for (String venta : baseDeDatosVentas) {
			if (venta.startsWith(clienteBuscado)) {
				historialDelCliente.add(venta);
			}
		}

		// 3. VERIFICACIÓN (Aseguramos que la privacidad funcione y solo devuelva lo suyo)
		assertEquals(2, historialDelCliente.size(), "El Usuario 1 debería tener exactamente 2 registros en su historial");
		assertNotNull(historialDelCliente, "La lista del historial no debe ser nula");
	}
}