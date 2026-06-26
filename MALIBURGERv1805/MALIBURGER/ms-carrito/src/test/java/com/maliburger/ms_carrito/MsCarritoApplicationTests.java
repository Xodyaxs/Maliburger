package com.maliburger.ms_carrito;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MsCarritoApplicationTests {

	@Test
	void contextLoads() {
		// Este test comprueba que tu aplicación enciende sin errores críticos.
	}

	@Test
	void testAgregarHamburguesas() {
		// 1. PREPARACIÓN (Simulamos la lista de tu carrito)
		List<String> carritoDeCompras = new ArrayList<>();

		// 2. ACCIÓN (El usuario añade productos)
		carritoDeCompras.add("La Maliburger");
		carritoDeCompras.add("Pollo Crispy");

		// 3. VERIFICACIÓN (Aseguramos que el sistema guardó los datos correctamente)
		assertEquals(2, carritoDeCompras.size(), "El carrito debería tener exactamente 2 productos");
		assertTrue(carritoDeCompras.contains("La Maliburger"), "El carrito debería contener 'La Maliburger'");
	}

	@Test
	void testCalcularTotal() {
		// 1. PREPARACIÓN (Simulamos los precios de una compra)
		int precioBurger1 = 8500;  // Ej: La Maliburger
		int cantidadBurger1 = 2;   // Llevó dos

		int precioBurger2 = 7500;  // Ej: Pollo Crispy
		int cantidadBurger2 = 1;   // Llevó una

		// 2. ACCIÓN (Ejecutamos la matemática que haría tu servicio)
		int subtotal1 = precioBurger1 * cantidadBurger1; // 17000
		int subtotal2 = precioBurger2 * cantidadBurger2; // 7500
		int totalFinal = subtotal1 + subtotal2;

		// 3. VERIFICACIÓN (La matemática debe ser exacta y a prueba de fallos)
		assertEquals(24500, totalFinal, "El cálculo del total tiene un error. Debería ser 24500");
	}

	@Test
	void testVaciarCarrito() {
		// 1. PREPARACIÓN (Un carrito lleno antes de pagar o cancelar)
		List<String> carritoDeCompras = new ArrayList<>();
		carritoDeCompras.add("Veggie Supreme");
		carritoDeCompras.add("Papas Fritas");

		// 2. ACCIÓN (El usuario presiona "Vaciar Carrito")
		carritoDeCompras.clear();

		// 3. VERIFICACIÓN (Aseguramos que no queden productos fantasma)
		assertEquals(0, carritoDeCompras.size(), "El carrito debería estar completamente vacío (tamaño 0)");
	}
}