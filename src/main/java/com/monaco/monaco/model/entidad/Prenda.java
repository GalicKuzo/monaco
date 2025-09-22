package com.monaco.monaco.model.entidad;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Prenda")
public class Prenda implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Prenda")
    private Long id_Prenda;

    @Column(name = "Escuderia")
    private String Escuderia;

    public Long getId_Prenda() {
        return id_Prenda;
    }

    public void setId_Prenda(Long id_Prenda) {
        this.id_Prenda = id_Prenda;
    }

    public String getEscuderia() {
        return Escuderia;
    }

    public void setEscuderia(String escuderia) {
        Escuderia = escuderia;
    }
    
}
