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

        // Seleciona a seção principal com a classe 'px-0 md:px-6'
        Element section = doc.select("div.px-0").first();

        // Verifica se a seção existe
        if (section != null) {
            // Seleciona todos os itens dentro da seção
            Elements articles = section.select("div[class*='basis-1/4']");

            // Extrai informações de cada artigo
            for (Element article : articles) {
                String title = article.select("h2").text();
                String link = article.select("a").attr("href");
                
                // Seleciona o subtítulo a partir da div com classe "line-clamp-1"
                String subtitle = article.select("div.line-clamp-1").text();
                
                // Seleciona a data de publicação da div com classe "mt-3 text-wl-neutral-500"
                String dataPublicacao = article.select("div.mt-3.text-wl-neutral-500").text();

                // Verifica se título, link, subtítulo e data de publicação estão presentes antes de criar a notícia
                if (title != null && !title.isEmpty() && link != null && !link.isEmpty() && subtitle != null && !subtitle.isEmpty() && dataPublicacao != null && !dataPublicacao.isEmpty()) {

                    Noticia noticia = new Noticia();
                    noticia.setTitulo(title);
                    noticia.setUrl(link);
                    noticia.setSubtitulo(subtitle); // Ajuste para definir o subtítulo
                    noticia.setDataPublicacao(dataPublicacao); // Ajuste para definir a data de publicação

                    // Adiciona a notícia à lista
                    noticias.add(noticia);
                }
            }
        } else {
            System.out.println("Seção não encontrada");
        }

        return noticias;
    }
}
