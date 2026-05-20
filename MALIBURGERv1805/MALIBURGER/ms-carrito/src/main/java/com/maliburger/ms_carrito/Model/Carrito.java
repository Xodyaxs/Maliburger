package com.maliburger.ms_carrito.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carritos")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrito;

    // de qué cliente es este carrido con el idUsuario
    @Column(nullable = false, unique = true)
    private Long idUsuario;

    //uno a muchos, un carrito cn hartas burgirs dentro
    //cascadetype.all para que al borrar el carrito se borren todas las burgirs
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_carrito") // Crea la llave foránea en la tabla de ítems
    private List<ItemCarrito> items = new ArrayList<>();

    //precio total del carrito
    private Double precioTotal = 0.0;
}