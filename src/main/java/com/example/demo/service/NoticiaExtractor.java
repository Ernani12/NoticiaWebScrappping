package com.example.demo.service;


import com.example.demo.model.Noticia;
import java.io.IOException;
import java.util.List;

public interface NoticiaExtractor {
    List<Noticia> extrairNoticias() throws IOException, InterruptedException;
}
