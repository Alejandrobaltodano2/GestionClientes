package com.cliente.Mappers;

import com.cliente.Model.Cliente;
import com.cliente.Model.DTO.ClienteDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toEntity(ClienteDTO dto);

    ClienteDTO toDto(Cliente entity);

}
