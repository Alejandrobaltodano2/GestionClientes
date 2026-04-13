package com.cliente.Controller;

import com.cliente.Model.DTO.ClientePostDTO;
import com.cliente.Model.DTO.ClienteResponseDTO;
import com.cliente.Model.Response.PaginacionResponse;
import com.cliente.Service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "Clientes", description = "Operaciones relacionadas a Clientes")
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    @Operation(summary = "Crear un nuevo Cliente", description = "Crea un nuevo cliente con los datos proporcionados")
    public ResponseEntity<Void> crearCliente(
                                                   @RequestBody @Valid ClientePostDTO clienteDTO ,
                                                   @RequestHeader("consumerId") String consumerId,
                                                   @RequestHeader("traceparent") String traceparent,
                                                   @RequestHeader("deviceType") String deviceType,
                                                   @RequestHeader("deviceId") String deviceId
                                                    )
    {

        service.crearCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Obtener todos los Clientes", description = "Listar todos los clientes registrados")
    public ResponseEntity<PaginacionResponse<ClienteResponseDTO>> listarClientes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size ,
            @RequestHeader("consumerId") String consumerId,
            @RequestHeader("traceparent") String traceparent,
            @RequestHeader("deviceType") String deviceType,
            @RequestHeader("deviceId") String deviceId) {
        PaginacionResponse<ClienteResponseDTO> clientes = service.mostrarTodosClientes(page, size);
        return ResponseEntity.ok(clientes);

    }
}