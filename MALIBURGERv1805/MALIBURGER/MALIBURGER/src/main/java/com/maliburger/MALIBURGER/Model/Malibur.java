package com.maliburger.MALIBURGER.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//Herramientas para conectar la base de datos

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="hamburguesas")
public class Malibur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBurger;

    @Column(nullable = false)
    private Integer precio;

    @Column(length=500, nullable = false)
    private String descripcion;

    @Column(nullable=false,unique=true,length=30)
    private String nombre;

    @Column(nullable=false,length=30)
    private String proteina;}


