package com.monaco.monaco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monaco.monaco.model.service.IPedidoService;

@Controller
@RequestMapping("/historial_pedidos")
public class HistorialPedidosController {

    @Autowired
    private IPedidoService pedidoService;
    
    @GetMapping("/")
    public String inicio(Model model){
        model.addAttribute("listaPedido", pedidoService.cargarPedido());
        return "historial_pedidos/inicio";
    }
}
