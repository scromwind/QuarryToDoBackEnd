package com.gpscoder.quarrytodo.usuarios.Services.implementaciones;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gpscoder.quarrytodo.usuarios.Dtos.UsuarioEntradaDto;
import com.gpscoder.quarrytodo.usuarios.Dtos.UsuarioSalidaDto;
import com.gpscoder.quarrytodo.usuarios.Mappers.UsuarioMapper;
import com.gpscoder.quarrytodo.usuarios.Models.UsuarioModel;
import com.gpscoder.quarrytodo.usuarios.Repositories.UsuarioRepository;
import com.gpscoder.quarrytodo.usuarios.Services.UsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImplement implements UsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder EncoderPassword;

    @Override
    public UsuarioSalidaDto crearUsuario(UsuarioEntradaDto usuario) {
        try{
            System.out.println("el usuario es "+usuario);
            String passwordEncoder = EncoderPassword.encode(usuario.getPassword());
            usuario.setPassword(passwordEncoder);
            UsuarioModel usuarioModel = usuarioMapper.toUsuarioModel(usuario);
            UsuarioModel usuarioGuardado = usuarioRepository.save(usuarioModel);
            return usuarioMapper.toUsuarioSalidaDto(usuarioGuardado);
        }catch(Exception e){
            System.err.println(e);
            return null;
        }

    }

}
