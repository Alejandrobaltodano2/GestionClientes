package com.cliente.Service;

import com.cliente.Model.DTO.ClienteDTO;
import com.cliente.Model.Response.PaginacionResponse;


public interface ClienteService {

    void crearCliente(ClienteDTO clienteDTO);
    PaginacionResponse<ClienteDTO> mostrarTodosClientes(int page , int size);

}
