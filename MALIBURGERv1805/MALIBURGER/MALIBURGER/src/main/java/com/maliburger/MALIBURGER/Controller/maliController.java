package com.maliburger.MALIBURGER.Controller;

// Importa las herramientas que necesitamos
import com.maliburger.MALIBURGER.Model.Malibur;
import com.maliburger.MALIBURGER.Service.maliService;
import com.maliburger.MALIBURGER.dto.MaliDtoResponse;
import com.maliburger.MALIBURGER.dto.MalidtoRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.CollectionModel;
import java.util.stream.Collectors;

// Herramientas mágicas para HATEOAS
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

@RestController
@RequestMapping("/api/maliburger")
@RequiredArgsConstructor
public class maliController {

    // Acá se procesan los datos.
    private final maliService maliburService;

    // Para ver todas las hamburguesas
    // Para ver todas las hamburguesas (¡Nivel Experto con CollectionModel!)
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<MaliDtoResponse>>> obtenerTodas() {

        // 1. Buscamos la lista normal en tu base de datos
        List<MaliDtoResponse> listaNormal = maliburService.getAllMaliburs();

        // 2. Transformamos cada hamburguesa de la lista en una cajita EntityModel con su propio enlace
        List<EntityModel<MaliDtoResponse>> listaConEnlaces = listaNormal.stream()
                .map(hamburguesa -> EntityModel.of(hamburguesa,
                        // Le ponemos a cada hamburguesa el enlace directo a su propio ID
                        linkTo(methodOn(maliController.class).obtenerPorId(hamburguesa.getIdBurger())).withSelfRel()
                ))
                .collect(Collectors.toList());

        // 3. Metemos todas las cajitas individuales en la caja mayor
        CollectionModel<EntityModel<MaliDtoResponse>> cajaFamiliar = CollectionModel.of(listaConEnlaces);

        // 4. Le ponemos un enlace a la caja mayor indicando el catálogo general
        cajaFamiliar.add(linkTo(methodOn(maliController.class).obtenerTodas()).withSelfRel());

        // 5. Devolvemos todo con un estado HTTP 200 (OK)
        return ResponseEntity.ok(cajaFamiliar);
    }

    // Para ver la hamburguesa según su id (Actualizado con HATEOAS).
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<MaliDtoResponse>> obtenerPorId(@PathVariable Long id) {
        return maliburService.obtenerPorId(id)
                .map(hamburguesaDto -> {
                    // 1. Envolvemos tu DTO en el contenedor de HATEOAS
                    EntityModel<MaliDtoResponse> modelo = EntityModel.of(hamburguesaDto);

                    // 2. Creamos los enlaces automáticos
                    // Enlace a la propia hamburguesa
                    Link linkSelf = linkTo(methodOn(maliController.class).obtenerPorId(id)).withSelfRel();
                    // Enlace para volver a ver todas las hamburguesas
                    Link linkAll = linkTo(methodOn(maliController.class).obtenerTodas()).withRel("ver-catalogo");

                    // 3. Le pegamos los enlaces a la caja
                    modelo.add(linkSelf);
                    modelo.add(linkAll);

                    // 4. Devolvemos la caja lista
                    return ResponseEntity.ok(modelo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Para ver la hamburguesa según su tipo de proteína.
    @GetMapping("/proteina/{proteina}")
    public List<MaliDtoResponse> porProteina(@PathVariable String proteina) {
        return maliburService.buscarPorProteina(proteina);
    }

    // Para agregar una nueva hamburguesa.
    @PostMapping
    public ResponseEntity<MaliDtoResponse> crearMali(@Valid @RequestBody MalidtoRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(maliburService.guardarMali(dto));
    }

    // Modificar una hamburguesa ya existente.
    @PutMapping("/{id}")
    public ResponseEntity<MaliDtoResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody MalidtoRequest dto) {
        return maliburService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Para borrar una hamburguesa del menú con su id.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        maliburService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}