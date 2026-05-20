package com.maliburger.ms_venta.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ventas")
public class Venta {

    //múmero único de la trnsacción
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    //quién realizó la compra
    @Column(nullable = false)
    private Long idUsuario;

    //cuánto dinero se pagó
    @Column(nullable = false)
    private Double totalPagado;

    // para guardar fecha y hora boleta
    @Column(nullable = false)
    private LocalDateTime fechaVenta = LocalDateTime.now();
}