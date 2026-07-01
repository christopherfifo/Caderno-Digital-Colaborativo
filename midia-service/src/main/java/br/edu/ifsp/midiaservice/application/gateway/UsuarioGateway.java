package br.edu.ifsp.midiaservice.application.gateway;
public interface UsuarioGateway {
    String buscarNomeAutor(Long autorId);
    void validarAutorExistente(Long autorId);
}
