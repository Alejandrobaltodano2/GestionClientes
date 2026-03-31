package com.cliente.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    private String id;

    @NotBlank
    @Column(nullable = false)
    private String nombre;
    @NotBlank
    @Column(nullable = false)
    private String apellidoPaterno;

    @Column(nullable = false)
    private String apellidoMaterno;

    private LocalDateTime fechaCreacion;


    private Boolean estado;
}
