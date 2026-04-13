package com.cliente.Service;

import com.cliente.Model.DTO.ClientePostDTO;
import com.cliente.Model.DTO.ClienteResponseDTO;
import com.cliente.Model.Response.PaginacionResponse;


public interface ClienteService {

    void crearCliente(ClientePostDTO clienteDTO);
    PaginacionResponse<ClienteResponseDTO> mostrarTodosClientes(int page , int size);

}
