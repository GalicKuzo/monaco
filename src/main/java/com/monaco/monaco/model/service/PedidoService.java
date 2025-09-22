package com.monaco.monaco.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monaco.monaco.model.dao.IPedidoDAO;
import com.monaco.monaco.model.entidad.Pedido;

@Service
public class PedidoService implements IPedidoService{

    @Autowired
    private IPedidoDAO pedidoDAO;

    @Override
    public String guardarPedido(Pedido pedido) {
        try {
            String mensaje;

            if (pedido.getId_Pedido() == null) {
                mensaje = "Pedido registrado correctamente";
            } else {
                if (pedidoDAO.existsById(pedido.getId_Pedido())) {
                    mensaje = "Pedido actualizado correctamente";
                } else {
                    mensaje = "No se encontró el ID del pedido, se registrará como nuevo";
                }
            }

            pedidoDAO.save(pedido);
            return mensaje;

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public List<Pedido> cargarPedido() {
        return (List<Pedido>)pedidoDAO.findAll();
    }

    @Override
    public void FinalizarPedido(Long id) {
        
        Optional<Pedido> pedidoOpt = pedidoDAO.findById(id);
        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();
            pedido.setEstado("COMPLETADO");
            pedidoDAO.save(pedido);
        }
    }

    

}
