package com.maliburger.ms_venta.Controller;

import com.maliburger.ms_venta.Model.Venta;
import com.maliburger.ms_venta.Model.VentaRequest;
import com.maliburger.ms_venta.Service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

// Imports de HATEOAS
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/venta")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    // Registra una venta nueva
    @PostMapping("/registrar")
    public ResponseEntity<EntityModel<Venta>> registrarVenta(@RequestBody VentaRequest request) {
        Venta nuevaVenta = ventaService.registrarVenta(request.getIdUsuario());

        EntityModel<Venta> modelo = EntityModel.of(nuevaVenta);

        // Enlaces de navegación post-venta
        modelo.add(linkTo(methodOn(VentaController.class).registrarVenta(request)).withSelfRel());
        modelo.add(linkTo(methodOn(VentaController.class).obtenerHistorial(request.getIdUsuario())).withRel("ver-historial"));
        modelo.add(linkTo(methodOn(VentaController.class).obtenerHistorial(request.getIdUsuario())).withRel("ir-al-catalogo")); // Redirección lógica

        return ResponseEntity.ok(modelo);
    }

    // Ve el historial según el id del usuario
    @GetMapping("/historial/{idUsuario}")
    public ResponseEntity<CollectionModel<EntityModel<Venta>>> obtenerHistorial(@PathVariable Long idUsuario) {
        List<Venta> listaVentas = ventaService.obtenerHistorial(idUsuario);

        // Convertimos cada venta en un EntityModel
        List<EntityModel<Venta>> listaConEnlaces = listaVentas.stream()
                .map(venta -> EntityModel.of(venta,
                        linkTo(methodOn(VentaController.class).obtenerHistorial(idUsuario)).withSelfRel()
                ))
                .collect(Collectors.toList());

        // Agrupamos en una colección
        CollectionModel<EntityModel<Venta>> modeloColeccion = CollectionModel.of(listaConEnlaces);
        modeloColeccion.add(linkTo(methodOn(VentaController.class).obtenerHistorial(idUsuario)).withSelfRel());

        return ResponseEntity.ok(modeloColeccion);
    }
}