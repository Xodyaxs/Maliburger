package com.maliburger.ms_inventario.Controller;

import com.maliburger.ms_inventario.Model.DTOrequest;
import com.maliburger.ms_inventario.Model.Inventario;
import com.maliburger.ms_inventario.Service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService inventarioService;

    //consulta el stock de las hamburguesas
    @GetMapping("/{idBurger}")
    public ResponseEntity<Inventario> obtenerStock(@PathVariable Long idBurger) {
        return ResponseEntity.ok(inventarioService.obtenerStock(idBurger));
    }

    //se agregan hamburguesas al stock
    @PostMapping("/agregar")
    public ResponseEntity<Inventario> agregarStock(@RequestBody DTOrequest request) {
        return ResponseEntity.ok(inventarioService.agregarStock(request.getIdBurger(), request.getCantidad()));
    }

    //Se descuentan hamburguesas luego de una venta
    @PostMapping("/descontar")
    public ResponseEntity<Inventario> descontarStock(@RequestBody DTOrequest request) {
        try {
            return ResponseEntity.ok(inventarioService.descontarStock(request.getIdBurger(), request.getCantidad()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}