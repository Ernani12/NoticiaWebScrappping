package com.example.demo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoticiaTest {

    private Noticia noticia;

    @BeforeEach
    void setUp() {
        noticia = new Noticia();
    }
    
    @Test
    void dadoUmaNoticiaComCamposNulosQuandoChamarToStringDeveRetornarFormatoCorreto() {
        // Quando: Chama o método toString com campos nulos
        String resultado = noticia.toString();

        // Então: O resultado deve ser o formato esperado, com "null" nos campos
        String resultadoEsperado = "URL: null\nTítulo: null\nSubtítulo: null\nAutor: null\nData: null\nConteúdo: null\n";
        assertEquals(resultadoEsperado, resultado);
    }

}
