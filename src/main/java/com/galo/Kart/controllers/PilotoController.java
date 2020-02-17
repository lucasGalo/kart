package com.galo.Kart.controllers;

import com.galo.Kart.models.Piloto;
import com.galo.Kart.service.PilotoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping({"/piloto"})
public class PilotoController extends Base<Piloto> {

    @Autowired
    private PilotoService pilotoService;

 
    private List<Piloto> listaPiloto = null;
 

    // padronização de layout
    public PilotoController() {
        this.URL_LIST = "admin/list/list_piloto";
        this.URL_FORM = "admin/form/form_piloto";
        this.TITLE_LIST = "Lista de pilotos";
        this.TITLE_NEW = "Nova piloto";
        this.TITLE_EDIT = "Editar piloto";
        this.SINGLE_OBJECT = "piloto";
        this.FORM_OBJECT_NAME = "Piloto";
        this.LIST_OBJECT = "pilotos";
        this.SUCCESS_MESSAGE = "Piloto cadastrada com sucesso!";
        this.EDITABLE_MESSAGE = "Piloto alterado com sucesso!";
    }

    @GetMapping(value = "/novo")
    public ModelAndView Novo() {
        Piloto piloto = new Piloto();
        return newFormPage(piloto);
    }

    @PostMapping(value = "/novo")
    public ModelAndView Novo(Piloto piloto) {
        reiniciarVariaveisDeClasseESalvarPiloto(piloto);
        return listPageSuccess(pilotoService.findAll(), SUCCESS_MESSAGE);
    }

    @GetMapping("/list")
    public ModelAndView listar() {
        if (Objects.isNull(listaPiloto))
            listaPiloto = pilotoService.findAll();
        return listPage(listaPiloto);
    }

    @GetMapping("editar")
    public ModelAndView editar(@RequestParam("id") Integer id) {
        Piloto piloto = pilotoService.findById(id);
        if (!Objects.isNull(piloto)) {
            piloto.setPersistido(true);
            return editFormPage(piloto);
        }
        if (Objects.isNull(listaPiloto))
            listaPiloto = pilotoService.findAll();
        return listPageSuccess(listaPiloto, "Não foi possível editar esse item");
    }

    @PostMapping("editar")
    public ModelAndView editar(Piloto piloto) {
        reiniciarVariaveisDeClasseESalvarPiloto(piloto);
        listaPiloto = pilotoService.findAll();
        return listPageSuccess(listaPiloto, EDITABLE_MESSAGE);
    }

    @GetMapping("remover")
    public ModelAndView remover(@RequestParam("id") Integer id) {
        pilotoService.deleteById(id);
        listaPiloto = pilotoService.findAll();
        return listPage(listaPiloto);
    }

    public void reiniciarVariaveisDeClasseESalvarPiloto(Piloto piloto) {
        pilotoService.save(piloto);
        this.listaPiloto = null;
    }
}
