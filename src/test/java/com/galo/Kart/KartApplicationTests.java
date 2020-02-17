package com.galo.Kart;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galo.Kart.DAO.LogDAO;
import com.galo.Kart.DAO.PilotoDAO;
import com.galo.Kart.models.Log;
import com.galo.Kart.models.Piloto;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@SpringBootTest
public class KartApplicationTests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    //Injetamos o repositório para acesso ao Banco de dados
    @Autowired
    private LogDAO logDAO;
    @Autowired
    private PilotoDAO pilotoDAO;

    //Definimos o JacksonMapper responsável por converter
    private ObjectMapper MAPPER = new ObjectMapper();

    //Definimos o que será feito antes da execução do teste
    @Before
    public void setUp() throws Exception {

        //Deletamos todos os registros do banco
        logDAO.deleteAll();

    }

    @Test
    public void createLogData() {
        Date data = new Date();
        Timestamp t = new Timestamp(data.getTime());
        Piloto piloto = new Piloto(1, "Lucas");
        pilotoDAO.save(piloto);
        assertThat(piloto.getId()).isNotNull();
        assertThat(piloto.getNroPiloto()).isEqualTo(1);
        assertThat(piloto.getNome()).isEqualTo("Lucas");

        Log log = new Log(30.00, t, 1, piloto, t);

        this.logDAO.save(log);

        assertThat(log.getId()).isNotNull();
        assertThat(log.getNroVolta()).isEqualTo(1);
        assertThat(log.getVelocidadeMedia()).isEqualTo(30.00);
    }

    @Test
    public void findsByNroVolta() throws JsonProcessingException {
        List<Log> result = logDAO.findByNroVolta(1);
        List<Piloto> listaPiloto = pilotoDAO.findAll();
        assertNotNull(result);
        assertNotNull(listaPiloto);
    }
}