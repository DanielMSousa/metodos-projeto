package src.Controle;
import modelos.Usuario;
import src.Fronteira.niveis_acesso.AnalistaSistemasFronteira;
import src.Fronteira.niveis_acesso.GerenteFronteira;
import src.Fronteira.niveis_acesso.ProgramadorFronteira;


public class UsuarioController {
    
        private UsuarioManager usuarioManager;

        public UsuarioController(){
            this.usuarioManager = new UsuarioManager();
        }

        //metodo para criar usuario com base no nível de acesso
        public void criarNovoUsuario(int id,String nome, String nivelAcesso,String login, String senha){
            Usuario novoUsuario = null;
            if(nivelAcesso.equals("Gerente")){
                novoUsuario = new GerenteFronteira(id, nome);
            }else if(nivelAcesso.equals("Programador")){
                novoUsuario = new ProgramadorFronteira(id, nome);
            }else{
                novoUsuario = new AnalistaSistemasFronteira(id, nome);
            }

            if(novoUsuario != null){
                try{
                    if(novoUsuario.setLogin(login) && novoUsuario.setSenha(senha)){
                    //adicionar à lista de usuários
                    usuarioManager.adicionarUsuario(novoUsuario);}}
            catch(IllegalArgumentException e){
                    System.err.println("Erro ao criar novo usuário "+ e.getMessage());
            }}
        }

        public void listarUsuarios(){
            usuarioManager.listarUsuarios();
        }
    }

