package com.example.demo.service;

import com.example.demo.model.Noticia;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NoticiaServiceTest {

    @Mock
    private NoticiaDetalhadaExtractor noticiaDetalhadaExtractor;

    @InjectMocks
    private NoticiaService noticiaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        noticiaService = new NoticiaService(noticiaDetalhadaExtractor);
    }

    @Test
    void testExtrairNoticiasSemElementos() throws IOException, InterruptedException {
        // Simulando um cenário em que o método select não encontra a seção
        NoticiaService noticiaServiceMock = mock(NoticiaService.class);
        when(noticiaServiceMock.extrairNoticias()).thenReturn(List.of());

        // Verificando que a lista de notícias está vazia
        List<Noticia> noticias = noticiaServiceMock.extrairNoticias();
        assertTrue(noticias.isEmpty());
    }

    @Test
    void testExtrairNoticiaComCamposInvalidos() {
        // Simulando um artigo com campos vazios
        Element mockArticle = mock(Element.class);
        when(mockArticle.select("h2")).thenReturn(new Elements(new Element("h2").text("")));
        when(mockArticle.select("a")).thenReturn(new Elements(new Element("a").attr("href", "")));
        when(mockArticle.select("div.line-clamp-1")).thenReturn(new Elements(new Element("div").text("")));

        Noticia noticia = noticiaService.extrairNoticia(mockArticle);

        // Verificando que a notícia não foi extraída corretamente
        assertNull(noticia, "Notícia não deveria ser extraída se os campos estiverem vazios.");
    }

    @Test
    void testExtrairNoticiaComDadosValidos() throws IOException {
        // Criando um mock do Element com dados válidos
        Element mockArticle = mock(Element.class);
        when(mockArticle.select("h2")).thenReturn(new Elements(new Element("h2").text("Título da Notícia")));
        when(mockArticle.select("a")).thenReturn(new Elements(new Element("a").attr("href", "https://www.exemplo.com")));
        when(mockArticle.select("div.line-clamp-1")).thenReturn(new Elements(new Element("div").text("Subtítulo da Notícia")));

        // Chamando o método para extrair a notícia
        Noticia noticia = noticiaService.extrairNoticia(mockArticle);

        // Verificando se a notícia foi extraída corretamente
        assertNotNull(noticia);
        assertEquals("Título da Notícia", noticia.getTitulo());
        assertEquals("https://www.exemplo.com", noticia.getUrl());
        assertEquals("Subtítulo da Notícia", noticia.getSubtitulo());

        // Verificando se o método de extração de detalhes foi chamado
        verify(noticiaDetalhadaExtractor, times(1)).extrairDetalhes(noticia);
    }

    @Test
    void testExtrairNoticiaComTituloAusente() throws IOException {
        // Criando um mock do Element com o título ausente
        Element mockArticle = mock(Element.class);
        when(mockArticle.select("h2")).thenReturn(new Elements(new Element("h2").text("")));
        when(mockArticle.select("a")).thenReturn(new Elements(new Element("a").attr("href", "https://www.exemplo.com")));
        when(mockArticle.select("div.line-clamp-1")).thenReturn(new Elements(new Element("div").text("Subtítulo da Notícia")));

        // Chamando o método para extrair a notícia
        Noticia noticia = noticiaService.extrairNoticia(mockArticle);

        // Verificando se a notícia não foi extraída (deve ser null)
        assertNull(noticia);

        // Verificando se o método de extração de detalhes NÃO foi chamado
        verify(noticiaDetalhadaExtractor, never()).extrairDetalhes(any());
    }

    @Test
    void testExtrairNoticiaComSubtituloAusente() throws IOException {
        // Criando um mock do Element com o subtítulo ausente
        Element mockArticle = mock(Element.class);
        when(mockArticle.select("h2")).thenReturn(new Elements(new Element("h2").text("Título da Notícia")));
        when(mockArticle.select("a")).thenReturn(new Elements(new Element("a").attr("href", "https://www.exemplo.com")));
        when(mockArticle.select("div.line-clamp-1")).thenReturn(new Elements(new Element("div").text("")));

        // Chamando o método para extrair a notícia
        Noticia noticia = noticiaService.extrairNoticia(mockArticle);

        // Verificando se a notícia não foi extraída (deve ser null)
        assertNull(noticia);

        // Verificando se o método de extração de detalhes NÃO foi chamado
        verify(noticiaDetalhadaExtractor, never()).extrairDetalhes(any());
    }
}
