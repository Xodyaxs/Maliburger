package com.maliburger.ms_carrito.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items_carrito")
public class ItemCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    // conectar el ms carrito con el ms catálogo
    @Column(nullable = false)
    private Long idBurger;

    // cuántas de la misma burgir lleva el cliente
    @Column(nullable = false)
    private Integer cantidad;

    // precio de cu para calcular el total
    @Column(nullable = false)
    private Double precioUnitario;
}