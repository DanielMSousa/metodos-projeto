package controller;

import domain.Cartao;
import domain.CartaoUsuario;
import domain.UsuarioProjeto;
import infra.service.ServicePersistenceFactory;
import infra.service.ServicePersistenceIF;

public class CartaoController {
    private final String type;
    
    public CartaoController(String dbName) {
        this.type = dbName;
    }

    // Método para adicionar um novo cartão de usuário
    public Cartao getCartaoUsuario(String status, int kanban, String nome, String texto){
        return Cartao.getCartao(status, kanban, nome, texto);
    }

    public void adicionarCartao(Cartao cartao) {
        ServicePersistenceIF servicePersistence = ServicePersistenceFactory.criarServicePersistence(type);
        servicePersistence.createCartao(cartao);
    }

    public void alterarStatusCartao(Cartao cartao){
        ServicePersistenceIF servicePersistence =ServicePersistenceFactory.criarServicePersistence(type);
        servicePersistence.updateStatusCartao(cartao.getId(),cartao.getStatus());
    }

    // Método para excluir um cartão de usuário
    public void excluirCartaoUsuario(UsuarioProjeto usuarioProjeto, CartaoUsuario cartaoUsuario) {
        ServicePersistenceIF servicePersistence = ServicePersistenceFactory.criarServicePersistence(type);
        servicePersistence.removeCartaoUsuario(usuarioProjeto, cartaoUsuario);
    }
}