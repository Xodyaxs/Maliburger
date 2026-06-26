package com.maliburger.ms_inventario.Controller;

import com.maliburger.ms_inventario.Model.DTOrequest;
import com.maliburger.ms_inventario.Model.Inventario;
import com.maliburger.ms_inventario.Service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Imports de HATEOAS limpios
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService inventarioService;

    @GetMapping("/{idBurger}")
    public ResponseEntity<EntityModel<Inventario>> obtenerStock(@PathVariable Long idBurger) {
        Inventario inventario = inventarioService.obtenerStock(idBurger);

        EntityModel<Inventario> modelo = EntityModel.of(inventario);
        modelo.add(linkTo(methodOn(InventarioController.class).obtenerStock(idBurger)).withSelfRel());

        // Usamos null en los parámetros para que solo tome la referencia del método
        modelo.add(linkTo(methodOn(InventarioController.class).agregarStock(null)).withRel("agregar-stock"));
        modelo.add(linkTo(methodOn(InventarioController.class).descontarStock(null)).withRel("descontar-stock"));

        return ResponseEntity.ok(modelo);
    }

    @PostMapping("/agregar")
    public ResponseEntity<EntityModel<Inventario>> agregarStock(@RequestBody DTOrequest request) {
        Inventario inventario = inventarioService.agregarStock(request.getIdBurger(), request.getCantidad());

        EntityModel<Inventario> modelo = EntityModel.of(inventario);
        modelo.add(linkTo(methodOn(InventarioController.class).obtenerStock(request.getIdBurger())).withRel("ver-stock"));

        return ResponseEntity.ok(modelo);
    }

    @PostMapping("/descontar")
    public ResponseEntity<EntityModel<Inventario>> descontarStock(@RequestBody DTOrequest request) {
        try {
            Inventario inventario = inventarioService.descontarStock(request.getIdBurger(), request.getCantidad());

            EntityModel<Inventario> modelo = EntityModel.of(inventario);
            modelo.add(linkTo(methodOn(InventarioController.class).obtenerStock(request.getIdBurger())).withRel("ver-stock"));

            return ResponseEntity.ok(modelo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}