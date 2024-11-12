package com.example.demo.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Component;

@Component
public class NoticiaExceptionHandler {

    public void handleException(Exception e, Model model) {
        // Lógica de tratamento de exceções
        model.addAttribute("erro", "Erro ao carregar notícias. Tente novamente.");
        System.err.println("Erro ao obter notícias: " + e.getMessage());
        e.printStackTrace();
    }
}
