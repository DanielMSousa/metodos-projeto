package Relatorios;

import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RelatorioTXT extends Relatorio {
    String nomeArquivo;

    void escreveArquivo(String conteudo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.nomeArquivo))) {
            writer.write(conteudo);
            writer.newLine();
            System.out.println("Conteúdo gravado no arquivo com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao gravar no arquivo: " + e.getMessage());
        }
    }

    void iniciaRelatorio(){
        nomeArquivo = "relatorio.txt";
    }

    void finalizaRelatorio(){

    }

    void pegaVersoes(){
        //Esses dados seriam pegos do DB...
        List<String> listaDeStrings = new ArrayList<>();
        
        listaDeStrings.add("v1.0");
        listaDeStrings.add("v1.1");
        listaDeStrings.add("v2.0");

        //Esse sim é o código...
        escreveArquivo("Versões do documento:");
        for (String versao : listaDeStrings) {
            escreveArquivo(versao);
        }
    }

    void pegaTitulo(){
        String titulo = "titulo do documento";
        escreveArquivo(titulo);
    }

    void pegaPartesInteressadas(){
        List<String> partesInteressadas = new ArrayList<>();
        
        partesInteressadas.add("UFPB");
        partesInteressadas.add("Raoni Kulesza");

        //Esse sim é o código...
        escreveArquivo("Partes interessadas:");
        for (String cliente : partesInteressadas) {
            escreveArquivo(cliente);
        }
    }

    void listaAlteracoesRecentes(){
        List<String> alteracoesRecentes = new ArrayList<>();
        
        alteracoesRecentes.add("Adicionado adapter");
        alteracoesRecentes.add("Adicionado template");
        alteracoesRecentes.add("Adicionado relatório");

        //Esse sim é o código...
        escreveArquivo("Partes interessadas:");
        for (String alteracao : alteracoesRecentes) {
            escreveArquivo(alteracao);
        }
    }
}
