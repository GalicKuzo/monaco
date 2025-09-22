package com.monaco.monaco.model.service;

import java.util.List;

import com.monaco.monaco.model.entidad.Clientes;

public interface IClientesService {
    public String guardarClientes(Clientes clientes);
    public List<Clientes> cargarClientes();
}
