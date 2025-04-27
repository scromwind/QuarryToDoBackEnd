package com.gpscoder.quarrytodo.tareas.Mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.gpscoder.quarrytodo.tareas.Dtos.TareaEntradaDto;
import com.gpscoder.quarrytodo.tareas.Dtos.TareaSalidaDto;
import com.gpscoder.quarrytodo.tareas.Models.TareaModel;
import com.gpscoder.quarrytodo.usuarios.Mappers.UsuarioMapper;

@Mapper(componentModel = "spring",uses = UsuarioMapper.class)
public interface TareaMapper{

    public TareaSalidaDto toSalidaDto(TareaModel tareaModel);
    
    public List<TareaSalidaDto> toListDto(List<TareaModel> tareas);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "usuario",ignore = true)
    public TareaModel toModel(TareaEntradaDto tareaEntradaDto);
}
