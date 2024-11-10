package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Noticia;
import com.example.demo.service.NoticiaService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/noticias")
public class NoticiaController {

    @Autowired
    private NoticiaService noticiaService;

    // Endpoint para retornar as notícias
    @GetMapping
    public ResponseEntity<List<Noticia>> getNoticias() {
        try {
            // Chama o método do serviço para obter as notícias
            List<Noticia> noticias = noticiaService.obterNoticias();
            
            // Verifica se a lista está vazia
            if (noticias.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(noticias);
            }

            // Retorna as notícias encontradas com status 200 (OK)
            return ResponseEntity.ok(noticias);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            // Retorna erro caso haja exceção
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(List.of()); // Retorna uma lista vazia em caso de erro
        }
    }
}
