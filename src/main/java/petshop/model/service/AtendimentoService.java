package petshop.model.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.OptimisticLock;
import petshop.connection.JpaConnectionFactory;
import petshop.model.dao.AtendimentoDAO;
import petshop.model.dao.PetDAO;
import petshop.model.dao.ServicoDAO;
import petshop.model.entity.Atendimento;
import petshop.model.entity.Pet;
import petshop.model.entity.Servico;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.Optional;

public class AtendimentoService {


    private static Logger LOG = LogManager.getLogger(PetService.class);
    private EntityManager entityManager = new JpaConnectionFactory().getEntityManager();
    PetService petDAO;
    ServicoDAO servicoDAO;
    private AtendimentoDAO atendimentoDAO;

    public AtendimentoService() {
        this.atendimentoDAO = new AtendimentoDAO(this.entityManager);
        this.petDAO = new PetService();
        this.servicoDAO = new ServicoDAO(this.entityManager);
    }

    //TODO vai receber um AtendimentoDTO no lugar do atendimento
    public void save(Atendimento atendimento){
        //TODO validações TOP
        System.out.println("Agora o codigo está bem melhor!!");
        try {
            //TODO receberemos um AtendimentoDTO
            this.atendimentoDAO.getEntityManager().getTransaction().begin();
            Pet pet = this.entityManager.find(Pet.class, atendimento.getPet().getIdPet());
            Servico servico = this.entityManager.find(Servico.class, atendimento.getServico().getIdServico());
            atendimento.setServico(servico);
            atendimento.setPet(pet);
            this.atendimentoDAO.save(atendimento);
            this.atendimentoDAO.getEntityManager().getTransaction().commit();
        }catch (SQLException e) {
            LOG.error("Deu ruim: "+e.getMessage());
        }
    }
}
