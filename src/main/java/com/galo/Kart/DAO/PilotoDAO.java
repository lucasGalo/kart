package com.galo.Kart.DAO;

import com.galo.Kart.models.Piloto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PilotoDAO extends JpaRepository<Piloto, Integer> {

    List<Piloto> findAll();

//    Piloto findById(Integer id);

    Piloto save(Piloto piloto);

}
