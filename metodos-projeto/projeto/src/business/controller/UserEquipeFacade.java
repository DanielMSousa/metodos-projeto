package controller;

import Equipe.EquipeController;
import Usuarios.UsuarioAbstrato;
import infra.service.getConnection;
import infra.utils.TipoUsuarios;
import infra.utils.Exception.TipoUsuario.TipoUsuarioInvalidoException;
import infra.utils.Validacoes.UsuarioValidator;
import Usuarios.ControllersInterface;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class UserEquipeFacade {
    private static UserEquipeFacade instance;
    private final Map<TipoUsuarios, ControllersInterface> controladores = new HashMap<>();
    private EquipeController equipeController;
    private Connection connection;
    
    private UserEquipeFacade(String serverName,String dataBaseName,String username, String password) {
        this.connection = getConnection.getConexaoSQL(serverName, dataBaseName, username, password);
        controladores.put(TipoUsuarios.ANALISTA_DE_SISTEMAS, AnalistaSistemasController.getInstance(connection));
        controladores.put(TipoUsuarios.GERENTE, GerenteController.getInstance(connection));
        controladores.put(TipoUsuarios.PROGRAMADOR, ProgramadorController.getInstance(connection));
        equipeController = EquipeController.getInstance(connection);
    }

    public synchronized static UserEquipeFacade getInstance(Connection connection,String serverName,String dataBaseName,String username, String password) {
        if (instance == null) {
            instance = new UserEquipeFacade(serverName,dataBaseName,username,password);
        }
        return instance;
    }

    
    public UsuarioAbstrato criarUsuario(String tipoString, String nome, String login, String senha) {
        TipoUsuarios tipo;
        try {
            tipo = UsuarioValidator.mapearTipo(tipoString);
        } catch (TipoUsuarioInvalidoException e) {
            // Lida com a exceção de tipo de usuário inválido
            e.printStackTrace(); // ou trate de acordo com a sua lógica de negócios
            return null;
        }
        
        ControllersInterface controller = controladores.get(tipo);
        
        return controller.criarNovoUsuario(nome, login, senha);
    }


    
}
