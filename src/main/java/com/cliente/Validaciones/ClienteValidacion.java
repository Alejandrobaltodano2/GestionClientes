package com.cliente.Validaciones;

import com.cliente.Exceptions.ClienteInvalidoException;

public class ClienteValidacion {


    public static void nombreRepetidos(Boolean cliente) {

        if (cliente) {
            throw new ClienteInvalidoException("El cliente con el mismo nombre y apellidos ya existe");
        }
    }
}
