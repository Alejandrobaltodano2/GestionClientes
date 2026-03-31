package com.cliente.Service;

import com.cliente.Event.ClienteEventService;
import com.cliente.Exceptions.ClienteInvalidoException;
import com.cliente.Mappers.ClienteHelper;
import com.cliente.Mappers.ClienteMapper;
import com.cliente.Model.Cliente;
import com.cliente.Model.DTO.ClienteDTO;
import com.cliente.Repository.ClienteRepository;
import com.cliente.Service.Impl.ClienteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceImplTest {

    @Mock
    private ClienteRepository repository;

    @Mock
    private ClienteEventService eventService    ;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private ClienteMapper mapper;

    @Test
    void crearCliente_Exito() {
        ClienteDTO clienteDTO = ClienteHelper.creacionClienteDto();
        when(repository.existsByNombreIgnoreCaseAndApellidoPaternoIgnoreCaseAndApellidoMaternoIgnoreCase(clienteDTO.getNombre(), clienteDTO.getApellidoPaterno(), clienteDTO.getApellidoMaterno()))
                .thenReturn(false);
        when(repository.save(any())).thenReturn(ClienteHelper.clienteEntity_1());

         clienteService.crearCliente(clienteDTO);

        verify(repository).save(any());
    }

    @Test
    void crearCliente_NombreRepetido() {
        ClienteDTO clienteDTO = ClienteHelper.creacionClienteDto();
        when(repository.existsByNombreIgnoreCaseAndApellidoPaternoIgnoreCaseAndApellidoMaternoIgnoreCase(clienteDTO.getNombre(), clienteDTO.getApellidoPaterno(), clienteDTO.getApellidoMaterno()))
                .thenReturn(true);

        assertThrows(ClienteInvalidoException.class, () -> clienteService.crearCliente(clienteDTO));
    }

    @Test
    void listarClientes()
    {

            List<Cliente> clientes = ClienteHelper.clienteList();
            when(repository.findAll(any(Pageable.class)))
                    .thenReturn(new PageImpl<>(clientes));

            var result =  clienteService.mostrarTodosClientes(0, 10);
        assertNotNull(result);

    }
}
