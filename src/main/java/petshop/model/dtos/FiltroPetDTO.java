package petshop.model.dtos;

import petshop.model.enums.OrdemPesquisa;

public class FiltroPetDTO {

    private String nome;

    private String nomeRaca;

    private OrdemPesquisa ordemFidelidade;

    public FiltroPetDTO() {
    }

    public FiltroPetDTO(String nome, String nomeRaca, OrdemPesquisa ordemFidelidade) {
        this.nome = nome;
        this.nomeRaca = nomeRaca;
        this.ordemFidelidade = ordemFidelidade;
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

    public OrdemPesquisa getOrdemFidelidade() {
        return ordemFidelidade;
    }

    public void setOrdemFidelidade(OrdemPesquisa ordemFidelidade) {
        this.ordemFidelidade = ordemFidelidade;
    }
}
