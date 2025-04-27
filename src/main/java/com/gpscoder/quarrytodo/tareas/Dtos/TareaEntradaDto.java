package com.gpscoder.quarrytodo.tareas.Dtos;

import com.gpscoder.quarrytodo.usuarios.Dtos.UsuarioTareaDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TareaEntradaDto {

    private Long id;
    private String titulo;
    private String descripcion;
    private boolean finalizada;
    private UsuarioTareaDto usuario;

}
