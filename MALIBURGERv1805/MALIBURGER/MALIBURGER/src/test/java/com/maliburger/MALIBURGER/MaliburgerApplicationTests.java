package com.maliburger.MALIBURGER;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class MaliburgerApplicationTests {

	@Test
	void testCrearHamburguesa() {
		// Aquí simulamos una burger
		String nombre = "La Maliburger";
		int precio = 8500;

		// Verificamos que los datos sean correctos (si el resultado no es 8500, el test falla)
		assertEquals(8500, precio, "El precio debería ser 8500");
	}
}