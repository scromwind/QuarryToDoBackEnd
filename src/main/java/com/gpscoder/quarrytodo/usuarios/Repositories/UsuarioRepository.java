package com.gpscoder.quarrytodo.usuarios.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gpscoder.quarrytodo.usuarios.Models.UsuarioModel;
import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{

    Optional<UsuarioModel> findByDni(String dni);
}
