package com.example.demo.controller;

import com.example.demo.model.Noticia;
import com.example.demo.service.NoticiaExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NoticiaController {

    private final NoticiaExtractor noticiaExtractor;
    private final NoticiaExceptionHandler exceptionHandler;

    @Autowired
    public NoticiaController(NoticiaExtractor noticiaExtractor, NoticiaExceptionHandler exceptionHandler) {
        this.noticiaExtractor = noticiaExtractor;
        this.exceptionHandler = exceptionHandler;
    }

    @GetMapping("/noticias")
    public String getNoticias(Model model) {
        List<Noticia> noticias;
        try {
            noticias = noticiaExtractor.extrairNoticias();
            model.addAttribute("noticias", noticias);
        } catch (Exception e) {
            exceptionHandler.handleException(e, model);
        }
        return "noticias";
    }
}
