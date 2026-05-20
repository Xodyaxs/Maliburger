package com.maliburger.ms_carrito.Controller;

import com.maliburger.ms_carrito.Model.Carrito;
import com.maliburger.ms_carrito.Model.DTOrequest;
import com.maliburger.ms_carrito.Model.DTOrequest;
import com.maliburger.ms_carrito.Service.CarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;

    //con el id del usuario podemos ver su carrito de compras
    @GetMapping("/{idUsuario}")
    public ResponseEntity<Carrito> obtenerCarrito(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(carritoService.obtenerCarrito(idUsuario));
    }

    //toma el id de la hamburguesa y la cantidad y la agrega al carrito
    @PostMapping("/{idUsuario}/agregar")
    public ResponseEntity<Carrito> agregarItem(
            @PathVariable Long idUsuario,
            @RequestBody DTOrequest request) {

        Carrito carritoActualizado = carritoService.agregarItem(
                idUsuario,
                request.getIdBurger(),
                request.getCantidad()
        );

        return ResponseEntity.ok(carritoActualizado);
    }

    //Con esto vaciamos el carrito por completo.
    @DeleteMapping("/{idUsuario}/limpiar")
    public ResponseEntity<String> limpiarCarrito(@PathVariable Long idUsuario) {
        carritoService.limpiarCarrito(idUsuario);
        return ResponseEntity.ok("El carrito del usuario " + idUsuario + " ha sido vaciado con éxito.");
    }
}