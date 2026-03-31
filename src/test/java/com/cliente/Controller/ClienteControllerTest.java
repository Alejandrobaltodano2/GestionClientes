package com.cliente.Controller;

import com.cliente.Mappers.ControllerHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @Test
    void crearCliente_valido_devuelve201() throws Exception {

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ControllerHelper.creacionClienteJson()))
                .andExpect(status().isCreated());
    }


    @Test
    void crearCliente_nombreNull_devuelve400() throws Exception {

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ControllerHelper.errorClienteJsonIsNull()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void listarCliente_devuelve200() throws Exception {
        mockMvc.perform(get("/clientes?page=0&size=5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.pageNumber").value(0));
    }
}
