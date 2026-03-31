package com.cliente.Service.Impl;

import com.cliente.Event.ClienteEventService;
import com.cliente.Mappers.ClienteMapper;
import com.cliente.Model.Cliente;
import com.cliente.Model.DTO.ClienteDTO;
import com.cliente.Model.Response.PaginacionResponse;
import com.cliente.Repository.ClienteRepository;
import com.cliente.Service.ClienteService;

import com.cliente.Validaciones.ClienteValidacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service

public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private  ClienteRepository clienteRepository;

    @Autowired
    private  ClienteMapper mapper;


    @Autowired
    private ClienteEventService clienteEventService;



    private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Override
    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        ClienteValidacion.nombreRepetidos(clienteRepository.existsByNombreIgnoreCaseAndApellidoPaternoIgnoreCaseAndApellidoMaternoIgnoreCase(clienteDTO.getNombre(), clienteDTO.getApellidoPaterno(), clienteDTO.getApellidoMaterno()));
        clienteDTO.setId(UUID.randomUUID().toString());
        clienteDTO.setFechaCreacion(LocalDateTime.now());
         Cliente cliente =clienteRepository.save(mapper.toEntity(clienteDTO));
        log.info("EVENTO_CLIENTE_CREADO: {}",
                Map.of("id", clienteDTO.getId(),
                        "nombre", clienteDTO.getNombre(),
                        "timestamp", LocalDateTime.now()));
        clienteEventService.enviarEventoCliente(clienteDTO);
        return mapper.toDto(cliente);
    }

    @Override
    public PaginacionResponse<ClienteDTO> mostrarTodosClientes(int page , int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ClienteDTO> clientesPage = clienteRepository.findAll(pageable).map(mapper::toDto);
        log.info("EVENTO_CLIENTES_LISTADOS: {}",
                Map.of("page", page,
                        "size", size,
                        "content", clientesPage.getContent(),
                        "timestamp", LocalDateTime.now()));

        return this.mapToPaginacionResponse(clientesPage);
    }


    private PaginacionResponse<ClienteDTO> mapToPaginacionResponse(Page<ClienteDTO> clientesPage) {
        PaginacionResponse<ClienteDTO> response = new PaginacionResponse<>();
        response.setContent(clientesPage.getContent());
        response.setTotalPages(clientesPage.getTotalPages());
        response.setTotalElements(clientesPage.getTotalElements());
        response.setPageNumber(clientesPage.getNumber());
        response.setPageSize(clientesPage.getSize());

        return response;
    }
}
