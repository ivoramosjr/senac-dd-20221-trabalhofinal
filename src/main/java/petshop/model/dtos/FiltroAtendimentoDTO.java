package petshop.model.dtos;

import petshop.model.enums.OrdemPesquisa;
import petshop.model.enums.StatusAtendimentoEnum;

public class FiltroAtendimentoDTO {

    private String nome;

    private String nomeRaca;

    private OrdemPesquisa ordemData;

    private StatusAtendimentoEnum statusAntedimento = StatusAtendimentoEnum.AGENDADO;

    public FiltroAtendimentoDTO(String nome, String nomeRaca, OrdemPesquisa ordemData, StatusAtendimentoEnum statusAntedimento){
        this.nome = nome;
        this.nomeRaca = nomeRaca;
        this.ordemData = ordemData;
        this.statusAntedimento = statusAntedimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeRaca() {
        return nomeRaca;
    }

    public void setNomeRaca(String nomeRaca) {
        this.nomeRaca = nomeRaca;
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
