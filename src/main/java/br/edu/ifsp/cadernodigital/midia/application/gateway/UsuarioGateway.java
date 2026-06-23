package br.edu.ifsp.cadernodigital.midia.application.gateway;

public interface UsuarioGateway {
    String buscarNomeAutor(Long autorId);
    void validarAutorExistente(Long autorId);
}
