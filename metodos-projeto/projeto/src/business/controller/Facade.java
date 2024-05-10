package controller;

import domain.FuncaoIF;
import domain.Gerente;

public class Facade {
    private ProjetoController projetoController;
    private GerenteController gerenteController; 
    private ProgramadorController programadorController;
    private AnalistaSistemasController analistaSistemasController;
    private Facade instance;

    private Facade(String dbType){
       projetoController = ProjetoController.getInstance(dbType);
       gerenteController = GerenteController.getInstance(dbType);
       programadorController = ProgramadorController.getInstance(dbType);
       analistaSistemasController = AnalistaSistemasController.getInstance(dbType);
    };

    public Facade getInstance(String dbType){
        if(instance== null){
            instance = new Facade(dbType);
        }
        return instance;
    }
    //* MÉTODOS QUE UTILIZAM MAIS DE UM CONTROLLER */

    //* 1 - Adicionar Usuario no projeto */
    //* Controllers utilizados*/
    //* Controller de Gerente, Controller de Projeto */
    //* Pseudo codigo */
    //* Gerente envia o id do projeto para o usuário, determinando o tipo do usuário no projeto

    //* 2 - Adicionar Tarefa a outro usuario no projeto */
    //* Controllers utilizados*/
    //* Controller de Gerente, Controller do tipo do usuario, Controller do projeto */
    //* PseudoCodigo */
    //* Gerente pesquisa o usuario no projeto, adiciona tarefa, com descrição */ 
    public void adicionarTarefa(Gerente gerente,FuncaoIF usuario,int idProjeto){

    }

    //* 3 - ObterRelatorio */
    //* Controllers utilizados */
    //* Gerente controller, Controller de Relatórios */
    //* Pseudo codigo */ 
    //* Gerente solicita o relatorio, que contém dados relevantes sobre o progeto */


    //* 4 - Encerrar Projeto  */
    //* Controllers utilizados */
    //* Controller gerente, Controller de Relatórios */
    //* Pseudo codigo */
    //*O Gerente decide encerrar o projeto, o que envolve a geração de relatórios finais e outras atividades de encerramento. */ 

}
