package com.cliente.Model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ClienteDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @NotBlank
    @NotNull
    private String nombre;

    @NotBlank
    @NotNull
    private String apellidoPaterno;

    @NotNull
    private String apellidoMaterno;

    @JsonIgnore
    private LocalDateTime fechaCreacion;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean estado;
}
