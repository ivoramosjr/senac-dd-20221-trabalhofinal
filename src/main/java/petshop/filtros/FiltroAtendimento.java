package petshop.filtros;

import petshop.model.enums.OrdemPesquisa;
import petshop.model.enums.StatusAtendimentoEnum;

import java.util.ArrayList;
import java.util.List;

public class FiltroAtendimento {

    private String nomePet;

    private Long idServico;

    private String raca;

    private OrdemPesquisa ordemData;

    private List<StatusAtendimentoEnum> status = new ArrayList<>();

    public FiltroAtendimento(){

    }

    public FiltroAtendimento(String nomePet, Long idServico, String raca, OrdemPesquisa ordemData, List<StatusAtendimentoEnum> status) {
        this.nomePet = nomePet;
        this.idServico = idServico;
        this.raca = raca;
        this.ordemData = ordemData;
        this.status = status;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public Long getIdServico() {
        return idServico;
    }

    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public OrdemPesquisa getOrdemData() {
        return ordemData;
    }

    public void setOrdemData(OrdemPesquisa ordemData) {
        this.ordemData = ordemData;
    }

    public List<StatusAtendimentoEnum> getStatus() {
        return status;
    }

    public void setStatus(List<StatusAtendimentoEnum> status) {
        this.status = status;
    }
}
