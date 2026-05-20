package com.maliburger.MALIBURGER.Controller;

//Importa las herraamientas que necesitamos
import com.maliburger.MALIBURGER.Model.Malibur;
import com.maliburger.MALIBURGER.Service.maliService;
import com.maliburger.MALIBURGER.dto.MaliDtoResponse;
import com.maliburger.MALIBURGER.dto.MalidtoRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maliburger")
@RequiredArgsConstructor
public class maliController {
    //Acá se procesan los datos.
    private final maliService maliburService;

    //Para ver todas las hamburguesas
    @GetMapping
    public List<MaliDtoResponse> obtenerTodas() {
        return maliburService.getAllMaliburs();
    }

    //Para ver la hamburguesa según su id.
    @GetMapping("/{id}")
    public ResponseEntity<MaliDtoResponse> obtenerPorId(@PathVariable Long id) {
        return maliburService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Para ver la hamburguesa según su tipo de proteína.
    @GetMapping("/proteina/{proteina}")
    public List<MaliDtoResponse> porProteina(@PathVariable String proteina) {
        return maliburService.buscarPorProteina(proteina);
    }

    //Para agregar una nueva hamburguesa.
    @PostMapping
    public ResponseEntity<MaliDtoResponse> crearMali(@Valid @RequestBody MalidtoRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(maliburService.guardarMali(dto));
    }

    //Modificar una hamburguesa ya existente.
    @PutMapping("/{id}")
    public ResponseEntity<MaliDtoResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody MalidtoRequest dto) {
        return maliburService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Para borrar una hamburguesa del menú con su id.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        maliburService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}