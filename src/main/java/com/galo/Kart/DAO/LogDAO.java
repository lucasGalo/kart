package com.galo.Kart.DAO;

import com.galo.Kart.models.Log;
import com.galo.Kart.models.Piloto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Iterator;
import java.util.List;

public interface LogDAO extends CrudRepository<Log, Integer> {
    List<Log> findAll();

    List<Log> findByNroVolta(int volta);

    Log save(Log log);

    Log findByNroVoltaAndPiloto(int nroVolta, Piloto piloto);

    @Query( value = " " +
            "    select ROW_NUMBER() OVER (ORDER BY piloto) ||';'|| p.nropiloto||';'||p.nome||';'|| d.nrovolta||';'||d.tempovolta||';'||d.velocidademedia||';'||d.melhorVolta  " +
            "from  " +
            "    ( SELECT a.piloto,  " +
            "        (SELECT max(b.nrovolta)  " +
            "            FROM log b  " +
            "            WHERE b.piloto = a.piloto  " +
            "         ) AS nrovolta,  " +
            "        (SELECT SUM(EXTRACT (minutes from c.tempovolta))  " +
            "            FROM log c  " +
            "            WHERE c.piloto = a.piloto  " +
            "            ) AS tempovolta,  " +
            "            (SELECT avg(h.velocidademedia)  " +
            "            FROM log h  " +
            "            WHERE h.piloto = a.piloto  " +
            "            ) AS velocidademedia,  " +
            "            (select h.nrovolta   " +
            "            from log h where h.tempovolta = (SELECT min(c.tempovolta)  " +
            "            FROM log c  " +
            "                WHERE c.piloto = a.piloto  " +
            "              )) AS melhorVolta  " +
            "    FROM log a GROUP BY a.piloto order by tempovolta,nrovolta desc) d,  " +
            "    Piloto p  " +
            "    where p.id = d.piloto;" +
            "", nativeQuery = true)
    List<String> resultadoCorrida();


    @Query( value = " " +
            "select 'Melhor volta n°: '||a.nrovolta||', tempo: '|| to_char(a.tempovolta, 'HH24:MI:SS:MS')||' Código piloto: '|| p.nropiloto ||', nome: '|| p.nome  " +
            "from log a,  " +
            "piloto p  " +
            "where  a.piloto = p.id  " +
            "and a.tempovolta = (  " +
            "      SELECT  min(c.tempovolta)  " +
            "            FROM log c  " +
            "            )", nativeQuery = true)
    String melhorVolta();

}
