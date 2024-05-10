package domain;

public class CartaoUsuario {
    private int id = 0 ; // integer pk
    private int cartao; // integer (foreign key referencing cartoes.id)
    private int usuario; // integer (foreign key referencing usuarios.id)

    // Constructor
    private CartaoUsuario( int cartao, int usuario) {
        this.cartao = cartao;
        this.usuario = usuario;
    }

    public static CartaoUsuario createCartaoUsuario(int cartao,int usuario){
        return new CartaoUsuario(cartao, usuario);
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

    public void setId(int id) {
        this.id = id;
    }
    // Getters and setters
    // Similar to the Cartao class, you can generate or write them manually
}