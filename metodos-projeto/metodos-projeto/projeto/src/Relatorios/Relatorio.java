package Relatorios;

//classes abstratas e o metodos
public class Relatorio {
    void iniciaRelatorio(){

    }

    void pegaVersoes(){

    }

    void pegaTitulo(){

    }

    void pegaPartesInteressadas(){
        
    }

    void listaAlteracoesRecentes(){

    }

    void exibirDados(){

    }

    void finalizaRelatorio(){

    }

    void geraRelatorio(){
        iniciaRelatorio();
        pegaTitulo();
        pegaVersoes();
        listaAlteracoesRecentes();
        finalizaRelatorio();
    }
}
