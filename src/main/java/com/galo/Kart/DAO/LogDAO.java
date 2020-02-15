package com.galo.Kart.DAO;

import com.galo.Kart.models.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LogDAO extends JpaRepository<Log, Integer> {

    List<Log> findAll();

    //Log findById(Integer id);

    List<Log> findByNroVolta(int volta);

    Log save(Log log);
}
