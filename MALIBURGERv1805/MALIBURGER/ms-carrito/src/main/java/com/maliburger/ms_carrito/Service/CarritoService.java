package com.maliburger.ms_carrito.Service;

import com.maliburger.ms_carrito.Model.DTOburger;
import com.maliburger.ms_carrito.Model.Carrito;
import com.maliburger.ms_carrito.Model.DTOburger;
import com.maliburger.ms_carrito.Model.ItemCarrito;
import com.maliburger.ms_carrito.Repository.CarritoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarritoService {

    private final CarritoRepository carritoRepository;
    private final RestTemplate restTemplate; // <--- Traemos nuestro Postman interno

    public Carrito obtenerCarrito(Long idUsuario) {
        // ... (Tu código de obtenerCarrito se queda exactamente igual) ...
        return carritoRepository.findByIdUsuario(idUsuario)
                .orElseGet(() -> {
                    Carrito nuevoCarrito = new Carrito();
                    nuevoCarrito.setIdUsuario(idUsuario);
                    nuevoCarrito.setPrecioTotal(0.0);
                    return carritoRepository.save(nuevoCarrito);
                });
    }

    public Carrito agregarItem(Long idUsuario, Long idBurger, Integer cantidad) {

        String urlCatalogo = "http://localhost:8080/api/maliburger/" + idBurger;

       DTOburger hamburguesaOficial = restTemplate.getForObject(urlCatalogo, DTOburger.class);

        if (hamburguesaOficial == null || hamburguesaOficial.getPrecio() == null) {
            throw new RuntimeException("Error: La hamburguesa no existe en el catálogo.");
        }

        Double precioReal = hamburguesaOficial.getPrecio();

        Carrito carrito = obtenerCarrito(idUsuario);

        Optional<ItemCarrito> itemExistente = carrito.getItems().stream()
                .filter(item -> item.getIdBurger().equals(idBurger))
                .findFirst();

        if (itemExistente.isPresent()) {
            ItemCarrito item = itemExistente.get();
            item.setCantidad(item.getCantidad() + cantidad);
            item.setPrecioUnitario(precioReal);
        } else {
            ItemCarrito nuevoItem = new ItemCarrito(null, idBurger, cantidad, precioReal);
            carrito.getItems().add(nuevoItem);
        }

        recalcularTotal(carrito);
        return carritoRepository.save(carrito);
    }

    public void limpiarCarrito(Long idUsuario) {
        Carrito carrito = obtenerCarrito(idUsuario);
        carrito.getItems().clear();
        carrito.setPrecioTotal(0.0);
        carritoRepository.save(carrito);
    }

    private void recalcularTotal(Carrito carrito) {
        double total = carrito.getItems().stream()
                .mapToDouble(item -> item.getPrecioUnitario() * item.getCantidad())
                .sum();
        carrito.setPrecioTotal(total);
    }
}