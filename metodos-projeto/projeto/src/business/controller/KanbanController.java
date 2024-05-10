package controller;


import domain.Kanban;
import infra.service.ServicePersistenceFactory;
import infra.service.ServicePersistenceIF;

public class KanbanController {
    private final String type ;

    public KanbanController(String bdType){
        this.type = bdType;
    }
    
    public Kanban getKanban(int idProjeto, String nome){
        return Kanban.createKanban(idProjeto, nome);
    }

    // Método para adicionar um novo quadro Kanban
    public void CreateKanban(Kanban kanban) {
        ServicePersistenceIF servicePersistenceIF = ServicePersistenceFactory.criarServicePersistence(type);
        servicePersistenceIF.createKanban(kanban);
    }

    // Método para atualizar um quadro Kanban existente
    public void atualizarKanban(int id, String novoNome) {
        ServicePersistenceIF servicePersistenceIF = ServicePersistenceFactory.criarServicePersistence(type);
        servicePersistenceIF.updateNomeKanban(id, novoNome);
    }
}
