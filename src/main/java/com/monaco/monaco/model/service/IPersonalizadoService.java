package com.monaco.monaco.model.service;

import java.util.List;

import com.monaco.monaco.model.entidad.Personalizado;

public interface IPersonalizadoService {
    public String guardarPersonalizado(Personalizado personalizado);
    public List<Personalizado> cargarPersonalizados();
}
