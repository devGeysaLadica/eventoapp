package com.eventoapp.controller;

import com.eventoapp.models.Evento;
import com.eventoapp.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventoController {

    @Autowired  //injeção de dependencias ou seja toda vez usarmos a interface será criado uma nova instância automaticamente
    private EventoRepository er;  //er = evento repository

    @RequestMapping(value="/cadastrarevento", method=RequestMethod.GET) //get porque ele irá retornar o form
    public String form(){
        return "evento/formEvento";
    }

    @RequestMapping(value="/cadastrarevento", method=RequestMethod.POST)
    public String form(Evento evento){  //Recebe Evento.java (esta importado) como "evento" para salvar abaixo

        er.save(evento); //acessa a interface er (EventoRepository) e usa o metodo save (isso é possivel que essa interface extend CRUD) e salva o evento

        return "redirect:/cadastrarevento";
    }

    @RequestMapping("/eventos")
    public ModelAndView listaEventos(){
        ModelAndView mv = new ModelAndView("/index");
        Iterable<Evento> eventos = er.findAll();
        mv.addObject("eventos", eventos);

        return  mv;
    }
}
