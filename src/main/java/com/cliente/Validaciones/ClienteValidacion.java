package com.cliente.Validaciones;

import com.cliente.Exceptions.ClienteInvalidoException;
import com.cliente.Model.DTO.ClientePostDTO;

public class ClienteValidacion {


    public static void validarCliente(ClientePostDTO clienteDTO) {
        validarSoloTexto(clienteDTO.getNombre());

        validarSoloTexto(clienteDTO.getApellidoPaterno());
        validarSoloTexto(clienteDTO.getApellidoMaterno());


    }


    public static void nombreRepetidos(Boolean cliente) {

        if (cliente) {
            throw new ClienteInvalidoException("El cliente con el mismo nombre y apellidos ya existe");
        }
    }

    private static void validarSoloTexto(String texto) {
        if (!texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+$")) {
            throw new ClienteInvalidoException("El campo solo puede contener letras");
        }
    }

}
