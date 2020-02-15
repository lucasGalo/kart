package com.galo.Kart.controllers;


import com.galo.Kart.util.ToastrUtil;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Lucas Galo ( galo_pc@outlook.com )
 */

public class Base<T> {

    protected String URL_LIST         = "admin/list_perfil";
    protected String URL_FORM         = "admin/form_perfil";
    protected String URL_DETAILS      = "admin/list_perfil";
    protected String TITLE_LIST       = "Lista de Perfis";
    protected String TITLE_NEW        = "Novo Perfil";
    protected String TITLE_DETAILS    = "Detalhes Perfil";
    protected String TITLE_EDIT       = "Editar Perfil";
    protected String SINGLE_OBJECT    = "perfil";
    protected String FORM_OBJECT_NAME = "Perfil";
    protected String LIST_OBJECT      = "perfis";
    protected String SUCCESS_MESSAGE  = "perfis";
    protected String EDITABLE_MESSAGE = "perfis";

    protected ModelAndView postBack(String path, T objeto) {
        ModelAndView modelAndView = new ModelAndView(URL_FORM);
        if (path.contains("novo")) {
            modelAndView.addObject("FRM", TITLE_NEW);
        } else if (path.contains("editar")) {
            //((ModeloBase)objeto).objetoPersistido();
            modelAndView.addObject("FRM", TITLE_EDIT);
        }
        modelAndView.addObject(SINGLE_OBJECT, objeto);
        return modelAndView;
    }

    protected boolean samePageOrListPage(String pg) {
        return pg.contains("new");
    }

    protected ModelAndView listPage(List<T> list) {
        ModelAndView modelAndView = new ModelAndView(URL_LIST);
        modelAndView.addObject("FRM", TITLE_LIST);
        modelAndView.addObject("FORM_OBJECT_NAME", FORM_OBJECT_NAME);
        modelAndView.addObject(LIST_OBJECT, list);
        return modelAndView;
    }

    protected ModelAndView listPageSuccess(List<T> list) {
        ModelAndView modelAndView = new ModelAndView(URL_LIST);
        modelAndView.addObject("FRM", TITLE_LIST);
        modelAndView.addObject("FORM_OBJECT_NAME", FORM_OBJECT_NAME);
        modelAndView.addObject("message", SUCCESS_MESSAGE);
        modelAndView.addObject("alertClass", "alert-success");
        modelAndView.addObject(LIST_OBJECT, list);
        return modelAndView;
    }

    protected ModelAndView listPageSuccess(List<T> list, String message) {
        ModelAndView modelAndView = new ModelAndView(URL_LIST);
        modelAndView.addObject("FRM", TITLE_LIST);
        modelAndView.addObject("message", message);
        modelAndView.addObject("alertClass", "alert-success");
        modelAndView.addObject(LIST_OBJECT, list);
        return modelAndView;
    }
    protected ModelAndView detailsFormPage(T object) {
        ModelAndView modelAndView = new ModelAndView(URL_DETAILS);
        modelAndView.addObject("FRM", TITLE_DETAILS);
        modelAndView.addObject("FORM_OBJECT_NAME", FORM_OBJECT_NAME);
        modelAndView.addObject(SINGLE_OBJECT, object);
        return modelAndView;
    }

    protected ModelAndView newFormPage(T object) {
        ModelAndView modelAndView = new ModelAndView(URL_FORM);
        modelAndView.addObject("FRM", TITLE_NEW);
        modelAndView.addObject("FORM_OBJECT_NAME", FORM_OBJECT_NAME);
        modelAndView.addObject(SINGLE_OBJECT, object);
        return modelAndView;
    }

    protected ModelAndView newFormPageSuccess(T object) {
        ModelAndView modelAndView = new ModelAndView(URL_FORM);
        modelAndView.addObject("FRM", TITLE_NEW);
        modelAndView.addObject("FORM_OBJECT_NAME", FORM_OBJECT_NAME);
        modelAndView.addObject(SINGLE_OBJECT, object);
        modelAndView.addObject("message", ToastrUtil.successMessage(SUCCESS_MESSAGE) );
        //modelAndView.addObject("message", SUCCESS_MESSAGE);
        //modelAndView.addObject("alertClass", "alert-success");
        return modelAndView;
    }
    protected ModelAndView newFormPageDanger(T object, String msg) {
        ModelAndView modelAndView = new ModelAndView(URL_FORM);
        modelAndView.addObject("FRM", TITLE_NEW);
        modelAndView.addObject("FORM_OBJECT_NAME", FORM_OBJECT_NAME);
        modelAndView.addObject(SINGLE_OBJECT, object);
        modelAndView.addObject("message", ToastrUtil.successMessage(msg));
        modelAndView.addObject("alertClass", "alert-danger");
        return modelAndView;
    }

    protected ModelAndView editFormPage(T object) {
        ModelAndView modelAndView = new ModelAndView(URL_FORM);
        modelAndView.addObject("FRM", TITLE_EDIT);
        modelAndView.addObject("FORM_OBJECT_NAME", FORM_OBJECT_NAME);
        modelAndView.addObject(SINGLE_OBJECT, object);
        return modelAndView;
    }

    protected ModelAndView editFormPageSuccess(T object) {
        ModelAndView modelAndView = new ModelAndView(URL_FORM);
        modelAndView.addObject("FRM", TITLE_EDIT);
        modelAndView.addObject("FORM_OBJECT_NAME", FORM_OBJECT_NAME);
        modelAndView.addObject(SINGLE_OBJECT, object);
        modelAndView.addObject("message", ToastrUtil.successMessage(SUCCESS_MESSAGE) );
        return modelAndView;
    }

    protected ModelAndView editFormPageDanger(T object, String message) {
        ModelAndView modelAndView = new ModelAndView(URL_FORM);
        modelAndView.addObject("FRM", TITLE_NEW);
        modelAndView.addObject(SINGLE_OBJECT, object);
        //modelAndView.addObject("message", ToastrUtil.errorMessage(message) );
        modelAndView.addObject("message", message);
        modelAndView.addObject("alertClass", "alert-danger");
        return modelAndView;
    }

}
