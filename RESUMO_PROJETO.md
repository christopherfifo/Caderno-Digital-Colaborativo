# Resumo do Projeto - Caderno Digital Colaborativo

## Ideia geral

O projeto foi montado como uma API REST em Spring Boot para atender o MVP descrito nos PDFs do Tema 9. A aplicação permite cadastrar usuários, publicar mídias de aula, comentar, responder comentários, avaliar conteúdos, publicar materiais complementares e visualizar ranking de engajamento.

A estrutura foi feita de forma simples, parecida com um projeto acadêmico:

- `model`: entidades JPA que viram tabelas no banco.
- `repository`: interfaces do Spring Data JPA para acessar o banco.
- `dto`: objetos usados para receber e devolver dados na API.
- `controller`: endpoints REST.
- `service`: regra simples de pontuação.
- `exception`: tratamento básico de erros.

O banco usado é o H2 em memória, configurado em `src/main/resources/application.properties`. O Hibernate cria as tabelas automaticamente com `spring.jpa.hibernate.ddl-auto=update`.

## Organização do banco

### Tabela `usuarios`

Guarda alunos e professores que usam o sistema.

Campos principais:

- `id`: chave primária.
- `nome`: nome do usuário.
- `email`: e-mail do usuário.
- `tipo`: `ALUNO` ou `PROFESSOR`.
- `pontos`: pontuação usada no ranking.

Por que existe: todas as ações importantes precisam saber quem foi o autor para organizar colaboração e gamificação.

### Tabela `midias`

Guarda fotos e vídeos enviados das aulas.

Campos principais:

- `id`: chave primária.
- `titulo`: título do conteúdo.
- `descricao`: descrição opcional.
- `url_arquivo`: caminho ou link do arquivo enviado.
- `tipo`: `FOTO` ou `VIDEO`.
- `data_hora_aula`: data e horário da aula.
- `disciplina`: disciplina relacionada.
- `professor_responsavel`: professor da aula.
- `criado_em`: data de cadastro no sistema.
- `autor_id`: usuário que enviou a mídia.

Por que existe: atende as histórias de upload e classificação, permitindo encontrar conteúdo por aula, disciplina, professor e data.

### Tabela `comentarios`

Guarda comentários e respostas.

Campos principais:

- `id`: chave primária.
- `texto`: conteúdo do comentário.
- `link_complementar`: link opcional compartilhado no comentário.
- `criado_em`: data do comentário.
- `midia_id`: mídia comentada.
- `autor_id`: usuário que comentou.
- `comentario_pai_id`: comentário original, usado quando o registro é uma resposta.

Por que existe: o mesmo modelo serve para comentários normais e respostas. Quando `comentario_pai_id` é nulo, é um comentário principal; quando tem valor, é uma resposta.

### Tabela `avaliacoes`

Guarda avaliações de mídias e comentários.

Campos principais:

- `id`: chave primária.
- `nota`: nota de 1 a 5.
- `comentario`: texto opcional da avaliação.
- `tipo`: `MIDIA` ou `COMENTARIO`.
- `criado_em`: data da avaliação.
- `usuario_id`: usuário que avaliou.
- `midia_id`: mídia avaliada, quando o tipo for `MIDIA`.
- `comentario_id`: comentário avaliado, quando o tipo for `COMENTARIO`.

Por que existe: separa a avaliação da mídia e do comentário, mas mantém tudo em uma tabela simples.

### Tabela `materiais_complementares`

Guarda materiais extras relacionados a uma mídia.

Campos principais:

- `id`: chave primária.
- `titulo`: título do material.
- `descricao`: descrição opcional.
- `link`: link do material.
- `criado_em`: data de cadastro.
- `midia_id`: mídia relacionada.
- `autor_id`: usuário que publicou o material.

Por que existe: atende a história de materiais complementares sem misturar esses links com o upload principal da aula.

## Pontuação e ranking

A pontuação fica centralizada em `PontuacaoService`.

Regras usadas:

- Enviar mídia: `+10` pontos.
- Comentar ou responder: `+5` pontos.
- Publicar material complementar: `+7` pontos.
- Fazer avaliação: `+2` pontos.
- Receber nota 4 ou 5 em mídia ou comentário: `+3` pontos para o autor avaliado.

Por que funciona assim: a regra é simples, mas já incentiva as ações descritas no PDF: postar conteúdo, colaborar em comentários, compartilhar materiais, avaliar e produzir contribuições úteis.

## Endpoints

### Usuários

`POST /api/usuarios`

Cadastra aluno ou professor.

Exemplo:

```json
{
  "nome": "Diego",
  "email": "diego@ifsp.edu.br",
  "tipo": "ALUNO"
}
```

`GET /api/usuarios`

