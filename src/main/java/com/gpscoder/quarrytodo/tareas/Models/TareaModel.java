package com.gpscoder.quarrytodo.tareas.Models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.gpscoder.quarrytodo.usuarios.Models.UsuarioModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TareaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false)
    private String titulo;

    @Column(length = 200, nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private boolean finalizada;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;

    @Column
    @CreationTimestamp
    private LocalDateTime createdAt;
}
