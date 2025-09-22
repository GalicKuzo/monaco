package com.monaco.monaco.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monaco.monaco.model.dao.IDetallePrendaDAO;
import com.monaco.monaco.model.entidad.DetallePrenda;

@Service
public class DetallePrendaService implements IDetallePrendaService{

    @Autowired
    private IDetallePrendaDAO prendaDAO;

    @Override
    public String guardarDetallePrenda(DetallePrenda detallePrenda) {
        try {
            String mensaje;

            if (detallePrenda.getId_DetallePrenda() == null) {
                mensaje = "Detalle de la Prenda registrado correctamente";
            } else {
                if (prendaDAO.existsById(detallePrenda.getId_DetallePrenda())) {
                    mensaje = "Detalle de la Prenda actualizado correctamente";
                } else {
                    mensaje = "No se encontró el ID del Detalle de la Prenda, se registrará como nuevo";
                }
            }

            prendaDAO.save(detallePrenda);
            return mensaje;

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public List<DetallePrenda> cargDetallePrendas() {
        return (List<DetallePrenda>)prendaDAO.findAll();
    }
}
