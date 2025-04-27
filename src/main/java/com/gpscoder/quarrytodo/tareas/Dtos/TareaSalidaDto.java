package com.gpscoder.quarrytodo.tareas.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TareaSalidaDto {

    private Long id;
    private String titulo;
    private String descripcion;
    private boolean finalizada;
}
