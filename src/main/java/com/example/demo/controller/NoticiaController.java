package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.NoticiaService;

@Controller
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    @GetMapping("/noticias")
    public String getNoticias(Model model) {
        try {
            model.addAttribute("noticias", noticiaService.obterNoticias());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "noticias"; // Nome do template Thymeleaf
    }
}
