# Exemplos de Comandos cURL - API Caderno Digital Colaborativo

Este documento contém exemplos de comandos cURL para interagir com a API do projeto.

**URL Base:** `http://localhost:8080`

---

## 1. Usuários (`/api/usuarios`)

### Criar Usuário
```bash
curl -X POST http://localhost:8080/api/usuarios \
     -H "Content-Type: application/json" \
     -d '{
       "nome": "Diego",
       "email": "diego@exemplo.com",
       "tipo": "ALUNO"
     }'
```
*Observação: O campo `tipo` aceita `ALUNO` ou `PROFESSOR`.*

### Listar Todos os Usuários
```bash
curl -X GET http://localhost:8080/api/usuarios
```

### Buscar Usuário por ID
```bash
curl -X GET http://localhost:8080/api/usuarios/1
```

### Atualizar Usuário
```bash
curl -X PUT http://localhost:8080/api/usuarios/1 \
     -H "Content-Type: application/json" \
     -d '{
       "nome": "Diego Silva",
       "email": "diego.silva@exemplo.com",
       "tipo": "ALUNO"
     }'
```

### Excluir Usuário
```bash
curl -X DELETE http://localhost:8080/api/usuarios/1
```

### Ranking de Pontuação
```bash
# Ranking Geral
curl -X GET http://localhost:8080/api/usuarios/ranking

# Ranking Filtrado por Disciplina
curl -X GET "http://localhost:8080/api/usuarios/ranking?disciplina=Matematica"
```

---

## 2. Mídias (`/api/midias`)

### Criar Mídia
```bash
curl -X POST http://localhost:8080/api/midias \
     -H "Content-Type: application/json" \
     -d '{
       "titulo": "Aula de Cálculo I",
       "descricao": "Foto do quadro sobre derivadas",
       "urlArquivo": "http://link-da-imagem.com/foto.jpg",
       "tipo": "FOTO",
       "dataHoraAula": "2023-10-27T14:30:00",
       "disciplina": "Calculo",
       "professorResponsavel": "Dr. Newton",
       "autorId": 1
     }'
```
*Observação: O campo `tipo` aceita `FOTO` ou `VIDEO`.*

### Listar Mídias (com filtros)
```bash
curl -X GET "http://localhost:8080/api/midias?disciplina=Calculo&professor=Newton"
```

### Buscar Mídia por ID
```bash
curl -X GET http://localhost:8080/api/midias/1
```

### Atualizar Mídia
```bash
curl -X PUT http://localhost:8080/api/midias/1 \
     -H "Content-Type: application/json" \
     -d '{
       "titulo": "Aula de Cálculo I - Editada",
       "descricao": "Quadro sobre integrais",
       "urlArquivo": "http://link.com/nova-foto.jpg",
       "tipo": "FOTO",
       "dataHoraAula": "2023-10-27T14:30:00",
       "disciplina": "Calculo",
       "professorResponsavel": "Dr. Newton",
       "autorId": 1
     }'
```

### Excluir Mídia
```bash
curl -X DELETE http://localhost:8080/api/midias/1
```

---

## 3. Materiais Complementares (`/api/midias/{midiaId}/materiais`)

### Adicionar Material a uma Mídia
```bash
curl -X POST http://localhost:8080/api/midias/1/materiais \
     -H "Content-Type: application/json" \
     -d '{
       "titulo": "PDF de Exercícios",
       "descricao": "Lista de exercícios sobre o tema da aula",
       "link": "http://link.com/lista.pdf",
       "autorId": 1
     }'
```

### Listar Materiais de uma Mídia
```bash
curl -X GET http://localhost:8080/api/midias/1/materiais
```

---

## 4. Comentários e Respostas (`/api`)

### Comentar em uma Mídia
```bash
curl -X POST http://localhost:8080/api/midias/1/comentarios \
     -H "Content-Type: application/json" \
     -d '{
       "texto": "Excelente aula!",
       "linkComplementar": "http://wiki-auxiliar.com",
       "autorId": 1
     }'
```

### Listar Comentários de uma Mídia
```bash
curl -X GET http://localhost:8080/api/midias/1/comentarios
```

### Responder a um Comentário
```bash
curl -X POST http://localhost:8080/api/comentarios/1/respostas \
     -H "Content-Type: application/json" \
     -d '{
       "texto": "Concordo plenamente!",
       "autorId": 2
     }'
```

### Listar Respostas de um Comentário
```bash
curl -X GET http://localhost:8080/api/comentarios/1/respostas
```

---

## 5. Avaliações (`/api`)

### Avaliar uma Mídia
```bash
curl -X POST http://localhost:8080/api/midias/1/avaliacoes \
     -H "Content-Type: application/json" \
     -d '{
       "usuarioId": 2,
       "nota": 5,
       "comentario": "Qualidade da imagem está ótima"
     }'
```
*Observação: A `nota` deve ser entre 1 e 5.*

### Listar Avaliações de uma Mídia
```bash
curl -X GET http://localhost:8080/api/midias/1/avaliacoes
```

### Avaliar um Comentário
```bash
curl -X POST http://localhost:8080/api/comentarios/1/avaliacoes \
     -H "Content-Type: application/json" \
     -d '{
       "usuarioId": 1,
       "nota": 4,
       "comentario": "Resposta muito útil"
     }'
```

### Listar Avaliações de um Comentário
```bash
curl -X GET http://localhost:8080/api/comentarios/1/avaliacoes
```
