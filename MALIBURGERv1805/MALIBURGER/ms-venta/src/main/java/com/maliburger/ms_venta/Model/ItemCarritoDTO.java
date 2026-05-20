package com.maliburger.ms_venta.Model;

import lombok.Data;

@Data
public class ItemCarritoDTO {
    private Long idBurger;
    private Integer cantidad;
}

//está el id de la hamburguesa q el cliente escogio y la cantidad agregada a la compra