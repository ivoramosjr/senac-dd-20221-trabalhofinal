package petshop.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petshop.model.dtos.FiltroServicoDTO;
import petshop.model.dtos.ServicoDTO;
import petshop.model.entity.Servico;
import petshop.model.enums.OrdemPesquisa;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ServicoDAO extends GenericRepository{

    private static Logger LOG = LogManager.getLogger(ServicoDAO.class);

    public ServicoDAO() {
        super();
    }

    public void save(Servico servico) throws SQLException {
        LOG.info("Salvando serviço: "+servico.getNome());
        this.getEntityManager().persist(servico);
    }

    public List<ServicoDTO> findAll(){
        String hql = generateBaseHQL();
        List<ServicoDTO> servicos = this.getEntityManager().createQuery(hql, ServicoDTO.class).getResultList();
        return servicos;
    }

    public boolean servicoExists(Long idServico){
        Servico servico = this.getEntityManager().find(Servico.class, idServico);

        if(servico == null)
            return false;

        return true;
    }

    public List<ServicoDTO> findWithFilter(FiltroServicoDTO filtro){
        String hql = geracaoHQL(filtro);

        Query query = this.getEntityManager().createQuery(hql, ServicoDTO.class);

        montandoQuery(filtro, query);

        List<ServicoDTO> servicos = query.getResultList();

        if(filtro.getOrdemQuantidadeAtendimentos().equals(OrdemPesquisa.DESC)){
            servicos = servicos.stream()
                    .sorted(Comparator
                            .comparingLong(ServicoDTO::getQuantidadeAtendimentos)
                            .reversed())
                    .collect(Collectors.toList());
        }else{
            servicos = servicos.stream()
                    .sorted(Comparator
                            .comparingLong(ServicoDTO::getQuantidadeAtendimentos))
                    .collect(Collectors.toList());
        }

        return servicos;
    }

    private void montandoQuery(FiltroServicoDTO filtro, Query query) {
        if(filtro.getNome() != null && !filtro.getNome().isEmpty()){
            String nome = "%"+filtro.getNome().toLowerCase()+"%";
            query.setParameter("nome", nome);
        }
    }

    private String geracaoHQL(FiltroServicoDTO filtro) {
        String hql = generateBaseHQL();

        if(filtro.getNome() != null && !filtro.getNome().isEmpty()){
            hql = hql.concat("AND LOWER(s.nome) LIKE :nome ");
        }

        hql = hql.concat("ORDER BY s.valor ").concat(filtro.getOrdemValor().getDescricao());

        return hql;
    }

    private String generateBaseHQL() {
        return "SELECT new petshop.model.dtos.ServicoDTO(s.id, s.nome, s.valor, s.descricao, " +
                "(SELECT COUNT(a.idAtendimento) AS QTD_ATENDIMENTOS FROM Atendimento a WHERE a.servico.idServico = s.idServico)) " +
                "FROM Servico s " +
                "WHERE s.status = true ";
    }

}
