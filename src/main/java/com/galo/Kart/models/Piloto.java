package com.galo.Kart.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="piloto")
public class Piloto  extends EntityBase{

    public Piloto() {}

    public Piloto(int nroPiloto, String nome) {
        this.nroPiloto = nroPiloto;
        this.nome = nome;
    }

    @Column(name="nropiloto")
    private int nroPiloto;

    @Column(name="nome")
    private String nome;

    @OneToMany(mappedBy = "piloto")
    @JsonIgnore
    @Column(name="listalog")
    private List<Log> listaLog;

    public int getNroPiloto() {
        return nroPiloto;
    }

    public void setNroPiloto(int nroPiloto) {
        this.nroPiloto = nroPiloto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Log> getListaLog() {
        return listaLog;
    }

    public void setListaLog(List<Log> listaLog) {
        this.listaLog = listaLog;
    }
}
