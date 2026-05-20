package com.maliburger.ms_venta.Model;

import lombok.Data;

@Data
public class InventarioRequestDTO {
    private Long idBurger;
    private Integer cantidad;
}

//identifica qué hamburguesa se vendio y cuantas unidades,
// asi el inventario sabe cuántas restar