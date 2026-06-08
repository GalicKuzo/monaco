package com.monaco.monaco.controller;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.monaco.monaco.model.dao.IDetallePrendaDao;
import com.monaco.monaco.model.dao.IPedidoDAO;
import com.monaco.monaco.model.entidad.DetallePrenda;
import com.monaco.monaco.model.entidad.Pedido;

@Controller
public class IndexController {

    @Autowired
    private IDetallePrendaDao detallePrendaDao;

    @Autowired
    private IPedidoDAO pedidoDao;

    @Transactional
    @GetMapping("/")
    public String inicio(Model model) {

        LocalDate hoy      = LocalDate.now();
        LocalDate lunEs    = hoy.with(java.time.DayOfWeek.MONDAY);
        LocalDate domFin   = hoy.with(java.time.DayOfWeek.SUNDAY);
        LocalDate inicioMes = hoy.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate finMes    = hoy.with(TemporalAdjusters.lastDayOfMonth());

        List<Pedido>       todosPedidos  = (List<Pedido>)       pedidoDao.findAll();
        List<DetallePrenda> todasPrendas = (List<DetallePrenda>) detallePrendaDao.findAll();

        /* ── Pedidos pendientes para la tabla ── */
        model.addAttribute("listaDetallePrenda",
            todasPrendas.stream()
                .filter(dp -> "PENDIENTE".equals(dp.getId_Pedido().getEstado()))
                .collect(java.util.stream.Collectors.toList()));

        /* ── Pedidos semanales (creados esta semana, cualquier estado) ── */
        long pedidosSemanales = todosPedidos.stream()
            .filter(p -> p.getFechaEntrega() != null
                && !p.getFechaEntrega().isBefore(lunEs)
                && !p.getFechaEntrega().isAfter(domFin))
            .count();

        /* ── Ingreso semanal (todos los pedidos con fecha esta semana, pago adelantado) ── */
        double ingresoSemanal = todosPedidos.stream()
            .filter(p -> p.getFechaEntrega() != null
                && !p.getFechaEntrega().isBefore(lunEs)
                && !p.getFechaEntrega().isAfter(domFin))
            .mapToDouble(Pedido::getPagoTotal)
            .sum();

        /* ── Ingreso mensual (todos los pedidos con fecha este mes, pago adelantado) ── */
        double ingresoMensual = todosPedidos.stream()
            .filter(p -> p.getFechaEntrega() != null
                && !p.getFechaEntrega().isBefore(inicioMes)
                && !p.getFechaEntrega().isAfter(finMes))
            .mapToDouble(Pedido::getPagoTotal)
            .sum();

        /* ── Pedidos del mes ── */
        long pedidosMes = todosPedidos.stream()
            .filter(p -> p.getFechaEntrega() != null
                && !p.getFechaEntrega().isBefore(inicioMes)
                && !p.getFechaEntrega().isAfter(finMes))
            .count();

        /* ── Escudería más vendida (todos los pedidos) ── */
        Map<String, Integer> conteo = new HashMap<>();
        todasPrendas.stream()
            .forEach(dp -> {
                String esc = dp.getId_Prenda().getEscuderia();
                conteo.merge(esc, dp.getCantidad(), Integer::sum);
            });

        String escuderiaMasVendida = conteo.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse("—");

        /* ── Pasar al modelo ── */
        model.addAttribute("pedidosSemanales",      pedidosSemanales);
        model.addAttribute("ingresoSemanal",         String.format("%.0f", ingresoSemanal));
        model.addAttribute("ingresoMensual",         String.format("%.0f", ingresoMensual));
        model.addAttribute("pedidosMes",             pedidosMes);
        model.addAttribute("escuderiaMasVendida",    escuderiaMasVendida);

        return "index";
    }
}
