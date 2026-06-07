package com.monaco.monaco.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.monaco.monaco.model.entidad.DetallePrenda;

public interface IDetallePrendaDao extends CrudRepository<DetallePrenda, Long> {

    // id_Pedido es el campo en DetallePrenda (ManyToOne a Pedido)
    // id_Pedido.id_Pedido es el PK de Pedido
    @Query("SELECT dp FROM DetallePrenda dp " +
           "JOIN FETCH dp.id_Prenda " +
           "LEFT JOIN FETCH dp.personalizados " +
           "WHERE dp.id_Pedido.id_Pedido = :idPedido")
    List<DetallePrenda> findByIdPedido(@Param("idPedido") Long idPedido);
}
