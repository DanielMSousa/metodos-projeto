package controller;

import domain.Cartao;
import domain.CartaoUsuario;
import domain.UsuarioProjeto;
import infra.service.ServicePersistenceFactory;
import infra.service.ServicePersistenceIF;

public class CartaoUsuarioController {
    private final String type;
    
    public CartaoUsuarioController(String dbName) {
        this.type = dbName;
    }

    // Método para adicionar um novo cartão de usuário
    public CartaoUsuario getCartaoUsuario(int idCartao,int idUsuario){
        return CartaoUsuario.createCartaoUsuario(idCartao, idUsuario);
    }

    public void adicionarCartaoUsuario(Cartao cartao, UsuarioProjeto usuario) {
        ServicePersistenceIF servicePersistence = ServicePersistenceFactory.criarServicePersistence(type);
        servicePersistence.addUsuarioCartao(cartao, usuario);
    }

    // Método para excluir um cartão de usuário
    public void excluirCartaoUsuario(UsuarioProjeto id, CartaoUsuario idcartaoUsuario) {
        ServicePersistenceIF servicePersistence = ServicePersistenceFactory.criarServicePersistence(type);
        servicePersistence.removeCartaoUsuario(id, idcartaoUsuario);
    }
}
