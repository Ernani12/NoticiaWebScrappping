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

        // Seleciona a seção principal da página
        Element section = doc.select("div.px-0").first();
        if (section == null) {
            System.out.println("Seção não encontrada");
            return noticias;
        }

        // Extrai os artigos presentes na seção
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
        // Extrai o título, link e subtítulo
        String title = article.select("h2").text();
        String link = article.select("a").attr("href");
        String subtitle = article.select("div.line-clamp-1").text();

        // Verifica se os dados essenciais estão presentes
        if (title.isEmpty() || link.isEmpty() || subtitle.isEmpty()) {
            return null;
        }

        // Cria uma nova instância de Notícia
        Noticia noticia = new Noticia();
        noticia.setTitulo(title);
        noticia.setUrl(link);
        noticia.setSubtitulo(subtitle);

        // Acessa o conteúdo detalhado da página da notícia
        try {
            Document noticiaDoc = Jsoup.connect(link).get();
            noticia.setAutor(obterAutor(noticiaDoc));
            noticia.setDataPublicacao(obterDataPublicacao(noticiaDoc));
            noticia.setConteudo(obterConteudo(noticiaDoc));
        } catch (IOException e) {
            System.out.println("Erro ao acessar a URL da notícia: " + link);
        }

        return noticia;
    }

    private String obterAutor(Document noticiaDoc) {
        return noticiaDoc.select("p.flex.flex-wrap.items-center").text();
    }

    private String obterDataPublicacao(Document noticiaDoc) {
        return noticiaDoc.select("p.im-mob-core-description.text-wl-neutral-600").text();
    }

    private String obterConteudo(Document noticiaDoc) {
        String conteudoCompleto = noticiaDoc.select("article.im-article.clear-fix").text();
        return conteudoCompleto.length() > 100
                ? conteudoCompleto.substring(0, 100) + "..."
                : conteudoCompleto;
    }
}
