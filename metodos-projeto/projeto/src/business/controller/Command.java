package controller;
class Originador {
    private String estado = "";

    public void adicionarTexto(String texto) {
        this.estado += texto;
    }

    public Memento salvarParaMemento() {
        return new Memento(estado);
    }

    public void restaurarDeMemento(Memento memento) {
        estado = memento.getEstado();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String texto){
        estado = texto;
    }
}


// Comando
interface Comando {
    void executar();
}

class ComandoAdicionarTexto implements Comando {
    private final Originador originador;
    private final String texto;

    public ComandoAdicionarTexto(Originador originador, String texto) {
        this.originador = originador;
        this.texto = texto;
    }

    @Override
    public void executar() {
        originador.setEstado(originador.getEstado() + texto);
    }
}

class ComandoDesfazer implements Comando {
    private final Originador originador;
    private final Caretaker caretaker;

    public ComandoDesfazer(Originador originador, Caretaker caretaker) {
        this.originador = originador;
        this.caretaker = caretaker;
    }

    @Override
    public void executar() {
        Memento memento = caretaker.desfazer();
        if (memento != null) {
            originador.restaurarDeMemento(memento);
        }
    }
}