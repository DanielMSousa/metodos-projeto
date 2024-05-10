package controller;


import domain.Kanban;
import infra.service.ServicePersistenceFactory;
import infra.service.ServicePersistenceIF;

public class KanbanController {
    private final String type ;

    private static KanbanController instance;

    private KanbanController(String bdType){
        this.type = bdType;
    }

    public KanbanController getInstance(String bdType){
        if(instance == null){
            instance = new KanbanController(bdType);
        }
        return instance;
    }
    
    // Método para adicionar um novo quadro Kanban
    public void CreateKanban(int idProjeto, String nome) {
        Kanban kanban = Kanban.createKanban(idProjeto, nome);

        ServicePersistenceIF servicePersistenceIF = ServicePersistenceFactory.criarServicePersistence(type);
        servicePersistenceIF.createKanban(kanban);
    }

    // Método para atualizar um quadro Kanban existente
    public void atualizarKanban(int id, String novoNome) {
        ServicePersistenceIF servicePersistenceIF = ServicePersistenceFactory.criarServicePersistence(type);
        servicePersistenceIF.updateNomeKanban(id, novoNome);
    }
}
