package com.monaco.monaco.controller.dto;

/**
 * DTO simple para serializar DetallePrenda sin referencias circulares.
 * Usar en el endpoint GET /historial_pedidos/detalle/{id}
 */
public class DetallePrendaDTO {
    public String escuderia;
    public String color;
    public String tipoPrenda;
    public String tipoCorte;
    public String talla;
    public int    cantidad;
    public String personalizado; // primer personalizado si existe

    public DetallePrendaDTO() {}

    public DetallePrendaDTO(String escuderia, String color, String tipoPrenda,
                             String tipoCorte, String talla, int cantidad,
                             String personalizado) {
        this.escuderia     = escuderia;
        this.color         = color;
        this.tipoPrenda    = tipoPrenda;
        this.tipoCorte     = tipoCorte;
        this.talla         = talla;
        this.cantidad      = cantidad;
        this.personalizado = personalizado;
    }
}
