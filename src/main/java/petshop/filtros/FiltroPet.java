package petshop.filtros;

import petshop.model.enums.OrdemPesquisa;
import petshop.model.enums.SexoEnum;

public class FiltroPet {

    private String nome;

    private String nomeRaca;

    private SexoEnum sexoEnum;

    private OrdemPesquisa ordemFidelidade;

    public FiltroPet() {
    }

    public FiltroPet(String nome, String nomeRaca, SexoEnum sexo, OrdemPesquisa ordemFidelidade) {
        this.nome = nome;
        this.nomeRaca = nomeRaca;
        this.sexoEnum = sexo;
        this.ordemFidelidade = ordemFidelidade;
    }

    public SexoEnum getSexoEnum() {
        return sexoEnum;
    }

    public void setSexoEnum(SexoEnum sexoEnum) {
        this.sexoEnum = sexoEnum;
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
