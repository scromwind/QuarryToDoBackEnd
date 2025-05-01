package com.gpscoder.quarrytodo.tareas.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gpscoder.quarrytodo.tareas.Dtos.TareaEntradaDto;
import com.gpscoder.quarrytodo.tareas.Dtos.TareaSalidaDto;
import com.gpscoder.quarrytodo.tareas.Services.TareaService;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@RestController
@RequestMapping("/tareas")
public class TareaController {

    private final TareaService tareaService;

    @PostMapping("/listaTareasPendientes")
    public ResponseEntity<List<TareaSalidaDto>> listarTareasPendientes(@RequestBody Long usuarioId) {
        List<TareaSalidaDto> listaTareas = tareaService.listarTareasPendientes(usuarioId);
        
        if(listaTareas != null){
            return new ResponseEntity<>(listaTareas,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.ACCEPTED);
        }
    }

    @PostMapping("/listaTareasFinalizadas")
    public ResponseEntity<List<TareaSalidaDto>> listarTareasFinalizadas(@RequestBody Long usuarioId) {
        List<TareaSalidaDto> listaTareas = tareaService.listarTareasFinalizadas(usuarioId);
        
        if(listaTareas != null){
            return new ResponseEntity<>(listaTareas,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.ACCEPTED);
        }
    }

    @PostMapping("/crearTarea")
    public ResponseEntity<String>crearTarea(@RequestBody TareaEntradaDto tarea) {
        boolean creado = tareaService.crearTarea(tarea);
        if (creado){
            return ResponseEntity.ok("Usuario Creado con exito");
        }else{
            return ResponseEntity.ok("Error al crear usuario");
        }
    }

    @PostMapping("/finalizar")
    public ResponseEntity<String>finalizarTarea(@RequestBody Long tareaId){
        boolean finalizada = tareaService.finalizarTarea(tareaId);
        if (finalizada) {
            return new ResponseEntity<>("Tarea finalizada con exito",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Error al finalizar la tarea",HttpStatus.ACCEPTED);
        }
    }

    @PostMapping("/tareaPendiente")
    public ResponseEntity<String>tareaPendiente(@RequestBody Long tareaId){
        boolean finalizada = tareaService.tareaPendiente(tareaId);
        if (finalizada) {
            return new ResponseEntity<>("La tarea se marco como pendiente",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Error al Volver a pendientes la tarea",HttpStatus.ACCEPTED);
        }
    }
    
    @PostMapping("/buscar")
    public ResponseEntity<TareaSalidaDto>BuscarTarea(@RequestBody Long tareaId){
        TareaSalidaDto tarea = tareaService.buscarTarea(tareaId);

        if(tarea != null){
            return new ResponseEntity<>(tarea,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        
    }

    @PostMapping("/eliminar")
    public ResponseEntity<String>ElimnarTarea(@RequestBody Long tareaId){
        boolean eliminado = tareaService.eliminarTarea(tareaId);
        if(eliminado){
            return new ResponseEntity<>("Tarea eliminada correctamente",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Error al eliminar tarea",HttpStatus.ACCEPTED);
        }     
    }
}
