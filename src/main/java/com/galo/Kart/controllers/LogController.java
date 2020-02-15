package com.galo.Kart.controllers;

import com.galo.Kart.models.Log;
import com.galo.Kart.service.LogService;
import com.galo.Kart.util.ToastrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping({"/log"})
public class LogController extends Base<Log> {


    @Autowired
    private LogService logService;

    // Variaveis de instância, evita consulta excessiva ao banco de dados
    private List<Log> listaAllLogs = null;

    // padronização de layout
    public LogController() {
        this.URL_LIST = "admin/list/list_log";
        this.URL_FORM = "admin/form/form_log";
        this.TITLE_LIST = "Lista de logs";
        this.TITLE_NEW = "Nova log";
        this.TITLE_EDIT = "Editar log";
        this.SINGLE_OBJECT = "log";
        this.FORM_OBJECT_NAME = "Log";
        this.LIST_OBJECT = "logs";
        this.SUCCESS_MESSAGE = "Log cadastrado com sucesso!";
        this.EDITABLE_MESSAGE = "Log alterado com sucesso!";
    }

    @GetMapping(value = "/novo")
    public ModelAndView Novo() {
        Log log = new Log();
        return newFormPage(log);
    }

    @PostMapping(value = "/novo")
    public ModelAndView Novo(Log log) {
        reiniciarVariaveisDeClasseESalvarLog(log);
        return listPageSuccess(logService.findAll(), SUCCESS_MESSAGE);
    }

    @GetMapping("/list")
    public ModelAndView listar() {
        if (Objects.isNull(listaAllLogs))
            listaAllLogs = logService.findAll();
        return listPage(listaAllLogs);
    }

 
    @GetMapping("editar")
    public ModelAndView editar(@RequestParam("id") Integer id) {
        Log log = logService.findById(id);
        if (!Objects.isNull(log)) {

            return editFormPage(log);
        }
        if (Objects.isNull(listaAllLogs))
            listaAllLogs = logService.findAll();
        return listPageSuccess(listaAllLogs, "Não foi possível editar esse item");
    }

    @PostMapping("editar")
    public ModelAndView editar(Log log) {
        reiniciarVariaveisDeClasseESalvarLog(log);
        listaAllLogs = logService.findAll();
        return listPageSuccess(listaAllLogs, EDITABLE_MESSAGE);
    }

    @GetMapping("remover")
    public ModelAndView remover(@RequestParam("id") Integer id) {
        logService.deleteById(id);
        listaAllLogs = logService.findAll();
        return listPage(listaAllLogs);
    }

    public void reiniciarVariaveisDeClasseESalvarLog(Log log) {
        logService.save(log);
        this.listaAllLogs = null;
    }

}
