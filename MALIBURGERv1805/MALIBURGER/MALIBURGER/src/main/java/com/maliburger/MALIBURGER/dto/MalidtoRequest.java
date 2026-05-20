package com.maliburger.MALIBURGER.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//herramientas para validar datos.

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MalidtoRequest {

    //Reglas para los precios
    @NotNull(message="El valor no puede estar vacío. ")
    @Min(value=9990, message="El valor no puede ser menor que $9.990. ")
    @Max(value=16990, message="El valor no puede superar los $16.990. ")
    private Integer precio;

    //Reglas para la descripción.
    @Size(max=100)
    @NotBlank(message="La descripción no puede estar vacía. ")
    private String descripcion;

    //Reglas para el nombre
    @Size(max=20)
    @NotBlank(message="El nombre no puede estar vacío. ")
    private String nombre;

    //Reglas para la proteina.
    @Size(max=20)
    @NotBlank(message="La proteina no puede estar vacío. ")
    private String proteina;
}
