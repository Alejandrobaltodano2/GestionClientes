package com.cliente.Event;

import com.cliente.Model.DTO.ClienteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class ClienteEventService {

    private static final Logger log = LoggerFactory.getLogger(ClienteEventService.class);

    @Async
    public void enviarEventoCliente(ClienteDTO clienteDTO) {
        String json = new ObjectMapper().writeValueAsString(clienteDTO);
        log.info("SIMULACION_ENVIO_EVENTO: {}", json);
    }

}
