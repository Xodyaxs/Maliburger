package com.maliburger.ms_inventario.Service;

import com.maliburger.ms_inventario.Model.Inventario;
import com.maliburger.ms_inventario.Repository.InventarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    //metodo para obtener stock de hamburguesas según su id
    public Inventario obtenerStock(Long idBurger) {
        return inventarioRepository.findByIdBurger(idBurger)
                .orElse(new Inventario(null, idBurger, 0));
    }

    //metodo para agregar hamburguesas en su stock y cantidad
    public Inventario agregarStock(Long idBurger, Integer cantidad) {
        Inventario stockActual = obtenerStock(idBurger);

        stockActual.setCantidadDisponible(stockActual.getCantidadDisponible() + cantidad);

        return inventarioRepository.save(stockActual);
    }

    //metodo para descontar hamburguesas post venta
    public Inventario descontarStock(Long idBurger, Integer cantidad) {
        Inventario stockActual = obtenerStock(idBurger);

        if (stockActual.getCantidadDisponible() < cantidad) {
            throw new RuntimeException("¡Stock insuficiente! Solo quedan: " + stockActual.getCantidadDisponible() + " unidades.");
        }

        stockActual.setCantidadDisponible(stockActual.getCantidadDisponible() - cantidad);

        return inventarioRepository.save(stockActual);
    }
}