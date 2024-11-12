# Notícias Scraper - Spring Boot

## Descrição

A aplicação **Notícias Scraper** é um projeto desenvolvido em **Spring Boot** que realiza o scraping de notícias do site **InfoMoney**, especificamente da seção **Mercados**. O objetivo da aplicação é coletar dados como título, link, subtítulo, autor, data de publicação e conteúdo de notícias, e retorná-los através de uma API RESTful.

### Funcionalidades

- A aplicação coleta informações de artigos da seção de **Mercados** do site **InfoMoney**.
- Para cada artigo, são coletados:
  - Título
  - Subtítulo
  - Link para o artigo
  - Autor
  - Data de publicação
  - Conteúdo do artigo (limitado a 100 caracteres, com reticências se necessário)
- Exposição de um endpoint HTTP GET `/noticias` que retorna uma lista de notícias no formato JSON.

### Arquitetura

1. **Controlador** (`NoticiaController`):
   - Define o endpoint `/noticias`.
   - Chama o serviço para obter as notícias e retorna uma lista delas em formato JSON.
   
2. **Serviço** (`NoticiaService`):
   - Realiza o scraping utilizando a biblioteca **Jsoup** para extrair informações de artigos da página principal do InfoMoney.
   - Faz a requisição HTTP e analisa o conteúdo HTML para coletar os dados.
   - Limita o conteúdo a 100 caracteres, com a adição de "..." quando necessário.

3. **Modelo** (`Noticia`):
   - Contém as propriedades para armazenar os dados extraídos de cada notícia (título, subtítulo, autor, data, conteúdo e URL).
   - Implementação do método `toString` para facilitar a exibição.

## Tecnologias

- **Spring Boot 3.3.5**: Framework principal para o desenvolvimento da API RESTful.
- **Jsoup 1.17.2**: Biblioteca para parsing de HTML e scraping de conteúdo da web.
- **Lombok**: Usado para gerar automaticamente os getters e setters das classes.
- **Java 17**: Versão do JDK usada no projeto.

## Como Rodar

1. **Clonar o repositório**:

   ```bash
   https://github.com/Ernani12/NoticiaWebScrappping.git
  

2. **Compilar e Rodar a Aplicação:

   ```bash
   mvn spring-boot:run

3. ** Testar a API:



# Notícias Scraper - Spring Boot OBSERVAÇAO
   ```bash
  A  requisiçao  que voces pediram para o bootao CARREGAR MAIS, pode
estar modoficada e mesmo, consultando pelo Dev Tool do Browser
e mostrando  uma requisiçao do TIPO POST , ela bloquea de nao deixa
fazer requisiçao para endereço. Mas o codigo era so adicionar o metodo:

//removido porque tem problema de requisiçao no link POST do Botao Carregar Mais..
 public void carregarMaisNoticias() {
        // Realiza 3 requisições antes de carregar as notícias
        for (int i = 1; i <= 3; i++) {
            System.out.println("Requisição " + i + ":");
            try {
                // Realiza uma requisição POST com dados fictícios
                Connection.Response response = Jsoup.connect("https://www.infomoney.com.br/wp-json/infomoney/v1/cards")
                        .ignoreContentType(true)
                        .method(Connection.Method.POST)  // Alterando para POST
                        .header("Content-Type", "application/json")  // Ajuste no header
                        .header("Authorization", "Bearer <seu-token>")  // Inclua o token de autenticação, se necessário
                        .header("Origin", "https://www.infomoney.com.br")  // Origem para CORS
                        .requestBody("{\"key\":\"value\"}")  // Dados fictícios no corpo (substitua conforme necessário)
                        .execute();
    
                System.out.println("Status Code: " + response.statusCode());
                System.out.println("Response: " + response.body());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\n-------------------------\n");
        }
    }




   
