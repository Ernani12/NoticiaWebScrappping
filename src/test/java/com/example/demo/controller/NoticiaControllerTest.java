package com.example.demo.controller;


import com.example.demo.model.Noticia;
import com.example.demo.service.NoticiaExtractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


class NoticiaControllerTest {

    private NoticiaController noticiaController;

    @Mock
    private NoticiaExtractor noticiaExtractor;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        noticiaController = new NoticiaController(noticiaExtractor, null);
    }

    @Test
    void testGetNoticias() throws Exception {
        // Mocking uma lista de notícias
        Noticia noticia = new Noticia();
        noticia.setTitulo("Notícia 1");
        when(noticiaExtractor.extrairNoticias()).thenReturn(List.of(noticia));

        // Chamar o método
        String viewName = noticiaController.getNoticias(model);

        // Verificar se a lista de notícias foi adicionada ao modelo
        verify(model).addAttribute("noticias", List.of(noticia));

        // Verificar se o método retorna o nome da view correta
        assertEquals("noticias", viewName);
    }
}


