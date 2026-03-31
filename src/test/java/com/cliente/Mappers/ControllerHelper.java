package com.cliente.Mappers;

public class ControllerHelper {
    public static String creacionClienteJson()
    {
        return """
                {
                  "nombre": "Alejandro",
                  "apellidoPaterno": "Baltodano",
                  "apellidoMaterno": "Flores",
                  "estado": true
                }
            """;
    }

    public static String errorClienteJsonIsNull()
    {
        return """
                {
                  "nombre": "Alejandro",
                  "apellidoPaterno": "Baltodano",
                  "apellidoMaterno": null,
                  "estado": true
                }
            """;
    }


}
