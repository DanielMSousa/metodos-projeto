package Usuarios;

import Utils.Exception.CriacaoLoginSenha.SenhaInvalidaException;
import Utils.Validacoes.UsuarioValidator;
import factory.ServicePersistenceFactory;
import factory.UsuarioFactory;
import Service.ServicePersistence;
import Utils.Exception.CriacaoLoginSenha.LoginExisteException;
import Utils.Exception.CriacaoLoginSenha.LoginInvalidoException;
public class UsuarioController{
    private final String type ;
    private final String loginUsuario ;
    private final String password ;
    private static UsuarioController instance;

    private UsuarioController(String bdType,String login,String password){
        this.type = bdType;
        this.loginUsuario = login;
        this.password = password;
    }

    public static synchronized UsuarioController getInstance(String bdType,String login, String password){
        if(instance == null){
            instance = new UsuarioController(bdType, login, password);
        }
        return instance;
    }

    public void criarNovoUsuario(String nome, String login, String senha) throws LoginExisteException,LoginInvalidoException,SenhaInvalidaException {
        
        try{
            UsuarioValidator.validarFormatoLogin(login);
        }catch( LoginInvalidoException e){
            throw new LoginInvalidoException("Formato de login inválido " + e);
        }

        try{
            UsuarioValidator.validarSenha(senha);
        }catch(SenhaInvalidaException e){
            throw new SenhaInvalidaException("Formato de senha inválida" + e);
        }

        Usuario novoUsuario = UsuarioFactory.criarUsuario(nome, login, senha);
        
        try {
            ServicePersistence servicePersistence = ServicePersistenceFactory.criarServicePersistence(type, loginUsuario, password);
            servicePersistence.criarUsuario(novoUsuario);
        } catch (LoginExisteException e) {
            throw new LoginExisteException("Login já existe: " + e.getMessage());
        }
    }

    public void deletarUsuario(String login) throws LoginExisteException {
        try{
            ServicePersistence servicePersistence = ServicePersistenceFactory.criarServicePersistence(type, loginUsuario, password);
            servicePersistence.excluirUsuario(login);
        }catch(LoginExisteException e){
            throw new LoginExisteException("Usuario nao encontrado " + e.getMessage());
        }
    }

    public Usuario updateUsuario(String login, String novoNome, String novaSenha) {
        try {
            // Cria uma instância do serviço de persistência
            ServicePersistence servicePersistence = ServicePersistenceFactory.criarServicePersistence(type, loginUsuario, password);
            
            // Busca o usuário com base no login
            Usuario usuario = servicePersistence.buscarUsuarioPorLogin(login);
            
            // Verifica se o usuário foi encontrado
            if (usuario != null) {
                // Atualiza os dados do usuário, se novos valores foram fornecidos
                if (novoNome != null && !novoNome.isEmpty()) {
                    usuario.setNome(novoNome);
                }
                if (novaSenha != null && !novaSenha.isEmpty()) {
                    usuario.setSenha(novaSenha);
                }
                
                // Atualiza o usuário no serviço de persistência
                servicePersistence.atualizarUsuario(usuario);
                
                // Retorna o usuário atualizado
                return usuario;
            } else {
                // Se o usuário não for encontrado, retorna null ou lança uma exceção, dependendo da sua lógica de negócios
                return null;
            }
        } catch (Exception e) {
            // Lida com exceções, se necessário
            e.printStackTrace();
            return null;
        }
    }

    public String getUsuarios() {
        // Supondo que você tenha uma instância de ServicePersistence já configurada com o tipo, login e senha
        ServicePersistence servicePersistence = ServicePersistenceFactory.criarServicePersistence(type, loginUsuario, password);
        
        // Chama o método getUsuariosProjeto() sem especificar um projeto específico
        String usuariosProjetoJSON = servicePersistence.getUsuarios();
        
        return usuariosProjetoJSON;
    }

    
}
