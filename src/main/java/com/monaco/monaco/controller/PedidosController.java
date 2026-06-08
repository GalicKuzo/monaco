package com.monaco.monaco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.monaco.monaco.model.entidad.Clientes;
import com.monaco.monaco.model.entidad.Pedido;
import com.monaco.monaco.model.service.IClientesService;
import com.monaco.monaco.model.service.IPedidoService;

@Controller
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private IClientesService clientesService;

    @Autowired
    private IPedidoService pedidoService;

    @GetMapping("/")
    public String inicio(Model model){
        model.addAttribute("Cliente", new Clientes());
        model.addAttribute("Pedido", new Pedido());
        model.addAttribute("listaClientes", clientesService.cargarClientes());
        model.addAttribute("listaPedido", pedidoService.cargarPedido());
        return "pedidos/inicio";
    }

    @PostMapping("/guardarCliente")
    public String guardarCliente(Clientes c, RedirectAttributes flash){
        flash.addFlashAttribute("mensajeCliente", clientesService.guardarClientes(c));
        return "redirect:/pedidos/";
    }

    @PostMapping("/guardarPedido")
    public String guardarPedido(Pedido p, RedirectAttributes flash){
        flash.addFlashAttribute("mensajePedido", pedidoService.guardarPedido(p));
        return "redirect:/pedidos/";
    }

    @GetMapping("/finalizar/{id}")
    public String finalizarPedido(@PathVariable("id") Long id) {
        pedidoService.FinalizarPedido(id);
        return "redirect:/pedidos/";
    }
}
