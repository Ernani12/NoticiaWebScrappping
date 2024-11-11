package com.example.demo.service;

import com.example.demo.model.Noticia;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoticiaService implements NoticiaExtractor {

    private static final String BASE_URL = "https://www.infomoney.com.br/mercados/";

    private final NoticiaDetalhadaExtractor noticiaDetalhadaExtractor;

    public NoticiaService(NoticiaDetalhadaExtractor noticiaDetalhadaExtractor) {
        this.noticiaDetalhadaExtractor = noticiaDetalhadaExtractor;
    }

    @Override
    public List<Noticia> extrairNoticias() throws IOException, InterruptedException {
        List<Noticia> noticias = new ArrayList<>();
        Document doc = Jsoup.connect(BASE_URL).get();

        Element section = doc.select("div.px-0").first();
        if (section == null) {
            System.out.println("Seção não encontrada");
            return noticias;
        }

        Elements articles = section.select("div[class*='basis-1/4']");
        for (Element article : articles) {
            Noticia noticia = extrairNoticia(article);
            if (noticia != null) {
                noticias.add(noticia);
            }
        }

        return noticias;
    }

    private Noticia extrairNoticia(Element article) {
        String title = article.select("h2").text();
        String link = article.select("a").attr("href");
        String subtitle = article.select("div.line-clamp-1").text();

        if (title.isEmpty() || link.isEmpty() || subtitle.isEmpty()) {
            return null;
        }

        Noticia noticia = new Noticia();
        noticia.setTitulo(title);
        noticia.setUrl(link);
        noticia.setSubtitulo(subtitle);

        try {
            // Delegando a extração dos detalhes da notícia para outra classe
            noticiaDetalhadaExtractor.extrairDetalhes(noticia);
        } catch (IOException e) {
            System.out.println("Erro ao acessar a URL da notícia: " + link);
        }

        return noticia;
    }
}
