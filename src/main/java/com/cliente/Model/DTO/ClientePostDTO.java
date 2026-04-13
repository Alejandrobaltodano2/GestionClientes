package com.cliente.Model.DTO;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientePostDTO {

    @JsonIgnore
    private String id;

    @NotBlank
    @NotNull
    private String nombre;

    @NotBlank
    @NotNull
    private String apellidoPaterno;

    @NotBlank
    @NotNull
    private String apellidoMaterno;


    private Boolean estado;

    @JsonIgnore
    private LocalDateTime fechaCreacion;
}
