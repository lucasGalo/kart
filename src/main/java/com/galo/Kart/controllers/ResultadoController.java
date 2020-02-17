package com.galo.Kart.controllers;

import com.galo.Kart.models.Resultado;
import com.galo.Kart.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Controller
@RequestMapping({"/resultado"})
public class ResultadoController extends Base<Resultado> {


    @Autowired
    private LogService logService;


    // padronização de layout
    public ResultadoController() {
        this.URL_LIST = "admin/list/list_resultado";
        this.TITLE_LIST = "Lista de resultado da corrida";
        this.SINGLE_OBJECT = "resultado";
        this.FORM_OBJECT_NAME = "Resultado da corrida";
        this.LIST_OBJECT = "resultados";
        this.SUCCESS_MESSAGE = "";
        this.EDITABLE_MESSAGE = "";
    }

    @GetMapping("/list")
    public ModelAndView listar() {
        String s = logService.melhorVolta();
        List<Resultado> listaResultado = populaResultado(logService.resultadoCorrida());
        return listPage(listaResultado).addObject("volta", s);
    }


    public List<Resultado> populaResultado(List<String> listaLog) {
        List<Resultado> lista;
        if (!Objects.isNull(listaLog)) {
            String cvsSeparadorCampo = ";";
            lista = new ArrayList<>();
            for (String s : listaLog) {
                String[] campo = s.split(cvsSeparadorCampo);
                Resultado rs = new Resultado(campo[0], campo[1], campo[2], campo[3], campo[4], campo[5], campo[6]);
                lista.add(rs);
            }
            return lista;
        } else
            return new ArrayList<>();
    }


}
