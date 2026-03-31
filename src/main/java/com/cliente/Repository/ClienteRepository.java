package com.cliente.Repository;

import com.cliente.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    boolean existsByNombreIgnoreCaseAndApellidoPaternoIgnoreCaseAndApellidoMaternoIgnoreCase(
            String nombre, String apellidoPaterno, String apellidoMaterno);
}
