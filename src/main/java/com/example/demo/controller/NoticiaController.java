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

    // Injeção de dependência da interface NoticiaExtractor
    @Autowired
    public NoticiaController(NoticiaExtractor noticiaExtractor) {
        this.noticiaExtractor = noticiaExtractor;
    }

    @GetMapping("/noticias")
    public String getNoticias(Model model) {
        List<Noticia> noticias;
        try {
            // Chama o serviço para obter as notícias
            noticias = noticiaExtractor.extrairNoticias();
            // Adiciona as notícias ao modelo
            model.addAttribute("noticias", noticias);
        } catch (Exception e) {
            // Caso ocorra algum erro, captura e exibe na view
            model.addAttribute("erro", "Erro ao carregar notícias. Tente novamente.");
            handleException(e);
        }
        return "noticias"; // Nome do template Thymeleaf
    }

    // Método para lidar com exceções
    private void handleException(Exception e) {
        // Log de erro para fins de depuração
        System.err.println("Erro ao obter notícias: " + e.getMessage());
        e.printStackTrace();
    }
}
