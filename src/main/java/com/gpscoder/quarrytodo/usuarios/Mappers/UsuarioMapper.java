package com.gpscoder.quarrytodo.usuarios.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gpscoder.quarrytodo.usuarios.Dtos.UsuarioEntradaDto;
import com.gpscoder.quarrytodo.usuarios.Dtos.UsuarioLoginDto;
import com.gpscoder.quarrytodo.usuarios.Dtos.UsuarioSalidaDto;
import com.gpscoder.quarrytodo.usuarios.Models.UsuarioModel;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    public UsuarioSalidaDto toUsuarioSalidaDto (UsuarioModel usuario);

    @Mapping(target = "createdAt",ignore = true)
    public UsuarioModel toUsuarioModel(UsuarioEntradaDto usuario);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "nombre",ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public UsuarioModel toUsuarioModel(UsuarioLoginDto usuario);
}
