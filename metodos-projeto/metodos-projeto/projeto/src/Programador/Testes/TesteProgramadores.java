package src.Programador.Testes;

import src.Programador.ProgramadorController;
import src.Programador.ProgramadorModel;
import src.Programador.Listas.ControllerProgramadoresDisponiveis;
import src.Programador.Listas.ControllerProgramadoresEmProjeto;


public class TesteProgramadores {

    public static void main(String[] args) {
        // Criando alguns programadores
        
        ProgramadorController programadorController = ProgramadorController.getInstance();
        ControllerProgramadoresDisponiveis lista = ControllerProgramadoresDisponiveis.getInstance();
        ControllerProgramadoresEmProjeto listaEP = ControllerProgramadoresEmProjeto.getInstance();
        // Adicionando programadores à lista de programadores disponíveis
        programadorController.criarNovoUsuario("João", "nsjnsjdns", "@dsad23dwwddwdwdw");
        programadorController.criarNovoUsuario( "Maria", "nsjnsjdns", "@dsad23dwdwdw");
        programadorController.criarNovoUsuario( "Pedro", "nsjnsjdns", "@dsad23dwdwdw");

        //Listando
        lista.listarProgramadoresDisponiveis();
        //obtendo usuario
        ProgramadorModel usuario = lista.getUsuarioPorNome("João");  
        //Removendo
        programadorController.alterarDiponibilidade(usuario);
        //printando
        System.out.println("PROGRAMADORES DISPONIVEIS");
        lista.listarProgramadoresDisponiveis();
        System.out.println("PROGRAMADORES EM PROJETO");
        listaEP.listarProgramadoresEmProjeto();
    }
}