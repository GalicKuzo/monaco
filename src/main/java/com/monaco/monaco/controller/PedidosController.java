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
        Clientes clientes = new Clientes();
        Pedido pedido = new Pedido();
        model.addAttribute("Cliente", clientes);
        model.addAttribute("Pedido", pedido);
        model.addAttribute("listaClientes", clientesService.cargarClientes());
        model.addAttribute("listaPedido", pedidoService.cargarPedido());
        return"pedidos/inicio";
    }

    @PostMapping("/guardarCliente")
    public String guardarCliente(Clientes c, RedirectAttributes flash){
        String rpta = clientesService.guardarClientes(c);
        flash.addFlashAttribute("mensaje", rpta);
        return "redirect:/pedidos/";
    }

    @PostMapping("/guardarPedido")
    public String guardarPedido(Pedido p, RedirectAttributes flash){
        String rpta = pedidoService.guardarPedido(p);
        flash.addFlashAttribute("mensaje",rpta);
        return "redirect:/pedidos/";
    }

    @GetMapping("/finalizar/{id}")
    public String finalizarPedido(@PathVariable("id") Long id) {
        pedidoService.FinalizarPedido(id);
        return "redirect:/pedidos/"; 
    }

}
