package com.maliburger.ms_inventario.Model;

import lombok.Data;

@Data
public class DTOrequest {
    private Long idBurger;
    private Integer cantidad;
}

//Agrega o resta cosas al inventario,