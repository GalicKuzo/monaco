package com.monaco.monaco.model.service;

import java.util.List;

import com.monaco.monaco.model.entidad.Prenda;

public interface IPrendaService {
    public String gurdarPrenda(Prenda prenda);
    public List<Prenda> cargarPrendas();
}
