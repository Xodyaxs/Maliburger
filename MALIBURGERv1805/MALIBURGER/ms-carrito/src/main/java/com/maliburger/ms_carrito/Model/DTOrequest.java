package com.maliburger.ms_carrito.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DTOrequest {
    //Dato que ingresa para calcular el valor final de precio(de la idburger)*cantidad
    private Long idBurger;
    private Integer cantidad;
}