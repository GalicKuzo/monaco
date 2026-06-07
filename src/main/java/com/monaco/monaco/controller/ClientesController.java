package com.monaco.monaco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.monaco.monaco.model.dao.IClientesDAO;
import com.monaco.monaco.model.entidad.Clientes;

@Controller
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private IClientesDAO clientesDao;

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("listaClientes", clientesDao.findAll());
        return "clientes/inicio";
    }

    @PostMapping("/editar")
    public String editar(
            @RequestParam("id_Cliente") Long id,
            @RequestParam("Nombre")     String nombre,
            @RequestParam("DNI")        String dni,
            @RequestParam("Celular")    String celular,
            @RequestParam("Distrito")   String distrito) {

        Clientes cliente = clientesDao.findById(id).orElse(null);
        if (cliente != null) {
            cliente.setNombre(nombre);
            cliente.setDNI(dni);
            cliente.setCelular(celular);
            cliente.setDistrito(distrito);
            clientesDao.save(cliente);
        }
        return "redirect:/clientes/";
    }
}
