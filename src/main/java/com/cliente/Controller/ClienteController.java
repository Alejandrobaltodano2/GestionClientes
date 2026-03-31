package com.cliente.Controller;

import com.cliente.Model.DTO.ClienteDTO;
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

    public ResponseEntity<ClienteDTO> crearCliente(
                                                   @RequestBody @Valid ClienteDTO clienteDTO ,
                                                   @RequestHeader("consumerId") String consumerId,
                                                   @RequestHeader("traceparent") String traceparent,
                                                   @RequestHeader("deviceType") String deviceType,
                                                   @RequestHeader("deviceId") String deviceId
                                                    )
    {
        if (!deviceType.equals("IOS") && !deviceType.equals("AND")) {
            return ResponseEntity.badRequest().build();
        }
        service.crearCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los Clientes", description = "Listar todos los clientes registrados")
    public ResponseEntity<PaginacionResponse<ClienteDTO>> listarClientes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size ,
            @RequestHeader("consumerId") String consumerId,
            @RequestHeader("traceparent") String traceparent,
            @RequestHeader("deviceType") String deviceType,
            @RequestHeader("deviceId") String deviceId) {
        if (!deviceType.equals("IOS") && !deviceType.equals("AND")) {
            return ResponseEntity.badRequest().build();
        }
        PaginacionResponse<ClienteDTO> clientes = service.mostrarTodosClientes(page, size);
        return ResponseEntity.ok(clientes);

    }
}