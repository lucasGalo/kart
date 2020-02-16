package com.galo.Kart.models;

import java.time.LocalTime;

public class Resultado {

    public Resultado() {}

    public Resultado(String posicao, String pilotoCodigo, String pilotoNome, String qtdVoltas, String tempo, String velocidadeMedia) {
        this.posicao = posicao;
        this.pilotoCodigo = pilotoCodigo;
        this.pilotoNome = pilotoNome;
        this.qtdVoltas = qtdVoltas;
        this.tempo = tempo;
        this.velocidadeMedia = velocidadeMedia;
    }

    private String posicao;

    private String pilotoCodigo;

    private String pilotoNome;

    private String qtdVoltas;

    private String tempo;

    private String velocidadeMedia;

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getPilotoCodigo() {
        return pilotoCodigo;
    }

    public void setPilotoCodigo(String pilotoCodigo) {
        this.pilotoCodigo = pilotoCodigo;
    }

    public String getPilotoNome() {
        return pilotoNome;
    }

    public void setPilotoNome(String pilotoNome) {
        this.pilotoNome = pilotoNome;
    }

    public String getQtdVoltas() {
        return qtdVoltas;
    }

    public void setQtdVoltas(String qtdVoltas) {
        this.qtdVoltas = qtdVoltas;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getVelocidadeMedia() {
        return velocidadeMedia;
    }

    public void setVelocidadeMedia(String velocidadeMedia) {
        this.velocidadeMedia = velocidadeMedia;
    }
}
