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

    /**
     * Método toString mais legível e com formato consistente
     */
    @Override
    public String toString() {
        return String.format("URL: %s%nTítulo: %s%nSubtítulo: %s%nAutor: %s%nData: %s%nConteúdo: %s%n", 
                             url, titulo, subtitulo, autor, dataPublicacao, conteudo);
    }
}
