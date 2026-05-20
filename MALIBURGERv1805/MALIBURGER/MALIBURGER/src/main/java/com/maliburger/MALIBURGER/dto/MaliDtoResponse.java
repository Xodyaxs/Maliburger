package com.maliburger.MALIBURGER.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Son los datos que solicitamos por postman.

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaliDtoResponse {
        private Long idBurger;
        private Integer precio;
        private String descripcion;
        private String nombre;
        private String proteina;
}
