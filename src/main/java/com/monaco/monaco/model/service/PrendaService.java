package com.monaco.monaco.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monaco.monaco.model.dao.IPrendaDAO;
import com.monaco.monaco.model.entidad.Prenda;

@Service
public class PrendaService implements IPrendaService{

    @Autowired
    private IPrendaDAO prendaDAO;

    @Override
    public String gurdarPrenda(Prenda prenda) {
        try {
            String mensaje;

            if (prenda.getId_Prenda() == null) {
                mensaje = "Prenda registrada correctamente";
            } else {
                if (prendaDAO.existsById(prenda.getId_Prenda())) {
                    mensaje = "Prenda actualizada correctamente";
                } else {
                    mensaje = "No se encontró el ID de la prenda, se registrará como nuevo";
                }
            }

            prendaDAO.save(prenda);
            return mensaje;

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public List<Prenda> cargarPrendas() {
        return (List<Prenda>)prendaDAO.findAll();
    }

}
