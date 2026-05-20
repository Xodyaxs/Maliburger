package com.maliburger.ms_carrito.Model;

import lombok.Data;

@Data
public class DTOrequest {
    //Dato que ingresa para calcular el valor final de precio(de la idburger)*cantidad
    private Long idBurger;
    private Integer cantidad;
}