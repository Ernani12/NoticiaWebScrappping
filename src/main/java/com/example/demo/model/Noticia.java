package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Noticia {

    @NotNull(message = "URL não pode ser nulo")
    private String url;

    @NotNull(message = "Título não pode ser nulo")
    private String titulo;

    @NotNull(message = "Subtítulo não pode ser nulo")
    private String subtitulo;

    @NotNull(message = "Autor não pode ser nulo")
    private String autor;

    @NotNull(message = "Data de publicação não pode ser nula")
    private String dataPublicacao;

    @NotNull(message = "Conteúdo não pode ser nulo")
    private String conteudo;

    @Override
    public String toString() {
        return String.format("URL: %s%nTítulo: %s%nSubtítulo: %s%nAutor: %s%nData: %s%nConteúdo: %s%n", 
                             url, titulo, subtitulo, autor, dataPublicacao, conteudo);
    }
}


