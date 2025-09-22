package com.monaco.monaco.model.service;

import java.util.List;

import com.monaco.monaco.model.entidad.DetallePrenda;

public interface IDetallePrendaService {
    public String guardarDetallePrenda(DetallePrenda detallePrenda);
    public List<DetallePrenda> cargDetallePrendas();

}
