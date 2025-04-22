package com.gpscoder.quarrytodo.usuarios.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLoginDto {

    private String dni;
    private String password;
}
