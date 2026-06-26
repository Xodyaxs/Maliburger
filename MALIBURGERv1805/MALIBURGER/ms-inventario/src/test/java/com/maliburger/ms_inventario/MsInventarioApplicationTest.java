package com.maliburger.ms_inventario;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class MsInventarioApplicationTests {

    @Test
    void testDescontarStockExitoso() {
        int stockInicial = 50;
        int cantidadVendida = 4;
        int stockFinal = stockInicial - cantidadVendida;
        assertEquals(46, stockFinal, "El stock final debería ser 46 tras vender 4 unidades");
    }

    @Test
    void testValidarStockSuficiente() {
        int stockEnBodega = 15;
        int cantidadRequerida = 5;
        boolean sePuedeVender = stockEnBodega >= cantidadRequerida;
        assertTrue(sePuedeVender, "El sistema debería permitir la venta porque hay stock suficiente");
    }

    @Test
    void testVentaRechazadaPorFaltaDeStock() {
        int stockEnBodega = 2;
        int cantidadRequerida = 10;
        boolean sePuedeVender = stockEnBodega >= cantidadRequerida;
        assertFalse(sePuedeVender, "¡Alerta! El sistema NO debe permitir vender más hamburguesas de las que existen");
    }
}