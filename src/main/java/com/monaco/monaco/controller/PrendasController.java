package com.monaco.monaco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.monaco.monaco.model.entidad.DetallePrenda;
import com.monaco.monaco.model.entidad.Personalizado;
import com.monaco.monaco.model.entidad.Prenda;
import com.monaco.monaco.model.service.IDetallePrendaService;
import com.monaco.monaco.model.service.IPedidoService;
import com.monaco.monaco.model.service.IPersonalizadoService;
import com.monaco.monaco.model.service.IPrendaService;

@Controller
@RequestMapping("/prendas")
public class PrendasController {

    @Autowired private IPrendaService          prendaService;
    @Autowired private IDetallePrendaService   detallePrendaService;
    @Autowired private IPedidoService          pedidoService;
    @Autowired private IPersonalizadoService   personalizadoService;

    @GetMapping("/")
    public String inicio(Model model){
        model.addAttribute("DetallePrenda",    new DetallePrenda());
        model.addAttribute("Prenda",           new Prenda());
        model.addAttribute("Personalizado",    new Personalizado());
        model.addAttribute("listaPrenda",      prendaService.cargarPrendas());
        model.addAttribute("listaPedido",      pedidoService.cargarPedido());
        model.addAttribute("listaDetallePrenda", detallePrendaService.cargDetallePrendas());
        return "prendas/inicio";
    }

    @PostMapping("/guardarPrenda")
    public String guardarPrenda(Prenda p, RedirectAttributes flash){
        flash.addFlashAttribute("mensajePrenda", prendaService.gurdarPrenda(p));
        return "redirect:/prendas/";
    }

    @PostMapping("/guardarDetallePrenda")
    public String guardarDetallePrenda(DetallePrenda dp, RedirectAttributes flash){
        flash.addFlashAttribute("mensajeDetalle", detallePrendaService.guardarDetallePrenda(dp));
        return "redirect:/prendas/";
    }

    @PostMapping("/guardarPersonalizado")
    public String guardarPersonalizado(Personalizado p, RedirectAttributes flash){
        flash.addFlashAttribute("mensajePersonalizado", personalizadoService.guardarPersonalizado(p));
        return "redirect:/prendas/";
    }

    @GetMapping("/productos")
    public String productos(Model model){
        model.addAttribute("listaDetallePrenda", detallePrendaService.cargDetallePrendas());
        return "prendas/productos_comprar";
    }
}
