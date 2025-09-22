package com.monaco.monaco.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monaco.monaco.model.dao.IPersonalizadoDAO;
import com.monaco.monaco.model.entidad.Personalizado;

@Service
public class PersonalizadoService implements IPersonalizadoService{

    @Autowired
    private IPersonalizadoDAO personalizadoDAO;

    @Override
    public String guardarPersonalizado(Personalizado personalizado) {
        try {
            String mensaje;

            if (personalizado.getId_Personalizado() == null) {
                mensaje = "Personalizacion registrada correctamente";
            } else {
                if (personalizadoDAO.existsById(personalizado.getId_Personalizado())) {
                    mensaje = "Personalizacion actualizada correctamente";
                } else {
                    mensaje = "No se encontró el ID de la Personalizacion, se registrará como nuevo";
                }
            }

            personalizadoDAO.save(personalizado);
            return mensaje;

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public List<Personalizado> cargarPersonalizados() {
        return (List<Personalizado>)personalizadoDAO.findAll();
    }
}
