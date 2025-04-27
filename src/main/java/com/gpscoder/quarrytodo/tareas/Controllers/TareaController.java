package com.gpscoder.quarrytodo.tareas.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gpscoder.quarrytodo.tareas.Dtos.TareaEntradaDto;
import com.gpscoder.quarrytodo.tareas.Dtos.TareaSalidaDto;
import com.gpscoder.quarrytodo.tareas.Services.TareaService;

import lombok.AllArgsConstructor;

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

    @PostMapping("listaTareas")
    public ResponseEntity<List<TareaSalidaDto>> listarTareas(@RequestBody Long usuarioId) {

        return ResponseEntity.ok(tareaService.listarTareas(usuarioId));
    }

    @PostMapping("/crearTarea")
    public ResponseEntity<String>crearTarea(@RequestBody TareaEntradaDto tarea) {
        boolean creado = tareaService.crearTarea(tarea);
        if (creado){
            return ResponseEntity.ok("Usuario Creado con extio");
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
            return new ResponseEntity<>("Error al finalizar la tarea",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    @PostMapping("/buscar")
    public ResponseEntity<TareaSalidaDto>BuscarTarea(@RequestBody Long tareaId){
        TareaSalidaDto tarea = tareaService.buscarTarea(tareaId);
        
        return ResponseEntity.ok(tarea);
    }

    @PostMapping("/eliminar")
    public ResponseEntity<String>ElimnarTarea(@RequestBody Long tareaId){
        boolean eliminado = tareaService.eliminarTarea(tareaId);
        if(eliminado){
            return new ResponseEntity<>("Tarea eliminada correctamente",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Error al eliminar tarea",HttpStatus.INTERNAL_SERVER_ERROR);
        }     
    }
}
