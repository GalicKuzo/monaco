package com.monaco.monaco.model.entidad;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "GastoPrenda")
public class GastoPrenda implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_GastoPrenda")
    private Long id_GastoPrenda;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_Gasto", referencedColumnName = "id_Gasto")
    private Gasto id_Gasto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_DetallePrenda", referencedColumnName = "id_DetallePrenda")
    private DetallePrenda id_DetallePrenda;

    @Column(name = "Cantidad")
    private int Cantidad;

    public Long getId_GastoPrenda() {
        return id_GastoPrenda;
    }

    public void setId_GastoPrenda(Long id_GastoPrenda) {
        this.id_GastoPrenda = id_GastoPrenda;
    }

    public Gasto getId_Gasto() {
        return id_Gasto;
    }

    public void setId_Gasto(Gasto id_Gasto) {
        this.id_Gasto = id_Gasto;
    }

    public DetallePrenda getId_DetallePrenda() {
        return id_DetallePrenda;
    }

    public void setId_DetallePrenda(DetallePrenda id_DetallePrenda) {
        this.id_DetallePrenda = id_DetallePrenda;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    
}
