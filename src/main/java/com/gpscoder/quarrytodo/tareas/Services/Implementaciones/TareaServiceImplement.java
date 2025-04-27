package com.gpscoder.quarrytodo.tareas.Services.Implementaciones;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gpscoder.quarrytodo.tareas.Dtos.TareaEntradaDto;
import com.gpscoder.quarrytodo.tareas.Dtos.TareaSalidaDto;
import com.gpscoder.quarrytodo.tareas.Mappers.TareaMapper;
import com.gpscoder.quarrytodo.tareas.Models.TareaModel;
import com.gpscoder.quarrytodo.tareas.Repositories.TareaRepository;
import com.gpscoder.quarrytodo.tareas.Services.TareaService;
import com.gpscoder.quarrytodo.usuarios.Models.UsuarioModel;
import com.gpscoder.quarrytodo.usuarios.Repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TareaServiceImplement implements TareaService{

    private final TareaMapper tareaMapper;
    private final TareaRepository tareaRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<TareaSalidaDto> listarTareas(Long usuarioId) {
        List<TareaModel> tareaModel = tareaRepository.findByFinalizadaFalseAndUsuarioId(usuarioId);
        if(!tareaModel.isEmpty()){
            return tareaMapper.toListDto(tareaModel);
        }else{
            return null;
        }
    }

    @Override
    public boolean eliminarTarea(Long idTarea) {
        
        boolean tareaExiste = tareaRepository.existsById(idTarea);

        if (tareaExiste) {
            tareaRepository.deleteById(idTarea);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean crearTarea(TareaEntradaDto tarea) {
        UsuarioModel usuarioModel = usuarioRepository.findById(tarea.getUsuario().getId()).get();
        TareaModel tareaModel = tareaMapper.toModel(tarea);
        if(tareaModel != null && usuarioModel != null){
            tareaModel.setUsuario(usuarioModel);
            TareaModel tareaCreado = tareaRepository.save(tareaModel);
            if (tareaCreado != null) {
                return true;
            }else{
                throw new RuntimeException("Error al Crear la tarea");
            }
        }else{
            throw new RuntimeException("error al buscar usuario o transformar tarea");
        }
    }

    @Override
    public boolean actualizarTarea(TareaEntradaDto tareaDto) {
        TareaModel tareaModel = tareaRepository.findById(tareaDto.getId()).get();
        if (tareaModel != null) {
            tareaModel.setTitulo(tareaDto.getTitulo());
            tareaModel.setDescripcion(tareaDto.getDescripcion());
            TareaModel tareaActualizada = tareaRepository.save(tareaModel);
            if(tareaActualizada != null){
                return true;
            }else{
                return false;
            }
        }else{
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    @Override
    public TareaSalidaDto buscarTarea(Long idTarea) {
        TareaModel tareaModel = tareaRepository.findById(idTarea).get();
        if(tareaModel != null){
            TareaSalidaDto tareaSalidaDto = tareaMapper.toSalidaDto(tareaModel);
            if (tareaSalidaDto != null) {
                return tareaSalidaDto;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public boolean finalizarTarea(Long idTarea) {
        boolean existe = tareaRepository.existsById(idTarea);
        if(existe){
            TareaModel tareaModel = tareaRepository.findById(idTarea).get();
            tareaModel.setFinalizada(true);
            TareaModel tareaGuardada = tareaRepository.save(tareaModel);
            if (tareaGuardada != null) {
                return true;
            }else{
                return false;
            }
        }else{
            return false; 
        }
    }

}
