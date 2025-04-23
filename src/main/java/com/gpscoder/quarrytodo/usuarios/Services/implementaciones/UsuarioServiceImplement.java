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
            System.err.println(e);
            return null;
        }

    }

    @Override
    public UsuarioSalidaDto iniciarSesion(UsuarioLoginDto datosLogin) {
        System.out.println("los datos enviados por el front son "+datosLogin);
        Optional<UsuarioModel> usuarioModel = usuarioRepository.findByDni(datosLogin.getDni());
        System.out.println("el usuarioModel encontrado es "+ usuarioModel);
        if(usuarioModel.isPresent()){
            boolean validarPassword = EncoderPassword.matches(datosLogin.getPassword(), usuarioModel.get().getPassword());
            System.out.println("la comparacion de la contraseña es "+validarPassword);
            if (validarPassword) {
                return usuarioMapper.toUsuarioSalidaDto(usuarioModel.get());
            }
            else{
                
                System.out.println("Contraseña no coincide");
                return null;
            }
        }else{
            System.out.println("Usuario no encontrado");
            return null;
        }
        
    }

}
