package petshop.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import petshop.filtros.FiltroAtendimento;
import petshop.filtros.FiltroRelatorioAtendimento;
import petshop.model.dtos.response.AtendimentoRelatorioDTO;
import petshop.model.dtos.response.AtendimentoResponseListagemDTO;
import petshop.model.dtos.response.RelatorioAtendimentoDTO;
import petshop.model.entity.Atendimento;
import petshop.model.enums.StatusAtendimentoEnum;
import petshop.model.enums.TipoAnimal;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class AtendimentoDAO extends GenericRepository{

    private static Logger LOG = LogManager.getLogger(AtendimentoDAO.class);

    public AtendimentoDAO() {
        super();
    }

    public void save(Atendimento atendimento) throws SQLException {
        LOG.info("Salvando atendimento");
        this.getEntityManager().persist(atendimento);
    }

    public List<AtendimentoResponseListagemDTO> findAll(){
        String hql = "SELECT new petshop.model.dtos.response.AtendimentoResponseListagemDTO(" +
                "a.idAtendimento, a.pet.nome, a.pet.raca, a.dataAtendimento, a.servico.nome, a.servico.valor, a.statusAtendimento) " +
                "FROM Atendimento a ";

        return this.getEntityManager()
                .createQuery(hql, AtendimentoResponseListagemDTO.class)
                .getResultList();
    }

    public boolean atendimentoExist(Long idAtendimento){
        Atendimento atendimento = this.find(Atendimento.class, idAtendimento);

        if(atendimento == null)
            return false;

        return true;
    }

    public void delete(Long idAtendimento){
        LOG.info("Delentando atendimento: "+idAtendimento);

        Atendimento atendimento = this.find(Atendimento.class, idAtendimento);

        atendimento.setStatusAtendimento(StatusAtendimentoEnum.DESMARCADO);

        this.merge(atendimento);
    }

    public List<AtendimentoResponseListagemDTO> findWithFilter(FiltroAtendimento filtro){
        String sql = geracaoSQL(filtro);

        Query query = this.getEntityManager().createQuery(sql, AtendimentoResponseListagemDTO.class);

        montandoQuery(filtro, query);

        return query.getResultList();
    }

    private void montandoQuery(FiltroAtendimento filtro, Query query) {
        if(filtro.getNomePet() != null && !filtro.getNomePet().isEmpty()){
            String nome = "%"+ filtro.getNomePet().toLowerCase()+"%";
            query.setParameter("nomePet", nome);
        }

        if(filtro.getIdServico() != null && filtro.getIdServico() != 0){
            query.setParameter("idServico", filtro.getIdServico());
        }

        if(filtro.getRaca() != null && !filtro.getRaca().isEmpty()){
            query.setParameter("raca", filtro.getRaca().toLowerCase());
        }

        if(filtro.getStatus().contains(StatusAtendimentoEnum.AGENDADO)){
            query.setParameter("statusAtendimentoAgendado", StatusAtendimentoEnum.AGENDADO);
        }

        if(filtro.getStatus().contains(StatusAtendimentoEnum.REALIZADO)){
            query.setParameter("statusAtendimentoRealizado", StatusAtendimentoEnum.REALIZADO);
        }
    }

    private String geracaoSQL(FiltroAtendimento filtro) {
        String sql = "SELECT new petshop.model.dtos.response.AtendimentoResponseListagemDTO(" +
                "a.idAtendimento, a.pet.nome, a.pet.raca, a.dataAtendimento, a.servico.nome, a.servico.valor, a.statusAtendimento) " +
                "FROM Atendimento a ";

        String andOrWhere = "WHERE ";

        if(filtro.getNomePet() != null && !filtro.getNomePet().isEmpty()){
            sql = sql.concat(andOrWhere).concat("LOWER(a.pet.nome) like :nomePet ");
            andOrWhere = "AND ";
        }

        if(filtro.getRaca() != null && !filtro.getRaca().isEmpty()){
            sql = sql.concat(andOrWhere).concat("LOWER(a.pet.raca) like :raca ");
            andOrWhere = "AND ";
        }

        if(filtro.getIdServico() != null && filtro.getIdServico() != 0){
            sql = sql.concat(andOrWhere).concat("a.servico.idServico = :idServico ");
            andOrWhere = "AND ";
        }

        if(filtro.getStatus().contains(StatusAtendimentoEnum.AGENDADO)){
            sql = sql.concat(andOrWhere).concat("(a.statusAtendimento = :statusAtendimentoAgendado ");
            andOrWhere = "OR ";
        }

        if(filtro.getStatus().contains(StatusAtendimentoEnum.REALIZADO)){
            sql = sql.concat(andOrWhere).concat("a.statusAtendimento = :statusAtendimentoRealizado ");
        }

        sql = sql.concat(filtro.getStatus().contains(StatusAtendimentoEnum.AGENDADO)?") ":"")
                .concat("ORDER BY a.dataAtendimento ")
                .concat(filtro.getOrdemData().getDescricao());

        return sql;
    }

    public boolean horarioEstaMarcado(Long idAtendimento, LocalDateTime dataAtendimento) {
        String sql = "SELECT ID_ATENDIMENTO FROM Atendimento " +
                "WHERE dataAtendimento = :dataAtendimento ";

        if(idAtendimento != null)
            sql = sql.concat("AND ID_ATENDIMENTO = :idAtendimento");

        Query query = this.getEntityManager()
                .createNativeQuery(sql)
                .setParameter("dataAtendimento", dataAtendimento);

        if(idAtendimento != null)
            query.setParameter("idAtendimento", idAtendimento);

        try {
            BigInteger id = (BigInteger) query.getSingleResult();

            if(idAtendimento != null && idAtendimento == id.longValue())
                return false;

            return true;
        }catch (NoResultException e){
            return false;
        }
    }

    public RelatorioAtendimentoDTO gerarRelatorio(FiltroRelatorioAtendimento filtro) {
        Long numeroAtendimentos = getNumeroAtendimentos();
        Long atendimentosAgendados = getNumeroAtendimentosSatus(StatusAtendimentoEnum.AGENDADO);
        Long atendimentosRealizados = getNumeroAtendimentosSatus(StatusAtendimentoEnum.REALIZADO);
        Long atendimentosCancelados = getNumeroAtendimentosSatus(StatusAtendimentoEnum.DESMARCADO);
        LocalDateTime ultimoAtendimento = getDataUltimoAtendimentoRealizado();
        TipoAnimal tipoAnimal = getTipoAnimalMaisAtendido();
        Double valorTotal = getValorTotalAtendimentosRealizados();
        List<AtendimentoRelatorioDTO> listaAtendimentos = new ArrayList<>();
        if(filtro.isGerarTabela()){
            listaAtendimentos = getAtendimentosRelatorio();
        }
        RelatorioAtendimentoDTO relatorioAtendimentoDTO = new RelatorioAtendimentoDTO(
                numeroAtendimentos, atendimentosAgendados, atendimentosRealizados, atendimentosCancelados,
                ultimoAtendimento, tipoAnimal, valorTotal, listaAtendimentos
        );
        return relatorioAtendimentoDTO;
    }

    private List<AtendimentoRelatorioDTO> getAtendimentosRelatorio() {
        return this.getEntityManager()
                .createQuery("SELECT new petshop.model.dtos.response.AtendimentoRelatorioDTO(a.pet.nome," +
                        "a.pet.tipoAnimal, a.dataAtendimento, a.servico.nome, a.servico.valor, a.statusAtendimento) FROM Atendimento a", AtendimentoRelatorioDTO.class)
                .getResultList();
    }

    private Double getValorTotalAtendimentosRealizados() {
        return (Double) this.getEntityManager()
                .createQuery("SELECT SUM(a.servico.valor) FROM Atendimento a WHERE a.statusAtendimento = :status")
                .setParameter("status", StatusAtendimentoEnum.REALIZADO)
                .getSingleResult();
    }

    private TipoAnimal getTipoAnimalMaisAtendido() {
        //TODO testar mais vezes
        try{
            String sql = "SELECT a.pet.tipoAnimal, COUNT(a.pet.idPet) AS OCORRENCIA FROM Atendimento a WHERE a.statusAtendimento = :status " +
                    "GROUP BY a.pet.tipoAnimal ORDER BY OCORRENCIA DESC";
            Object[] result = (Object[]) this.getEntityManager()
                            .createQuery(sql)
                            .setParameter("status", StatusAtendimentoEnum.REALIZADO)
                            .setMaxResults(1)
                    .getSingleResult();
            TipoAnimal tipoAnimal = (TipoAnimal) result[0];
            return tipoAnimal;
        }catch (NoResultException e){
            return null;
        }
    }

    private LocalDateTime getDataUltimoAtendimentoRealizado(){
        return (LocalDateTime) this.getEntityManager()
                .createQuery("SELECT a.dataAtendimento FROM Atendimento a ORDER BY a.idAtendimento DESC")
                .setMaxResults(1)
                .getSingleResult();
    }

    private Long getNumeroAtendimentosSatus(StatusAtendimentoEnum status) {
        return (Long) this.getEntityManager()
                .createQuery("SELECT COUNT(a) FROM Atendimento a WHERE a.statusAtendimento = :status")
                .setParameter("status", status)
                .getSingleResult();
    }

    private Long getNumeroAtendimentos() {
        return (Long) this.getEntityManager()
                .createQuery("SELECT COUNT(a) FROM Atendimento a")
                .getSingleResult();
    }
}
