package com.maliburger.ms_venta.Service;

import com.maliburger.ms_venta.Model.*;
import com.maliburger.ms_venta.Repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;
    private final RestTemplate restTemplate;

    public Venta registrarVenta(Long idUsuario) {
        // 1. Obtener el carrito completo del usuario (Puerto 8083)
        String urlCarrito = "http://localhost:8083/api/carrito/" + idUsuario;
        DTOcarrito carrito = restTemplate.getForObject(urlCarrito, DTOcarrito.class);

        if (carrito == null || carrito.getItems() == null || carrito.getItems().isEmpty()) {
            throw new RuntimeException("El carrito está vacío o no existe. No se puede procesar la venta.");
        }

        // 2. ¡NUEVO! Recorrer los productos del carrito y descontarlos del Inventario (Puerto 8082)
        String urlInventario = "http://localhost:8082/api/inventario/descontar";

        for (ItemCarritoDTO item : carrito.getItems()) {
            // Preparamos el JSON que espera el inventario por cada artículo
            InventarioRequestDTO inventarioRequest = new InventarioRequestDTO();
            inventarioRequest.setIdBurger(item.getIdBurger());
            inventarioRequest.setCantidad(item.getCantidad());

            // Hacemos el envío por el Body de forma automática al ms-inventario
            restTemplate.postForObject(urlInventario, inventarioRequest, Object.class);
        }

        // 3. Registrar la boleta de venta con el total oficial
        Venta nuevaVenta = new Venta();
        nuevaVenta.setIdUsuario(idUsuario);
        nuevaVenta.setTotalPagado(carrito.getPrecioTotal());
        Venta ventaGuardada = ventaRepository.save(nuevaVenta);

        // 4. Vaciar el carrito automáticamente (Puerto 8083)
        String urlLimpiar = "http://localhost:8083/api/carrito/" + idUsuario + "/limpiar";
        restTemplate.delete(urlLimpiar);

        return ventaGuardada;
    }

    public List<Venta> obtenerHistorial(Long idUsuario) {
        return ventaRepository.findByIdUsuario(idUsuario);
    }
}