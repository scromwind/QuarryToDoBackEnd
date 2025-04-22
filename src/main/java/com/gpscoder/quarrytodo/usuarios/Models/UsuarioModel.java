package com.gpscoder.quarrytodo.usuarios.Models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60,nullable = false)
    private String nombre;

    @Column(length = 20,nullable = false)
    private String dni;

    @Column(length = 80,nullable = false)
    private String password;

    @CreationTimestamp
    @Column
    private LocalDateTime createdAt;
}
