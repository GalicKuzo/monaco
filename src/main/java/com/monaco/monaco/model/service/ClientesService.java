package com.monaco.monaco.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monaco.monaco.model.dao.IClientesDAO;
import com.monaco.monaco.model.entidad.Clientes;

@Service
public class ClientesService implements IClientesService{

    @Autowired
    private IClientesDAO clientesDAO;

    @Override
    public String guardarClientes(Clientes clientes) {
        try {
            String mensaje;

            if (clientes.getId_Cliente() == null) {
                mensaje = "Cliente registrado correctamente";
            } else {
                if (clientesDAO.existsById(clientes.getId_Cliente())) {
                    mensaje = "Cliente actualizado correctamente";
                } else {
                    mensaje = "No se encontró el ID del cliente, se registrará como nuevo";
                }
            }

            clientesDAO.save(clientes);
            return mensaje;

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public List<Clientes> cargarClientes() {
        return (List<Clientes>)clientesDAO.findAll();
    }
}
