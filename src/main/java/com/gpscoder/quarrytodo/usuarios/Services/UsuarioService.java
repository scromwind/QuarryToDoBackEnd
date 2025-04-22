package com.gpscoder.quarrytodo.usuarios.Services;

import com.gpscoder.quarrytodo.usuarios.Dtos.UsuarioEntradaDto;
import com.gpscoder.quarrytodo.usuarios.Dtos.UsuarioLoginDto;
import com.gpscoder.quarrytodo.usuarios.Dtos.UsuarioSalidaDto;


public interface UsuarioService {
    
    public UsuarioSalidaDto crearUsuario(UsuarioEntradaDto usuario);
    public UsuarioSalidaDto iniciarSesion(UsuarioLoginDto datosLogin);
}
