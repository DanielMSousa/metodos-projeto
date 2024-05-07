package src.Facade;

import src.AnalistaSistemas.AnalistaSistemasController;
import src.Equipe.EquipeController;
import src.Gerente.GerenteController;
import src.Programador.ProgramadorController;
import src.UsuarioAbstracao.UsuarioAbstrato;
import src.UsuarioAbstracao.UsuarioController;
import src.Utils.TipoUsuarios;
import src.Utils.Validacoes.UsuarioValidator;
import src.Utils.Exception.TipoUsuario.TipoUsuarioInvalidoException;

import java.util.HashMap;
import java.util.Map;

public class UserEquipeFacade {
    private static UserEquipeFacade instance;
    private final Map<TipoUsuarios, UsuarioController> controladores = new HashMap<>();
    private EquipeController equipeController;
    
    private UserEquipeFacade() {
        controladores.put(TipoUsuarios.ANALISTA_DE_SISTEMAS, AnalistaSistemasController.getInstance());
        controladores.put(TipoUsuarios.GERENTE, GerenteController.getInstance());
        controladores.put(TipoUsuarios.PROGRAMADOR, ProgramadorController.getInstance());
        equipeController = EquipeController.getInstance();
    }

    public synchronized static UserEquipeFacade getInstance() {
        if (instance == null) {
            instance = new UserEquipeFacade();
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
        
        UsuarioController controller = controladores.get(tipo);
        
        return controller.criarNovoUsuario(nome, login, senha);
    }


    
}
