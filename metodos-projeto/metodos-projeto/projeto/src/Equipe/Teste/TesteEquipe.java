package src.Equipe.Teste;

import src.AnalistaSistemas.AnalistaSistemasModel;
import src.Equipe.EquipeController;
import src.Equipe.EquipeLista;
import src.Equipe.EquipeModel;
import src.Facade.UserEquipeFacade;
import src.Gerente.GerenteController;
import src.Gerente.GerenteModel;
import src.Gerente.Listas.ControllerGerentesDisponiveis;
import src.Programador.ProgramadorController;
import src.Programador.ProgramadorModel;
import src.Programador.Listas.ProgramadoresDisponiveis;

import java.util.List;

public class TesteEquipe {

    public static void main(String[] args) {
        UserEquipeFacade facade = UserEquipeFacade.getInstance();
        facade.criarUsuario("gerente", "matheusGer", "MAMAAFF", "kfsakmn123");
        ProgramadorController programadorController = ProgramadorController.getInstance();
        GerenteController gerenteController = GerenteController.getInstance();
        // Criando instâncias dos modelos necessários
        AnalistaSistemasModel analista = new AnalistaSistemasModel("Nome do Analista");
        GerenteModel gerente = gerenteController.criarNovoUsuario("Gerente 10", "GerenteAB", "MelhorGerente1020");
        ProgramadorModel programador1 = programadorController.criarNovoUsuario("Programador 1", "loginA", "senhaadsa21");
        ProgramadorModel programador2 = programadorController.criarNovoUsuario("Programador 2", "loginB", "senha1ds2");
        ProgramadoresDisponiveis listProgramadoresDisponiveis = ProgramadoresDisponiveis.getInstance(null);
//    Criando uma nova equipe usando o controlador
        EquipeController equipeController = EquipeController.getInstance();
        System.out.println(ControllerGerentesDisponiveis.getInstance().getListaUsuarios());
   equipeController.criarEquipe(gerente, programadorController.obterProgramadoresDisponiveis(), analista);
   System.out.println(programadorController.obterProgramadoresDisponiveis());
   // Obtendo a instância única de EquipeLista
   EquipeLista equipeLista = EquipeLista.getInstance();

   // Obtendo a lista de equipes
   List<EquipeModel> listaEquipes = equipeLista.getListaEquipes();

   // Exibindo as equipes na lista
   System.out.println("Lista de equipes:");
   for (EquipeModel equipe : listaEquipes) {
       System.out.println("Gerente: " + equipe.getGerente().getNome());
       System.out.println("Analista de Sistemas: " + equipe.getAnalistaSistemas().getNome());
       System.out.println("Programadores:");
       for (ProgramadorModel programador : equipe.getListaProgramador()) {
           System.out.println("- " + programador.getNome());
       }
       System.out.println();
   }
    }

}
