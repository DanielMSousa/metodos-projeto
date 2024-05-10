package infra.relatorios;

import java.util.List;
import java.util.ArrayList;

import infra.logger.LoggerService;

public class RelatorioLog extends RelatorioTemplate {
    LoggerService logger;
    RelatorioLog(){
        //trocar por singleton
        this.logger = LoggerService.getInstance();
    }

    @Override
    void pegaVersoes(){
        //Esses dados seriam pegos do DB...
        List<String> listaDeStrings = new ArrayList<>();
        
        listaDeStrings.add("v1.0");
        listaDeStrings.add("v1.1");
        listaDeStrings.add("v2.0");

        //Esse sim é o código...
        logger.log("Versões do documento:");
        for (String versao : listaDeStrings) {
            logger.log(versao);
        }
    }
    @Override
    void pegaTitulo(){
        String titulo = "titulo do documento";
        logger.log(titulo);
    }
    @Override
    void pegaPartesInteressadas(){
        List<String> partesInteressadas = new ArrayList<>();
        
        partesInteressadas.add("UFPB");
        partesInteressadas.add("Raoni Kulesza");

        //Esse sim é o código...
        logger.log("Partes interessadas:");
        for (String cliente : partesInteressadas) {
            logger.log(cliente);
        }
    }
    @Override
    void listaAlteracoesRecentes(){
        List<String> alteracoesRecentes = new ArrayList<>();
        
        alteracoesRecentes.add("Adicionado adapter");
        alteracoesRecentes.add("Adicionado template");
        alteracoesRecentes.add("Adicionado relatório");

        //Esse sim é o código...
        logger.log("Partes interessadas:");
        for (String alteracao : alteracoesRecentes) {
            logger.log(alteracao);
        }
    }
    @Override
    void finalizaRelatorio() {
        // TODO Auto-generated method stub
        
    }
    @Override
    void iniciaRelatorio() {
        // TODO Auto-generated method stub
        
    }
    @Override
    void exibirDados() {
        // TODO Auto-generated method stub
        
    }
}

