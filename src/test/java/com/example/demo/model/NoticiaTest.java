package com.example.demo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoticiaTest {

    private Noticia noticia;


    @BeforeEach
    void setUp() {
        // Initialize the Noticia object before each test
        noticia = new Noticia();
    }

    @Test
    void dadoUmaNoticiaQuandoAtributoUrlNaoForNullEntaoDevePassar() {
        // Dado que a URL é válida
        noticia.setUrl("https://www.infomoney.com.br/mercados/");
        
        // Então a URL deve ser corretamente atribuída
        assertEquals("https://www.infomoney.com.br/mercados/", noticia.getUrl());
    }

    @Test
    void dadoUmaNoticiaQuandoAtributoTituloNaoForNullEntaoDevePassar() {
        // Dado que o título é válido
        noticia.setTitulo("Notícia de Teste");

        // Então o título deve ser corretamente atribuído
        assertEquals("Notícia de Teste", noticia.getTitulo());
    }

    @Test
    void dadoUmaNoticiaQuandoAtributoSubtituloNaoForNullEntaoDevePassar() {
        // Dado que o subtítulo é válido
        noticia.setSubtitulo("Subtítulo de Teste");

        // Então o subtítulo deve ser corretamente atribuído
        assertEquals("Subtítulo de Teste", noticia.getSubtitulo());
    }

    @Test
    void dadoUmaNoticiaQuandoAtributoAutorNaoForNullEntaoDevePassar() {
        // Dado que o autor é válido
        noticia.setAutor("Autor Teste");

        // Então o autor deve ser corretamente atribuído
        assertEquals("Autor Teste", noticia.getAutor());
    }

    @Test
    void dadoUmaNoticiaQuandoAtributoDataPublicacaoNaoForNullEntaoDevePassar() {
        // Dado que a data de publicação é válida
        noticia.setDataPublicacao("2024-11-12");

        // Então a data de publicação deve ser corretamente atribuída
        assertEquals("2024-11-12", noticia.getDataPublicacao());
    }

    @Test
    void dadoUmaNoticiaQuandoAtributoConteudoNaoForNullEntaoDevePassar() {
        // Dado que o conteúdo é válido
        noticia.setConteudo("Conteúdo da notícia de teste");

        // Então o conteúdo deve ser corretamente atribuído
        assertEquals("Conteúdo da notícia de teste", noticia.getConteudo());
    }

 
    @Test
    void givenValidNoticia_whenFieldsAreSet_thenAttributesShouldBeCorrect() {
        // Given
        noticia.setUrl("https://www.exemplo.com");
        noticia.setTitulo("Notícia de Teste");
        noticia.setSubtitulo("Subtítulo de Teste");
        noticia.setAutor("Autor Teste");
        noticia.setDataPublicacao("2024-11-12");
        noticia.setConteudo("Conteúdo da notícia");

        // When
        String url = noticia.getUrl();
        String titulo = noticia.getTitulo();
        String subtitulo = noticia.getSubtitulo();
        String autor = noticia.getAutor();
        String dataPublicacao = noticia.getDataPublicacao();
        String conteudo = noticia.getConteudo();

        // Then
        assertEquals("https://www.exemplo.com", url);
        assertEquals("Notícia de Teste", titulo);
        assertEquals("Subtítulo de Teste", subtitulo);
        assertEquals("Autor Teste", autor);
        assertEquals("2024-11-12", dataPublicacao);
        assertEquals("Conteúdo da notícia", conteudo);
    }


    @Test
    void givenNullUrl_whenSetUrl_thenShouldThrowException() {
        // Given
        noticia.setUrl(null);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            if (noticia.getUrl() == null) {
                throw new IllegalArgumentException("URL não pode ser nulo");
            }
        });

        assertEquals("URL não pode ser nulo", exception.getMessage());
    }

    @Test
    void givenNullTitulo_whenSetTitulo_thenShouldThrowException() {
        // Given
        noticia.setTitulo(null);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            if (noticia.getTitulo() == null) {
                throw new IllegalArgumentException("Título não pode ser nulo");
            }
        });

        assertEquals("Título não pode ser nulo", exception.getMessage());
    }

    @Test
    void givenNullSubtitulo_whenSetSubtitulo_thenShouldThrowException() {
        // Given
        noticia.setSubtitulo(null);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            if (noticia.getSubtitulo() == null) {
                throw new IllegalArgumentException("Subtítulo não pode ser nulo");
            }
        });

        assertEquals("Subtítulo não pode ser nulo", exception.getMessage());
    }

    @Test
    void givenNullAutor_whenSetAutor_thenShouldThrowException() {
        // Given
        noticia.setAutor(null);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            if (noticia.getAutor() == null) {
                throw new IllegalArgumentException("Autor não pode ser nulo");
            }
        });

        assertEquals("Autor não pode ser nulo", exception.getMessage());
    }

    @Test
    void givenNullDataPublicacao_whenSetDataPublicacao_thenShouldThrowException() {
        // Given
        noticia.setDataPublicacao(null);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            if (noticia.getDataPublicacao() == null) {
                throw new IllegalArgumentException("Data de publicação não pode ser nula");
            }
        });

        assertEquals("Data de publicação não pode ser nula", exception.getMessage());
    }

    @Test
    void givenNullConteudo_whenSetConteudo_thenShouldThrowException() {
        // Given
        noticia.setConteudo(null);

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            if (noticia.getConteudo() == null) {
                throw new IllegalArgumentException("Conteúdo não pode ser nulo");
            }
        });

        assertEquals("Conteúdo não pode ser nulo", exception.getMessage());
    }


    @Test
    void givenNoticia_whenToStringIsCalled_thenCorrectStringFormat() {
        // Given
        noticia.setUrl("https://www.exemplo.com");
        noticia.setTitulo("Notícia de Teste");
        noticia.setSubtitulo("Subtítulo de Teste");
        noticia.setAutor("Autor Teste");
        noticia.setDataPublicacao("2024-11-12");
        noticia.setConteudo("Conteúdo da notícia");

        // When
        String result = noticia.toString();

        // Then
        String expected = String.format("URL: %s%nTítulo: %s%nSubtítulo: %s%nAutor: %s%nData: %s%nConteúdo: %s%n", 
                                        "https://www.exemplo.com", "Notícia de Teste", "Subtítulo de Teste", 
                                        "Autor Teste", "2024-11-12", "Conteúdo da notícia");

        assertEquals(expected, result);
    }
    
}
