package com.monaco.monaco.model.entidad;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "DetallePrenda")
public class DetallePrenda implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_DetallePrenda")
    private Long id_DetallePrenda;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_Prenda", referencedColumnName = "id_Prenda")
    private Prenda id_Prenda;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_Pedido", referencedColumnName = "id_Pedido")
    private Pedido id_Pedido;

    @Column(name = "Color")
    private String Color;

    @Column(name = "TipoPrenda")
    private String TipoPrenda;

    @Column(name = "TipoCorte")
    private String TipoCorte;

    @Column(name = "Talla")
    private String Talla;

    @Column(name = "Cantidad")
    private int Cantidad;

    @OneToMany(mappedBy = "id_DetallePrenda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Personalizado> personalizados;


    public Long getId_DetallePrenda() {
        return id_DetallePrenda;
    }

    public void setId_DetallePrenda(Long id_DetallePrenda) {
        this.id_DetallePrenda = id_DetallePrenda;
    }

    public Prenda getId_Prenda() {
        return id_Prenda;
    }

    public void setId_Prenda(Prenda id_Prenda) {
        this.id_Prenda = id_Prenda;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getTipoPrenda() {
        return TipoPrenda;
    }

    public void setTipoPrenda(String tipoPrenda) {
        TipoPrenda = tipoPrenda;
    }

    public String getTipoCorte() {
        return TipoCorte;
    }

    public void setTipoCorte(String tipoCorte) {
        TipoCorte = tipoCorte;
    }

    public String getTalla() {
        return Talla;
    }

    public void setTalla(String talla) {
        Talla = talla;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public Pedido getId_Pedido() {
        return id_Pedido;
    }

    public void setId_Pedido(Pedido id_Pedido) {
        this.id_Pedido = id_Pedido;
    }

    public List<Personalizado> getPersonalizados() {
        return personalizados;
    }

    public void setPersonalizados(List<Personalizado> personalizados) {
        this.personalizados = personalizados;
    }

    
}
