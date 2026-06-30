package br.edu.ifsp.interacaoservice.application.gateway;
public interface UsuarioGateway {
    String buscarNomeUsuario(Long usuarioId);
    void validarUsuarioExistente(Long usuarioId);
    Long buscarAutorDaMidia(Long midiaId);
}
