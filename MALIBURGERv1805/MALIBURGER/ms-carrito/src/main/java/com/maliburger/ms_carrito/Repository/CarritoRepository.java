package com.maliburger.ms_carrito.Repository;

import com.maliburger.ms_carrito.Model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    //método que busca si un cliente tiene su carrito abierto
    Optional<Carrito> findByIdUsuario(Long idUsuario);

}