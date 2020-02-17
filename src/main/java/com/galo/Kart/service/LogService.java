package com.galo.Kart.service;

import com.galo.Kart.DAO.LogDAO;
import com.galo.Kart.models.Log;
import com.galo.Kart.models.Piloto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class LogService {

    @Autowired
    private LogDAO logDAO;

    public List<Log> findAll() { return logDAO.findAll(); }

    public void deleteById(Integer id) {
        logDAO.deleteById(id);
    }

    public void delete(Log log) {
        logDAO.delete(log);
    }

    public Log findById(Integer id) {
        return logDAO.findById(id).orElse(null);
    }

    public Log save(Log log) {
        return logDAO.save(log);
    }

    public Log findByNroVoltaAndPiloto(int nroVolta, Piloto piloto) {
        return logDAO.findByNroVoltaAndPiloto(nroVolta, piloto);
    }

    public List<String> resultadoCorrida() { return logDAO.resultadoCorrida(); }

    public String melhorVolta(){return logDAO.melhorVolta();}

}
