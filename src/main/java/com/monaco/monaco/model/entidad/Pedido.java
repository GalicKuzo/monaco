package com.monaco.monaco.model.entidad;

import java.io.Serializable;
import java.time.LocalDate;

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
@Table(name = "Pedido")
public class Pedido implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Pedido")
    private Long id_Pedido;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_Cliente", referencedColumnName = "id_Cliente")
    private Clientes id_Cliente;

    @Column(name = "Estado")
    private String Estado;

    @Column(name = "FechaEntrega")
    private LocalDate FechaEntrega;

    @Column(name = "PagoTotal")
    private double PagoTotal;

    public Long getId_Pedido() {
        return id_Pedido;
    }

    public void setId_Pedido(Long id_Pedido) {
        this.id_Pedido = id_Pedido;
    }

    public Clientes getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(Clientes id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public LocalDate getFechaEntrega() {
        return FechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        FechaEntrega = fechaEntrega;
    }

    public double getPagoTotal() {
        return PagoTotal;
    }

    public void setPagoTotal(double pagoTotal) {
        PagoTotal = pagoTotal;
    }
    
}
