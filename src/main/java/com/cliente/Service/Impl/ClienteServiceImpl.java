package com.cliente.Service.Impl;

import com.cliente.Event.ClienteEventService;
import com.cliente.Mappers.ClienteMapper;
import com.cliente.Model.Cliente;
import com.cliente.Model.DTO.ClientePostDTO;
import com.cliente.Model.DTO.ClienteResponseDTO;
import com.cliente.Model.Response.PaginacionResponse;
import com.cliente.Repository.ClienteRepository;
import com.cliente.Service.ClienteService;
import com.cliente.Validaciones.ClienteValidacion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private  ClienteEventService clienteEventService;

    private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);


    @Override
    public void crearCliente(ClientePostDTO clienteDTO) {
        ClienteValidacion.validarCliente(clienteDTO);
        ClienteValidacion.nombreRepetidos(
                clienteRepository.existsByNombreIgnoreCaseAndApellidoPaternoIgnoreCaseAndApellidoMaternoIgnoreCase(
                        clienteDTO.getNombre(),
                        clienteDTO.getApellidoPaterno(),
                        clienteDTO.getApellidoMaterno()
                )
        );

        var id = UUID.randomUUID().toString();
        var ahora = LocalDateTime.now();

        clienteDTO.setId(id);
        clienteDTO.setFechaCreacion(ahora);

        clienteRepository.save(mapper.toEntity(clienteDTO));

        log.info("EVENTO_CLIENTE_CREADO: {}", Map.of(
                "id",        id,
                "nombre",    clienteDTO.getNombre(),
                "timestamp", ahora
        ));

        clienteEventService.enviarEventoCliente(clienteDTO);
    }

    @Override
    public PaginacionResponse<ClienteResponseDTO> mostrarTodosClientes(int page, int size) {
        var pageable = PageRequest.of(page, size);

        var clientesPage = clienteRepository.findAll(pageable)
                .map(this::toClienteDtoConNombreCompleto);

        log.info("EVENTO_CLIENTES_LISTADOS: {}", Map.of(
                "page",      page,
                "size",      size,
                "total",     clientesPage.getTotalElements(),
                "timestamp", LocalDateTime.now()
        ));

        return mapToPaginacionResponse(clientesPage);
    }

    private ClienteResponseDTO toClienteDtoConNombreCompleto(Cliente cliente) {
        var dto = mapper.toDto(cliente);
        dto.setNombreCompleto(
                "%s %s %s".formatted(
                        cliente.getNombre(),
                        cliente.getApellidoPaterno(),
                        cliente.getApellidoMaterno()
                )
        );
        return dto;
    }


    private PaginacionResponse<ClienteResponseDTO> mapToPaginacionResponse(Page<ClienteResponseDTO> clientesPage) {
        var response = new PaginacionResponse<ClienteResponseDTO>();
        response.setContent(clientesPage.getContent());
        response.setTotalPages(clientesPage.getTotalPages());
        response.setTotalElements(clientesPage.getTotalElements());
        response.setPageNumber(clientesPage.getNumber());
        response.setPageSize(clientesPage.getSize());
        return response;
    }
}