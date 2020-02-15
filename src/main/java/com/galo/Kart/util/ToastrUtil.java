package com.galo.Kart.util;


public class ToastrUtil {

    private String tipo;
    private String texto;

    private ToastrUtil(String tipo,String texto) {
        this.tipo = tipo;
        this.texto = texto;
    }

    public static ToastrUtil infoMessage(String texto) {
        return new ToastrUtil("ALERTA",texto);
    }

    public static ToastrUtil errorMessage(String texto) {
        return new ToastrUtil("ERRO",texto);
    }

    public static ToastrUtil successMessage(String texto) {
        return new ToastrUtil("OK",texto);
    }


}

