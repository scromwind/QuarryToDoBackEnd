package com.gpscoder.quarrytodo.tareas.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gpscoder.quarrytodo.tareas.Models.TareaModel;

@Repository
public interface TareaRepository extends JpaRepository<TareaModel,Long>{

    List<TareaModel> findByFinalizadaFalseAndUsuarioId(Long id);
    List<TareaModel> findByFinalizadaTrueAndUsuarioId(Long id);
}
