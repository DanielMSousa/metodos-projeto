package controller;
public class Pilha<T> {
    private static class No<T> {
        private T dado;
        private No<T> proximo;

        private No(T dado) {
            this.dado = dado;
        }
    }

    private No<T> topo;

    public Pilha() {
        this.topo = null;
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public void empilhar(T dado) {
        No<T> novoNo = new No<>(dado);
        novoNo.proximo = topo;
        topo = novoNo;
    }

    public T desempilhar() {
        if (estaVazia()) {
            throw new IllegalStateException("A pilha está vazia");
        }
        T dadoRemovido = topo.dado;
        topo = topo.proximo;
        return dadoRemovido;
    }

    public T peek() {
        if (estaVazia()) {
            throw new IllegalStateException("A pilha está vazia");
        }
        return topo.dado;
    }
}
