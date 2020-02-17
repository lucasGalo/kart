package com.galo.Kart.controllers;

import com.galo.Kart.models.Log;
import com.galo.Kart.models.Piloto;
import com.galo.Kart.service.LogService;
import com.galo.Kart.service.PilotoService;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping({"/log"})
public class LogController extends Base<Log> {


    @Autowired
    private LogService logService;

    @Autowired
    private PilotoService pilotoService;


    private List<Log> listaLog = null;


    // padronização de layout
    public LogController() {
        this.URL_LIST = "admin/list/list_log";
        this.URL_FORM = "admin/form/form_arquivo";
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
        if (Objects.isNull(listaLog))
            listaLog = logService.findAll();
        return listPage(listaLog);
    }

    // Ffeito a implementação aqui no controler log para minimizar arquivos de analise
    @GetMapping("/arquivo")
    public ModelAndView arquvio() {
        ModelAndView modelAndView = new ModelAndView("admin/form/form_arquivo");
        modelAndView.addObject("FRM", "Importando arquivo");
        modelAndView.addObject("FORM_OBJECT_NAME", "Importando arquivo de log");
        modelAndView.addObject("TITLE_NEW", "Novo arquivo ");
        return modelAndView;
    }

    @GetMapping("remover")
    public ModelAndView remover(@RequestParam("id") Integer id) {
        logService.deleteById(id);
        listaLog = logService.findAll();
        return listPage(listaLog);
    }

    @PostMapping(value = "/arquivo")
    public ModelAndView arquivo(@RequestParam(value = "arquivo") MultipartFile file) {

        if (!file.getOriginalFilename().equals("")) {
            if (salvarArquivo(file)) {
                if (Objects.isNull(listaLog))
                    listaLog = logService.findAll();
                return listPageSuccess(listaLog, SUCCESS_MESSAGE);
            }
        }
        // no caso de erro, voltar para a página e emitir uma msg ao usuário
        ModelAndView modelAndView = new ModelAndView("admin/form/form_arquivo");
        modelAndView.addObject("FRM", "Importando arquivo");
        modelAndView.addObject("FORM_OBJECT_NAME", "Import arquivo de log");
        modelAndView.addObject("TITLE_NEW", "Novo arquivo ");
        modelAndView.addObject("message", "Não foi possível salvar o arquivo");
        modelAndView.addObject("alertClass", "alert-danger");
        return modelAndView;
    }

    public boolean salvarArquivo(MultipartFile file) {

        BufferedReader conteudo = null;
        String linha = "";

        String SeparadorCampo = "\\s+";
        try {
            conteudo = new BufferedReader(new InputStreamReader(file.getInputStream()));
            while ((linha = conteudo.readLine()) != null) {
                String[] campo = linha.split(SeparadorCampo);
                Piloto piloto = pilotoService.findByNroPiloto(Integer.parseInt(campo[1]));
                if (Objects.isNull(piloto)) {
                    piloto = new Piloto((Integer.parseInt(campo[1])), campo[3]);
                    pilotoService.save(piloto);
                }
                if (!Objects.isNull(piloto) && Objects.isNull(logService.findByNroVoltaAndPiloto(Integer.parseInt(campo[4]), piloto))) {
                    Log log = new Log(Double.parseDouble(campo[6].replace(",", ".")),
                            stringParaLocalTime(campo[5]),
                            Integer.parseInt(campo[4]),
                            piloto,
                            stringParaLocalTime(campo[0]));
                    logService.save(log);
                }
            }
            this.listaLog = null;

        } catch (FileNotFoundException e) {
            System.out.println("Erro não encontrou o arquivo: \n " + e.getMessage());
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Erro indice não existe: \n " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("Erro IO: \n " + e.getMessage());
            return false;
        } finally {
            if (conteudo != null) {
                try {
                    conteudo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public void reiniciarVariaveisDeClasseESalvarLog(Log log) {
        log.setHora(stringParaLocalTime(log.getHoraAux()));
        log.setTempoVolta(stringParaLocalTime(log.getTempo()));
        logService.save(log);

        this.listaLog = null;
    }

    public Timestamp stringParaLocalTime(String str) {
        try {
            Date date = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            System.out.println(formato.format(calendar.getTime()));
            if(str.length()< 12){
                str = "00:0"+str;
            }

            String ti = formato.format(calendar.getTime()) +" " + str;

            Timestamp ts;
            if (str != null) {
                ts = Timestamp.valueOf(ti);
                return ts;
            } else
                return null;
        } catch (Exception e) {

        }
        return null;
    }

}
