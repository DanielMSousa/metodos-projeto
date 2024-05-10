package domain;

import infra.utils.Exception.TipoUsuario.TipoUsuarioInvalidoException;

public class UserFactory implements UserFactoryIF{
    private static UserFactory instance;
    private UserFactory(){}
    public static synchronized UserFactory getInstance(){
        if(instance == null){
            instance = new UserFactory();
        }
        return instance;
    }

    @Override
    public UsuarioIF getSystemUser(String nome, String login, String senha) {
        return new Usuario(nome, login, senha);
    }
    @Override
    public  UsuarioProjeto getProjectUser(String userType, String login, int idProjeto)throws TipoUsuarioInvalidoException{
        try{
            FuncaoIF funcao = FunctionFactory.getUserFunction(userType);
            return new UsuarioProjeto(idProjeto, login, funcao);
        }
        catch(TipoUsuarioInvalidoException e){throw new TipoUsuarioInvalidoException(e.getMessage());}
        
    }
}
