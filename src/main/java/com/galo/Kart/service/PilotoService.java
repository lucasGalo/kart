package com.galo.Kart.service;

import com.galo.Kart.DAO.PilotoDAO;
import com.galo.Kart.models.Piloto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PilotoService{

    @Autowired
    private PilotoDAO pilotoDAO;

    public List<Piloto> findAll() {
        return pilotoDAO.findAll();
    }

    public void deleteById(Integer id) {
        pilotoDAO.deleteById(id);
    }

    public void delete(Piloto piloto) {
        pilotoDAO.delete(piloto);
    }

    public Piloto findById(Integer id) {
        return pilotoDAO.findById(id).orElse(null);
    }

    public Piloto save(Piloto piloto) {
        return pilotoDAO.save(piloto);
    }
}
