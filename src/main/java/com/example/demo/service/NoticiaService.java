package com.example.demo.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.example.demo.model.Noticia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoticiaService {

    private static final String BASE_URL = "https://www.infomoney.com.br/mercados/";

    public List<Noticia> obterNoticias() throws IOException, InterruptedException {
        List<Noticia> noticias = new ArrayList<>();
        Document doc = Jsoup.connect(BASE_URL).get();

        // Selecione a seção específica com a classe 'min-w-full' e o índice de filho 6
        Element section = doc.select("section.min-w-full:nth-of-type(6)").first();

        // Verifique se a seção existe
        if (section != null) {
            // Selecione todos os itens dentro da seção
            Elements articles = section.select("div[class*='basis-1/4']");



            // Extraia informações de cada artigo
            for (Element article : articles) {
                String title = article.select("h2").text();
                String link = article.select("a").attr("href");

                // Criando a Noticia com os dados extraídos
                Noticia noticia = new Noticia();
                noticia.setTitulo(title.isEmpty() ? "Sem título" : title);
                noticia.setUrl(link.isEmpty() ? "Sem link" : link);

                // Adicionando a notícia à lista
                noticias.add(noticia);
            }
        } else {
            System.out.println("Seção não encontrada");
        }

        return noticias;
    }
}
