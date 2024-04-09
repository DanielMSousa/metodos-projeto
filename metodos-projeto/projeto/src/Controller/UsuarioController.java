package src.Controller;


import src.Models.Usuarios.niveis_acesso.Abstrato.UsuarioAbstrato;
import src.Models.Usuarios.niveis_acesso.AnalistaSistemas;
import src.Models.Usuarios.niveis_acesso.Gerente;
import src.Models.Usuarios.niveis_acesso.Programador;


public class UsuarioController {
        private static UsuarioController instance;
        private UsuarioManager usuarioManager;

        public synchronized static UsuarioController getInstance(){
            if(instance == null){
                instance = new UsuarioController();
            }
            return instance;
        }

        
        //metodo para criar usuario com base no nível de acesso
        public UsuarioAbstrato criarNovoUsuario(int id,String nome, String nivelAcesso,String login, String senha){
            UsuarioAbstrato novoUsuario = null;
            if(nivelAcesso.equals("Gerente")){
                novoUsuario = new Gerente(id, nome);
            }else if(nivelAcesso.equals("Programador")){
                novoUsuario = new Programador(id, nome);
            }else{
                novoUsuario = new AnalistaSistemas(id, nome);
            }

            if(novoUsuario != null){
                try{
                    if(novoUsuario.setLogin(login) && novoUsuario.setSenha(senha)){
                    //adicionar à lista de usuários
                    usuarioManager.adicionarUsuario(novoUsuario);}}
            catch(IllegalArgumentException e){
                    System.err.println("Erro ao criar novo usuário "+ e.getMessage());
            }}

            return novoUsuario;
        }

        public void listarUsuarios(){
            usuarioManager.listarUsuarios();
        }
    }

