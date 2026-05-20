package com.maliburger.ms_venta.Model;

import lombok.Data;
import java.util.List;

@Data
public class DTOcarrito {
    private Double precioTotal;
    private List<ItemCarritoDTO> items;

}

//Genera los metodos necesarios para manejar estos datos
//muestra el monto final a pagar por el carrito
//lista detallada de cada hamburguesa