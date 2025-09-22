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
@Table(name = "GastoVarios")
public class GastoVarios implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_GastoVarios")
    private Long id_GastoVarios;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_Gasto", referencedColumnName = "id_Gasto")
    private Gasto id_Gasto;

    @Column(name = "Tipo")
    private String Tipo;

    public Long getId_GastoVarios() {
        return id_GastoVarios;
    }

    public void setId_GastoVarios(Long id_GastoVarios) {
        this.id_GastoVarios = id_GastoVarios;
    }

    public Gasto getId_Gasto() {
        return id_Gasto;
    }

    public void setId_Gasto(Gasto id_Gasto) {
        this.id_Gasto = id_Gasto;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }
    
}
