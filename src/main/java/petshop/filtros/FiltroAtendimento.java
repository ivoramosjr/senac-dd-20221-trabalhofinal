package petshop.filtros;

import petshop.model.enums.OrdemPesquisa;
import petshop.model.enums.StatusAtendimentoEnum;

public class FiltroAtendimento {

    private String nomePet;

    private String nomeServico;

    private OrdemPesquisa ordemData;

    private StatusAtendimentoEnum statusAntedimento;

    public FiltroAtendimento(){

    }

    public FiltroAtendimento(String nomePet, String nomeServico, OrdemPesquisa ordemData, StatusAtendimentoEnum statusAntedimento){
        this.nomePet = nomePet;
        this.nomeServico = nomeServico;
        this.ordemData = ordemData;
        this.statusAntedimento = statusAntedimento;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public OrdemPesquisa getOrdemData() {
        return ordemData;
    }

    public void setOrdemData(OrdemPesquisa ordemData) {
        this.ordemData = ordemData;
    }

    public StatusAtendimentoEnum getStatusAntedimento() {
        return statusAntedimento;
    }

    public void setStatusAntedimento(StatusAtendimentoEnum statusAntedimento) {
        this.statusAntedimento = statusAntedimento;
    }
}
