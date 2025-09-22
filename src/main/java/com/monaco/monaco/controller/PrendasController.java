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

    @Autowired
    private IPrendaService prendaService;

    @Autowired
    private IDetallePrendaService detallePrendaService;

    @Autowired
    private IPedidoService pedidoService;

    @Autowired
    private IPersonalizadoService personalizadoService;

    @GetMapping("/")
    public String inicio(Model model){
        Prenda prenda = new Prenda();
        DetallePrenda detallePrenda = new DetallePrenda();
        Personalizado personalizado = new Personalizado();
        model.addAttribute("DetallePrenda", detallePrenda);
        model.addAttribute("Prenda", prenda);
        model.addAttribute("Personalizado", personalizado);
        model.addAttribute("listaPrenda", prendaService.cargarPrendas());
        model.addAttribute("listaPedido", pedidoService.cargarPedido());
        model.addAttribute("listaDetallePrenda", detallePrendaService.cargDetallePrendas());
        return "prendas/inicio";
    }

    @PostMapping("/guardarPrenda")
    public String guardarPrenda(Prenda p, RedirectAttributes flash){
        String rpta = prendaService.gurdarPrenda(p);
        flash.addFlashAttribute("mensaje", rpta);
        return "redirect:/prendas/";
    }

    @PostMapping("/guardarDetallePrenda")
    public String guardarDetallePrenda(DetallePrenda dp, RedirectAttributes flash){
        String rpta = detallePrendaService.guardarDetallePrenda(dp);
        flash.addFlashAttribute("mensaje", rpta);
        return "redirect:/prendas/";
    }

    @PostMapping("/guardarPersonalizado")
    public String guardarPersonalizado(Personalizado p, RedirectAttributes flash){
        String rpta = personalizadoService.guardarPersonalizado(p);
        flash.addFlashAttribute("mensaje", rpta);
        return "redirect:/prendas/";
    }
}
