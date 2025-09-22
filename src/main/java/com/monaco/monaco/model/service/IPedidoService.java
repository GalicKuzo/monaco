package com.monaco.monaco.model.service;

import java.util.List;

import com.monaco.monaco.model.entidad.Pedido;

public interface IPedidoService {
    public String guardarPedido(Pedido pedido);
    public List<Pedido> cargarPedido();
    public void FinalizarPedido(Long id);
}