Lista todos os usuários.

`GET /api/usuarios/{id}`

Busca um usuário pelo id.

`PUT /api/usuarios/{id}`

Atualiza os dados de um usuário (nome, e-mail e tipo).

`DELETE /api/usuarios/{id}`

Remove um usuário do sistema.

`GET /api/usuarios/ranking`

Lista os usuários ordenados por pontuação global, do maior para o menor.

`GET /api/usuarios/ranking?disciplina=web`

Lista o ranking de usuários que tiveram atividades (mídias ou comentários) em uma disciplina específica.

### Mídias

`POST /api/midias`

Cadastra uma foto ou vídeo de aula e soma 10 pontos ao autor.

Exemplo:

```json
{
  "titulo": "Lousa de JPA",
  "descricao": "Explicação sobre entidades e repositories",
  "urlArquivo": "https://exemplo.com/lousa-jpa.jpg",
  "tipo": "FOTO",
  "dataHoraAula": "2026-06-16T19:00:00",
  "disciplina": "Desenvolvimento Web",
  "professorResponsavel": "Prof. Silva",
  "autorId": 1
}
```

`GET /api/midias`

Lista todas as mídias.

`GET /api/midias?disciplina=web&professor=silva`

Lista mídias filtrando por parte do nome da disciplina e/ou nome do professor.

`GET /api/midias/{id}`

Busca uma mídia pelo id.

`PUT /api/midias/{id}`

Atualiza os dados de uma mídia (título, descrição, etc.).

`DELETE /api/midias/{id}`

Remove uma mídia do sistema.

### Comentários e respostas

`POST /api/midias/{midiaId}/comentarios`

Cria comentário em uma mídia e soma 5 pontos ao autor.

Exemplo:

```json
{
  "texto": "Esse conteúdo ajudou na revisão.",
  "linkComplementar": "https://exemplo.com/artigo",
  "autorId": 1
}
```

`GET /api/midias/{midiaId}/comentarios`

Lista os comentários principais de uma mídia.

`POST /api/comentarios/{comentarioId}/respostas`

Cria uma resposta para um comentário existente e soma 5 pontos ao autor.

`GET /api/comentarios/{comentarioId}/respostas`

Lista as respostas de um comentário.

### Avaliações

`POST /api/midias/{midiaId}/avaliacoes`

Avalia uma mídia. O avaliador ganha 2 pontos. Se a nota for 4 ou 5, o autor da mídia ganha mais 3 pontos.

Exemplo:

```json
{
  "usuarioId": 1,
  "nota": 5,
  "comentario": "Foto nítida e bem organizada."
}
```

`GET /api/midias/{midiaId}/avaliacoes`

Lista avaliações de uma mídia.

`POST /api/comentarios/{comentarioId}/avaliacoes`

Avalia um comentário. O avaliador ganha 2 pontos. Se a nota for 4 ou 5, o autor do comentário ganha mais 3 pontos.

`GET /api/comentarios/{comentarioId}/avaliacoes`

Lista avaliações de um comentário.

### Materiais complementares

`POST /api/midias/{midiaId}/materiais`

Cadastra um material complementar ligado a uma mídia e soma 7 pontos ao autor.

Exemplo:

```json
{
  "titulo": "Resumo de Hibernate",
  "descricao": "Material extra para estudar ORM",
  "link": "https://exemplo.com/hibernate",
  "autorId": 1
}
```

`GET /api/midias/{midiaId}/materiais`

Lista os materiais complementares de uma mídia.

## Como rodar

Executar os testes:

```bash
mvn test
```

Subir a aplicação:

```bash
mvn spring-boot:run
```

A API fica em:

```text
http://localhost:8080
```

O console do H2 fica em:

```text
http://localhost:8080/h2-console
```

Dados do H2:

- JDBC URL: `jdbc:h2:mem:caderno_digital`
- User: `sa`
- Password: vazio

## Por que foi feito dessa forma

A proposta do PDF fala de um MVP, então o projeto evita regras muito complexas. Em vez de implementar login, armazenamento real de arquivos e permissões completas, a API usa `autorId` e `urlArquivo` para representar o usuário logado e o arquivo enviado.

Essa escolha deixa o projeto menor e mais fácil de validar:

- O upload é representado pelo cadastro da URL da mídia.
- A classificação fica nos campos de data, disciplina e professor.
- A colaboração fica nos comentários, respostas e materiais complementares.
- A qualidade do conteúdo é medida pelas avaliações.
- A gamificação é feita com pontos no usuário.
- O ranking é uma consulta ordenada por pontos.

Assim, a base já mostra a arquitetura com Spring Boot, JPA e Hibernate, mas continua simples o suficiente para ser entendida e evoluída por alunos.
