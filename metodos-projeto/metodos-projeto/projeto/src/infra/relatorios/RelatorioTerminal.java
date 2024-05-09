package infra.relatorios;

import java.util.List;
import java.util.ArrayList;

public class RelatorioTerminal extends RelatorioTemplate {
    @Override
    void pegaVersoes(){
        //Esses dados seriam pegos do DB...
        List<String> listaDeStrings = new ArrayList<>();
        
        listaDeStrings.add("v1.0");
        listaDeStrings.add("v1.1");
        listaDeStrings.add("v2.0");

        //Esse sim é o código...
        System.out.println("Versões do documento:");
        for (String versao : listaDeStrings) {
            System.out.println(versao);
        }
    }
    @Override
    void pegaTitulo(){
        String titulo = "titulo do documento";
        System.out.println(titulo);
    }
    @Override
    void pegaPartesInteressadas(){
        List<String> partesInteressadas = new ArrayList<>();
        
        partesInteressadas.add("UFPB");
        partesInteressadas.add("Raoni Kulesza");

        //Esse sim é o código...
        System.out.println("Partes interessadas:");
        for (String cliente : partesInteressadas) {
            System.out.println(cliente);
        }
    }
    @Override
    void listaAlteracoesRecentes(){
        List<String> alteracoesRecentes = new ArrayList<>();
        
        alteracoesRecentes.add("Adicionado adapter");
        alteracoesRecentes.add("Adicionado template");
        alteracoesRecentes.add("Adicionado relatório");

        //Esse sim é o código...
        System.out.println("Partes interessadas:");
        for (String alteracao : alteracoesRecentes) {
            System.out.println(alteracao);
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
