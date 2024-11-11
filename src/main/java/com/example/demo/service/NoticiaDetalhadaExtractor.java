package com.example.demo.service;

import com.example.demo.model.Noticia;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class NoticiaDetalhadaExtractor {

    public void extrairDetalhes(Noticia noticia) throws IOException {
        Document noticiaDoc = Jsoup.connect(noticia.getUrl()).get();
        noticia.setAutor(obterAutor(noticiaDoc));
        noticia.setDataPublicacao(obterDataPublicacao(noticiaDoc));
        noticia.setConteudo(obterConteudo(noticiaDoc));
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
