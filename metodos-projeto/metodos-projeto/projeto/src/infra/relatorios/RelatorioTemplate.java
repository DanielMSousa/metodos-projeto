package infra.relatorios;

//classes abstratas e o metodos
public abstract class RelatorioTemplate {
   abstract void iniciaRelatorio();
   abstract void pegaVersoes();

    abstract void pegaTitulo();

    abstract void pegaPartesInteressadas();

    abstract void listaAlteracoesRecentes();

    abstract void exibirDados();

    abstract void finalizaRelatorio();

    void geraRelatorio(){
        iniciaRelatorio();
        pegaTitulo();
        pegaVersoes();
        listaAlteracoesRecentes();
        finalizaRelatorio();
    }
}
