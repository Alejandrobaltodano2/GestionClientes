package com.cliente.Validaciones;
import com.cliente.Exceptions.ClienteInvalidoException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HeaderValidationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI();

        if (path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs")) {
            chain.doFilter(request, response);
            return;
        }

        String consumerId = httpRequest.getHeader("consumerId");
        String traceparent = httpRequest.getHeader("traceparent");
        String deviceType = httpRequest.getHeader("deviceType");
        String deviceId = httpRequest.getHeader("deviceId");

        if (consumerId == null || traceparent == null || deviceType == null || deviceId == null) {
            throw new ClienteInvalidoException("Headers requeridos faltantes");

        }

        if (!deviceType.equals("IOS") && !deviceType.equals("AND")) {
            throw new ClienteInvalidoException("deviceType inválido");
        }

        chain.doFilter(request, response);
    }
}
