package com.galo.Kart.models;

import javax.persistence.*;
import java.time.LocalTime;


@Entity
@Table(name = "log")
public class Log extends EntityBase {

    public Log() {}

    public Log(double velocidadeMedia, LocalTime tempoVolta, int nroVolta, Piloto piloto, LocalTime hora) {
        this.velocidadeMedia = velocidadeMedia;
        this.tempoVolta = tempoVolta;
        this.nroVolta = nroVolta;
        this.piloto = piloto;
        this.hora = hora;
    }

    @Column(name="velocidademedia")
    private double velocidadeMedia;

    @Column(name="tempovolta")
    private LocalTime tempoVolta;

    @Transient
    private String tempo;

    @Column(name="nrovolta")
    private int nroVolta;

    @ManyToOne
    @JoinColumn(name = "piloto")
    private Piloto piloto;

    @Column(name="hora")
    private LocalTime hora;

    @Transient
    private String horaAux;


    public double getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public void setVelocidadeMedia(double velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
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

    public LocalTime getTempoVolta() {
        return tempoVolta;
    }

    public void setTempoVolta(LocalTime tempoVolta) {
        this.tempoVolta = tempoVolta;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getHoraAux() {
        return horaAux;
    }

    public void setHoraAux(String horaAux) {
        this.horaAux = horaAux;
    }
}
