package com.maliburger.MALIBURGER.Repository;

import com.maliburger.MALIBURGER.Model.Malibur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MaliRepository extends JpaRepository<Malibur, Long> {

    @Query("SELECT m FROM Malibur m WHERE LOWER(m.proteina) LIKE LOWER(CONCAT('%', :proteina, '%'))")
    List<Malibur> buscarPorProteina(@Param("proteina") String proteina);

    @Query("SELECT m FROM Malibur m WHERE m.precio BETWEEN :min AND :max ORDER BY m.precio")
    List<Malibur> findByprecioBetween(@Param("min") int min, @Param("max") int max);


}
//intermediario entre java y los datos de oracle para el crud