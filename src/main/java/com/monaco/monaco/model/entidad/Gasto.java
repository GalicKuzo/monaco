package com.monaco.monaco.model.entidad;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Gasto")
public class Gasto implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Gasto")
    private Long id_Gasto;

    @Column(name = "CostoTotal")
    private double CostoTotal;

    public Long getId_Gasto() {
        return id_Gasto;
    }

    public void setId_Gasto(Long id_Gasto) {
        this.id_Gasto = id_Gasto;
    }

    public double getCostoTotal() {
        return CostoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        CostoTotal = costoTotal;
    }
    
}
