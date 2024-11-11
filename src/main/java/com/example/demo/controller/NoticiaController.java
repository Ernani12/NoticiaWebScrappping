package com.example.demo.controller;

import com.example.demo.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticiaController {

    private final NoticiaService noticiaService;

    @Autowired
    public NoticiaController(NoticiaService noticiaService) {
        this.noticiaService = noticiaService;
    }

    @GetMapping("/noticias")
    public String getNoticias(Model model) {
        try {
            // Adiciona as notícias ao modelo
            model.addAttribute("noticias", noticiaService.obterNoticias());
        } catch (Exception e) {
            // Log de erro simplificado
            handleException(e);
        }
        return "noticias"; // Nome do template Thymeleaf
    }

    private void handleException(Exception e) {
        // Log simples para capturar a exceção
        System.err.println("Erro ao obter notícias: " + e.getMessage());
        e.printStackTrace();
    }
}
