package com.gpscoder.quarrytodo.usuarios.Services.implementaciones;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gpscoder.quarrytodo.usuarios.Dtos.UsuarioEntradaDto;
import com.gpscoder.quarrytodo.usuarios.Dtos.UsuarioLoginDto;
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
            String passwordEncoder = EncoderPassword.encode(usuario.getPassword());
            usuario.setPassword(passwordEncoder);
            UsuarioModel usuarioModel = usuarioMapper.toUsuarioModel(usuario);
            UsuarioModel usuarioGuardado = usuarioRepository.save(usuarioModel);
            return usuarioMapper.toUsuarioSalidaDto(usuarioGuardado);
        }catch(Exception e){
            throw new RuntimeException("Error al Crear el Usuario");
        }

    }

    @Override
    public UsuarioSalidaDto iniciarSesion(UsuarioLoginDto datosLogin) {
        Optional<UsuarioModel> usuarioModel = usuarioRepository.findByDni(datosLogin.getDni());
        if(usuarioModel.isPresent()){
            boolean validarPassword = EncoderPassword.matches(datosLogin.getPassword(), usuarioModel.get().getPassword());
            if (validarPassword) {
                return usuarioMapper.toUsuarioSalidaDto(usuarioModel.get());
            }
            else{
                throw new RuntimeException("Contraseñas no coinciden");
            }
        }else{
            throw new RuntimeException("Usuario no encontrado");
        }
        
    }

}
