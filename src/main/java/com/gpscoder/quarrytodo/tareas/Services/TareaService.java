package com.gpscoder.quarrytodo.tareas.Services;

import java.util.List;

import com.gpscoder.quarrytodo.tareas.Dtos.TareaEntradaDto;
import com.gpscoder.quarrytodo.tareas.Dtos.TareaSalidaDto;

public interface TareaService {
    List<TareaSalidaDto> listarTareasPendientes(Long usuarioId);
    List<TareaSalidaDto> listarTareasFinalizadas(Long usuarioId);
    boolean eliminarTarea(Long idTarea);
    boolean crearTarea(TareaEntradaDto tarea);
    boolean actualizarTarea(TareaEntradaDto tareaDto);
    TareaSalidaDto buscarTarea(Long idTarea);
    boolean finalizarTarea(Long idTarea);
    boolean tareaPendiente(Long idTarea);
} 
