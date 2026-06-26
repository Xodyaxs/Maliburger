package com.maliburger.ms_carrito.Controller;

import com.maliburger.ms_carrito.Model.Carrito;
import com.maliburger.ms_carrito.Model.DTOrequest;
import com.maliburger.ms_carrito.Service.CarritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Imports de HATEOAS
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/carrito")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;

    // Con el id del usuario podemos ver su carrito de compras (¡Con HATEOAS Dinámico!)
    @GetMapping("/{idUsuario}")
    public ResponseEntity<EntityModel<Carrito>> obtenerCarrito(@PathVariable Long idUsuario) {

        // 1. Obtenemos el carrito normal
        Carrito miCarrito = carritoService.obtenerCarrito(idUsuario);

        // 2. Lo metemos en la caja de HATEOAS
        EntityModel<Carrito> modelo = EntityModel.of(miCarrito);

        // 3. Enlace fijo: "Ver mi propio carrito"
        Link linkSelf = linkTo(methodOn(CarritoController.class).obtenerCarrito(idUsuario)).withSelfRel();
        modelo.add(linkSelf);

        // 4. LÓGICA DINÁMICA: Evaluamos si hay algo que cobrar
        // IMPORTANTE: Aquí asumo que tu clase Carrito tiene un método getTotal().
        // Si usas otro nombre (ej. getSubtotal() o getCantidad()), cámbialo aquí abajo.
        if (miCarrito.getPrecioTotal() > 0) {

            // Si hay productos, mostramos los enlaces de pago y limpieza
            Link linkPagar = Link.of("http://localhost:8083/api/ventas/pagar/" + idUsuario).withRel("pagar-carrito");
            Link linkVaciar = linkTo(methodOn(CarritoController.class).limpiarCarrito(idUsuario)).withRel("vaciar-carrito");

            modelo.add(linkPagar);
            modelo.add(linkVaciar);
        } else {
            // Si el carrito está en 0, lo mandamos a ver el menú del catálogo
            Link linkCatalogo = Link.of("http://localhost:8081/api/maliburger").withRel("ir-al-catalogo");
            modelo.add(linkCatalogo);
        }

        return ResponseEntity.ok(modelo);
    }

    // Toma el id de la hamburguesa y la cantidad y la agrega al carrito
    @PostMapping("/{idUsuario}/agregar")
    public ResponseEntity<EntityModel<Carrito>> agregarItem(
            @PathVariable Long idUsuario,
            @RequestBody DTOrequest request) {

        Carrito carritoActualizado = carritoService.agregarItem(
                idUsuario,
                request.getIdBurger(),
                request.getCantidad()
        );

        // Hacemos que al agregar un producto también nos devuelva el carrito con sus enlaces actualizados
        EntityModel<Carrito> modelo = EntityModel.of(carritoActualizado);
        modelo.add(linkTo(methodOn(CarritoController.class).obtenerCarrito(idUsuario)).withSelfRel());

        return ResponseEntity.ok(modelo);
    }

    // Con esto vaciamos el carrito por completo.
    @DeleteMapping("/{idUsuario}/limpiar")
    public ResponseEntity<String> limpiarCarrito(@PathVariable Long idUsuario) {
        carritoService.limpiarCarrito(idUsuario);
        return ResponseEntity.ok("El carrito del usuario " + idUsuario + " ha sido vaciado con éxito.");
    }
}