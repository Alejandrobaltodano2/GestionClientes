package com.cliente.Mappers;

import com.cliente.Model.Cliente;
import com.cliente.Model.DTO.ClientePostDTO;
import com.cliente.Model.DTO.ClienteResponseDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toEntity(ClientePostDTO dto);

    ClienteResponseDTO toDto(Cliente entity);

}
