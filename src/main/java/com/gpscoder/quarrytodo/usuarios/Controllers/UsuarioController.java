package com.gpscoder.quarrytodo.usuarios.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gpscoder.quarrytodo.usuarios.Dtos.UsuarioEntradaDto;
import com.gpscoder.quarrytodo.usuarios.Dtos.UsuarioSalidaDto;
import com.gpscoder.quarrytodo.usuarios.Services.UsuarioService;

@CrossOrigin("*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/crear")
    public ResponseEntity <UsuarioSalidaDto> CrearUsuario(@RequestBody UsuarioEntradaDto usuario) {
        
        UsuarioSalidaDto usuarioSalida = usuarioService.crearUsuario(usuario);
        return ResponseEntity.ok(usuarioSalida);
    }
    
}
