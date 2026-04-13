package com.cliente.Mappers;

import com.cliente.Model.Cliente;
import com.cliente.Model.DTO.ClientePostDTO;

import java.util.List;

public class ClienteHelper {


    public static ClientePostDTO creacionClienteDto()
    {
        return ClientePostDTO.builder()
                .nombre("Juan")
                .apellidoPaterno("Perez")
                .apellidoMaterno("Gomez")
                .estado(true)
                .build();
    }

    public static Cliente clienteEntity_1()
    {
        return Cliente.builder()
                .id("123e4567-e89b-12d3-a456-426614174000")
                .nombre("Juan")
                .apellidoPaterno("Perez")
                .apellidoMaterno("Gomez")
                .estado(true)
                .build();
    }

    public static List<Cliente> clienteList()
    {
        return List.of(
                Cliente.builder()
                        .id("123e4567-e89b-12d3-a456-426614174000")
                        .nombre("Juan")
                        .apellidoPaterno("Perez")
                        .apellidoMaterno("Gomez")
                        .estado(true)
                        .build(),
                Cliente.builder()
                        .id("123e4567-e89b-12d3-a456-426614174001")
                        .nombre("Maria")
                        .apellidoPaterno("Lopez")
                        .apellidoMaterno("Diaz")
                        .estado(true)
                        .build()
        );
    }
}
