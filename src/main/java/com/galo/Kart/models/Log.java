package com.galo.Kart.models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalTime;


@Entity
@Table(name = "log")
public class Log extends EntityBase {

    public Log() {}

    public Log(double velocidadeMedia, Timestamp tempoVolta, int nroVolta, Piloto piloto, Timestamp hora) {
        this.velocidadeMedia = velocidadeMedia;
        this.tempoVolta = tempoVolta;
        this.nroVolta = nroVolta;
        this.piloto = piloto;
        this.hora = hora;
    }

    @Column(name="velocidademedia")
    private double velocidadeMedia;

    @Column(name="tempovolta")
    private Timestamp tempoVolta;

    @Transient
    private String tempo;

    @Column(name="nrovolta")
    private int nroVolta;

    @ManyToOne
    @JoinColumn(name = "piloto")
    private Piloto piloto;

    @Column(name="hora")
    private Timestamp hora;

    @Transient
    private String horaAux;

    public double getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public void setVelocidadeMedia(double velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }

    public Timestamp getTempoVolta() {
        return tempoVolta;
    }

    public void setTempoVolta(Timestamp tempoVolta) {
        this.tempoVolta = tempoVolta;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
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

    public Timestamp getHora() {
        return hora;
    }

    public void setHora(Timestamp hora) {
        this.hora = hora;
    }

    public String getHoraAux() {
        return horaAux;
    }

    public void setHoraAux(String horaAux) {
        this.horaAux = horaAux;
    }
}
