package com.maliburger.ms_venta.Repository;

import com.maliburger.ms_venta.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    // ver compras ant del cliente
    List<Venta> findByIdUsuario(Long idUsuario);
}
//Muestra el historial de compras de un usuario.