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
@Table(name = "Personalizado")
public class Personalizado implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Personalizado")
    private Long id_Personalizado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_DetallePrenda", referencedColumnName = "id_DetallePrenda")
    private DetallePrenda id_DetallePrenda;

    @Column(name = "Descripcion")
    private String Descripcion;

    public Long getId_Personalizado() {
        return id_Personalizado;
    }

    public void setId_Personalizado(Long id_Personalizado) {
        this.id_Personalizado = id_Personalizado;
    }

    public DetallePrenda getId_DetallePrenda() {
        return id_DetallePrenda;
    }

    public void setId_DetallePrenda(DetallePrenda id_DetallePrenda) {
        this.id_DetallePrenda = id_DetallePrenda;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
    
}
