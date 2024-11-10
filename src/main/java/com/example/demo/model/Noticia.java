package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Noticia {
    private String url;
    private String titulo;
    private String subtitulo;
    private String autor;
    private String dataPublicacao;
    private String conteudo;

    // Getters e setters
 
    @Override
    public String toString() {
        return "URL: " + url + "\n" +
               "Título: " + titulo + "\n" +
               "Subtítulo: " + subtitulo + "\n" +
               "Autor: " + autor + "\n" +
               "Data: " + dataPublicacao + "\n" +
               "Conteúdo: " + conteudo + "\n";
    }
}
