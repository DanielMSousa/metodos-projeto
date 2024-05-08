package Usuarios;

import Utils.Exception.CriacaoLoginSenha.SenhaInvalidaException;
import Utils.Exception.TipoUsuario.TipoUsuarioInvalidoException;
import Utils.Validacoes.UsuarioValidator;
import factory.OperadorSistemaFactory;
import factory.ServicePersistenceFactory;
import userInterface.OperadorSistema;
import Service.ServicePersistence;
import Utils.Exception.CriacaoLoginSenha.LoginExisteException;
import Utils.Exception.CriacaoLoginSenha.LoginInvalidoException;
public class UsuarioController{
    private final String type ;
    
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
        OperadorSistema novoUsuario = OperadorSistemaFactory.GetUsuario("criar",login,nome,senha);
        ServicePersistence servicePersistence = ServicePersistenceFactory.criarServicePersistence(type);
        servicePersistence.criarUsuario(novoUsuario);
        } catch (LoginExisteException e) {
                throw new LoginExisteException("Login já existe: " + e.getMessage());    
        }catch( TipoUsuarioInvalidoException e){
            throw new TipoUsuarioInvalidoException(e.getMessage());
        }
    }

    public void deletarUsuario(String login) throws LoginExisteException, TipoUsuarioInvalidoException {
        try{
            ServicePersistence servicePersistence = ServicePersistenceFactory.criarServicePersistence(type);
            servicePersistence.excluirUsuario(login);
        }catch(LoginExisteException e){
            throw new LoginExisteException("Usuario nao encontrado " + e.getMessage());
        }catch(TipoUsuarioInvalidoException e){
            throw new TipoUsuarioInvalidoException(e.getMessage());
        }
    }

    public OperadorSistema updateUsuario(String login, String novoNome, String novaSenha) {
        try {
            // Cria uma instância do serviço de persistência
            ServicePersistence servicePersistence = ServicePersistenceFactory.criarServicePersistence(type);
            
            // Busca o usuário com base no login
            OperadorSistema usuario = servicePersistence.buscarUsuarioPorLogin(login);
            
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
        ServicePersistence servicePersistence = ServicePersistenceFactory.criarServicePersistence(type);
        
        // Chama o método getUsuariosProjeto() sem especificar um projeto específico
        String usuariosProjetoJSON = servicePersistence.getUsuarios();
        
        return usuariosProjetoJSON;
    }

    
}
