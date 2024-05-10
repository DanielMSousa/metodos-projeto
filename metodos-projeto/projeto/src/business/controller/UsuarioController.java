package controller;

import domain.UserFactory;
import domain.Usuario;
import domain.UsuarioIF;
import infra.service.ServicePersistenceIF;
import infra.service.ServicePersistenceFactory;
import infra.utils.Exception.CriacaoLoginSenha.LoginExisteException;
import infra.utils.Exception.CriacaoLoginSenha.LoginInvalidoException;
import infra.utils.Exception.CriacaoLoginSenha.SenhaInvalidaException;
import infra.utils.Exception.TipoUsuario.TipoUsuarioInvalidoException;
import infra.utils.Validacoes.UsuarioValidator;
public class UsuarioController{
    private final String type ;
    private final UserFactory userFactory = UserFactory.getInstance();
    private static UsuarioController instance;

    private UsuarioController(String bdType){
        this.type = bdType;
        
    }

    public static synchronized UsuarioController getInstance(String bdType){
        if(instance == null){
            instance = new UsuarioController(bdType);
        }
        return instance;
    }

    public void criarNovoUsuario(String nome, String login, String senha) throws LoginExisteException,LoginInvalidoException,SenhaInvalidaException,TipoUsuarioInvalidoException {
        
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
        try{
        UsuarioIF novoUsuario = userFactory.getSystemUser(login,nome,senha);
        ServicePersistenceIF servicePersistence = ServicePersistenceFactory.criarServicePersistence(type);
        servicePersistence.criarUsuario(novoUsuario);
        } catch (LoginExisteException e) {
                throw new LoginExisteException("Login já existe: " + e.getMessage());    
        }catch( TipoUsuarioInvalidoException e){
            throw new TipoUsuarioInvalidoException(e.getMessage());
        }
    }

    public void deletarUsuario(String login) throws LoginExisteException, TipoUsuarioInvalidoException {
        try{
            ServicePersistenceIF servicePersistence = ServicePersistenceFactory.criarServicePersistence(type);
            servicePersistence.excluirUsuario(login);
        }catch(LoginExisteException e){
            throw new LoginExisteException("Usuario nao encontrado " + e.getMessage());
        }catch(TipoUsuarioInvalidoException e){
            throw new TipoUsuarioInvalidoException(e.getMessage());
        }
    }

    public UsuarioIF updateUsuario(String login, String novoNome, String novaSenha) {
        try {
            // Cria uma instância do serviço de persistência
            ServicePersistenceIF servicePersistence = ServicePersistenceFactory.criarServicePersistence(type);
            
            // Busca o usuário com base no login
            UsuarioIF usuario = servicePersistence.buscarUsuarioPorLogin(login);
            
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
        ServicePersistenceIF servicePersistence = ServicePersistenceFactory.criarServicePersistence(type);
        
        // Chama o método getUsuariosProjeto() sem especificar um projeto específico
        String usuariosProjetoJSON = servicePersistence.getUsuarios();
        
        return usuariosProjetoJSON;
    }

    public UsuarioIF getUsuarioId(String login){
        ServicePersistenceIF servicePersistence = ServicePersistenceFactory.criarServicePersistence(type);        
        UsuarioIF user = servicePersistence.getUsuarioPorId(login);

        return user;
    }

    
}
