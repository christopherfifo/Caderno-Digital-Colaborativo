package br.edu.ifsp.cadernodigital.midia.presentation;

import br.edu.ifsp.cadernodigital.midia.application.dto.MidiaRequest;
import br.edu.ifsp.cadernodigital.midia.application.dto.MidiaResponse;
import br.edu.ifsp.cadernodigital.midia.application.service.MidiaService;
import br.edu.ifsp.cadernodigital.midia.domain.model.TipoMidia;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MidiaController.class)
public class MidiaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MidiaService midiaService;

    private MidiaRequest midiaRequest;
    private MidiaResponse midiaResponse;

    @BeforeEach
    void setUp() {
        midiaRequest = new MidiaRequest(
                "Foto da Lousa",
                "Anotacoes da aula",
                "http://example.com/foto.jpg",
                TipoMidia.FOTO,
                LocalDateTime.now(),
                "Matematica",
                "Prof. Joao",
                1L
        );

        midiaResponse = new MidiaResponse(
                1L,
                midiaRequest.titulo(),
                midiaRequest.descricao(),
                midiaRequest.urlArquivo(),
                midiaRequest.tipo(),
                midiaRequest.dataHoraAula(),
                midiaRequest.disciplina(),
                midiaRequest.professorResponsavel(),
                LocalDateTime.now(),
                1L,
                "Aluno Teste"
        );
    }

    @Test
    void deveCriarMidia() throws Exception {
        Mockito.when(midiaService.criar(any(MidiaRequest.class))).thenReturn(midiaResponse);

        mockMvc.perform(post("/api/midias")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(midiaRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(midiaResponse.id()))
                .andExpect(jsonPath("$.titulo").value(midiaResponse.titulo()))
                .andExpect(jsonPath("$.autorId").value(midiaResponse.autorId()));
    }

    @Test
    void deveListarMidias() throws Exception {
        Mockito.when(midiaService.listar(any(), any())).thenReturn(List.of(midiaResponse));

        mockMvc.perform(get("/api/midias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(midiaResponse.id()));
    }

    @Test
    void deveBuscarMidiaPorId() throws Exception {
        Mockito.when(midiaService.buscarPorId(1L)).thenReturn(midiaResponse);

        mockMvc.perform(get("/api/midias/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(midiaResponse.id()));
    }

    @Test
    void deveAtualizarMidia() throws Exception {
        Mockito.when(midiaService.atualizar(eq(1L), any(MidiaRequest.class))).thenReturn(midiaResponse);

        mockMvc.perform(put("/api/midias/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(midiaRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(midiaResponse.id()));
    }

    @Test
    void deveExcluirMidia() throws Exception {
        Mockito.doNothing().when(midiaService).excluir(1L);

        mockMvc.perform(delete("/api/midias/{id}", 1L))
                .andExpect(status().isNoContent());

        Mockito.verify(midiaService).excluir(1L);
    }
}
