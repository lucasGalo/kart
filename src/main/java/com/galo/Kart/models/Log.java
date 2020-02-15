package com.galo.Kart.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="log")
public class Log extends EntityBase{

    public Log() {}

    public Log(double velocidadeMedia, Date tempoVolta, int nroVolta, Piloto piloto, Date hora) {
        this.velocidadeMedia = velocidadeMedia;
        this.tempoVolta = tempoVolta;
        this.nroVolta = nroVolta;
        this.piloto = piloto;
        this.hora = hora;
    }

    @Column(name="velocidademedia")
    private double velocidadeMedia;

    @Column(name="tempovolta")
    @Temporal(TemporalType.TIME) // HH:mm:ss
    private Date tempoVolta;

    @Column(name="nrovolta")
    private int nroVolta;

    @ManyToOne
    @JoinColumn(name = "piloto")
    private Piloto piloto;

    @Column(name="hora")
    @Temporal(TemporalType.TIME) // HH:mm:ss
    private Date hora;

    public double getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public void setVelocidadeMedia(double velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }

    public Date getTempoVolta() {
        return tempoVolta;
    }

    public void setTempoVolta(Date tempoVolta) {
        this.tempoVolta = tempoVolta;
    }

    public int getNroVolta() {
        return nroVolta;
    }

    public void setNroVolta(int nroVolta) {
        this.nroVolta = nroVolta;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
}
