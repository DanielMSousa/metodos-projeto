package domain;

public class CartaoUsuario {
    private int id; // integer pk
    private int cartao; // integer (foreign key referencing cartoes.id)
    private int usuario; // integer (foreign key referencing usuarios.id)

    // Constructor
    public CartaoUsuario(int id, int cartao, int usuario) {
        this.id = id;
        this.cartao = cartao;
        this.usuario = usuario;
    }

    public int getCartao() {
        return cartao;
    }
    public int getId() {
        return id;
    }
    public int getUsuario() {
        return usuario;
    }
    // Getters and setters
    // Similar to the Cartao class, you can generate or write them manually
}