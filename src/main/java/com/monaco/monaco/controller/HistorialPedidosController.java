package com.monaco.monaco.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monaco.monaco.controller.dto.DetallePrendaDTO;
import com.monaco.monaco.model.dao.IDetallePrendaDao;
import com.monaco.monaco.model.entidad.DetallePrenda;
import com.monaco.monaco.model.entidad.Personalizado;
import com.monaco.monaco.model.service.IPedidoService;

@Controller
@RequestMapping("/historial_pedidos")
public class HistorialPedidosController {

    @Autowired
    private IPedidoService pedidoService;

    @Autowired
    private IDetallePrendaDao detallePrendaDao;

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("listaPedido", pedidoService.cargarPedido());
        return "historial_pedidos/inicio";
    }

    @Transactional
    @GetMapping("/detalle/{idPedido}")
    @ResponseBody
    public List<DetallePrendaDTO> detalle(@PathVariable Long idPedido) {
        List<DetallePrenda> lista = detallePrendaDao.findByIdPedido(idPedido);

        return lista.stream().map(dp -> {
            String escuderia = dp.getId_Prenda() != null
                ? dp.getId_Prenda().getEscuderia() : "";

            String custom = "";
            List<Personalizado> pers = dp.getPersonalizados();
            if (pers != null && !pers.isEmpty()) {
                custom = pers.get(0).getDescripcion();
            }

            return new DetallePrendaDTO(
                escuderia,
                dp.getColor(),
                dp.getTipoPrenda(),
                dp.getTipoCorte(),
                dp.getTalla(),
                dp.getCantidad(),
                custom
            );
        }).collect(Collectors.toList());
    }
}
