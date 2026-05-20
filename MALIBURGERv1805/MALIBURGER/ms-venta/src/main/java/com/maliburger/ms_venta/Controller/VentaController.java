package com.maliburger.ms_venta.Controller;

import com.maliburger.ms_venta.Model.Venta;
import com.maliburger.ms_venta.Model.VentaRequest;
import com.maliburger.ms_venta.Service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/venta")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    //registra una venta nueva
    @PostMapping("/registrar")
    public ResponseEntity<Venta> registrarVenta(@RequestBody VentaRequest request) {
        Venta nuevaVenta = ventaService.registrarVenta(request.getIdUsuario());
        return ResponseEntity.ok(nuevaVenta);
    }

    //ve el historial según el id del usuario
    @GetMapping("/historial/{idUsuario}")
    public ResponseEntity<List<Venta>> obtenerHistorial(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(ventaService.obtenerHistorial(idUsuario));
    }
}