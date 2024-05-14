package controller;
class Memento {
    private final String estado;

    public Memento(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}

// Originador
class EditorTexto {
    private String texto;

    public void adicionarTexto(String texto) {
        if (this.texto == null) {
            this.texto = texto;
        } else {
            this.texto += texto;
        }
    }

    public String getTexto() {
        return texto;
    }

    public Memento salvarParaMemento() {
        return new Memento(texto);
    }

    public void restaurarDeMemento(Memento memento) {
        texto = memento.getEstado();
    }
}

// Comando
interface Comando {
    void executar();
}

class ComandoAdicionarTexto implements Comando {
    private final EditorTexto editor;
    private final String texto;

    public ComandoAdicionarTexto(EditorTexto editor, String texto) {
        this.editor = editor;
        this.texto = texto;
    }

    @Override
    public void executar() {
        editor.adicionarTexto(texto);
    }
}

class ComandoDesfazer implements Comando {
    private final EditorTexto editor;
    private final Caretaker caretaker;

    public ComandoDesfazer(EditorTexto editor, Caretaker caretaker) {
        this.editor = editor;
        this.caretaker = caretaker;
    }

    @Override
    public void executar() {
        editor.restaurarDeMemento(caretaker.desfazer());
    }
}

class ComandoRefazer implements Comando {
    private final EditorTexto editor;
    private final Caretaker caretaker;

    public ComandoRefazer(EditorTexto editor, Caretaker caretaker) {
        this.editor = editor;
        this.caretaker = caretaker;
    }

    @Override
    public void executar() {
        editor.restaurarDeMemento(caretaker.refazer());
    }
}

// Caretaker
class Caretaker {
    private final Pilha<Memento> mementos = new Pilha<>();
    private final Pilha<Memento> desfeitos = new Pilha<>();

    public void salvar(Memento memento) {
        mementos.empilhar(memento);
    }

    public Memento desfazer() {
        Memento memento = mementos.desempilhar();
        if (memento != null) {
            desfeitos.empilhar(memento);
        }
        return mementos.estaVazia() ? null : mementos.peek();
    }

    public Memento refazer() {
        Memento memento = desfeitos.desempilhar();
        if (memento != null) {
            mementos.empilhar(memento);
        }
        return memento;
    }
}

// Invoker
class EditorInvoker {
    private final EditorTexto editor;
    private final Caretaker caretaker;

    public EditorInvoker(EditorTexto editor, Caretaker caretaker) {
        this.editor = editor;
        this.caretaker = caretaker;
    }

    public void adicionarTexto(String texto) {
        Comando comando = new ComandoAdicionarTexto(editor, texto);
        comando.executar();
        caretaker.salvar(editor.salvarParaMemento());
    }

    public void desfazer() {
        Comando comando = new ComandoDesfazer(editor, caretaker);
        comando.executar();
    }

    public void refazer() {
        Comando comando = new ComandoRefazer(editor, caretaker);
        comando.executar();
    }

    public String getTexto() {
        return editor.getTexto();
    }
}