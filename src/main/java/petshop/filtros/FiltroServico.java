package petshop.filtros;

import petshop.model.enums.OrdemPesquisa;

public class FiltroServico {

    private String nome;

    private OrdemPesquisa ordemValor;

    private OrdemPesquisa ordemQuantidadeAtendimentos;

    public FiltroServico() {
    }

    public FiltroServico(String nome, OrdemPesquisa ordemValor, OrdemPesquisa ordemQuantidadeAtendimentos) {
        this.nome = nome;
        this.ordemValor = ordemValor;
        this.ordemQuantidadeAtendimentos = ordemQuantidadeAtendimentos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public OrdemPesquisa getOrdemValor() {
        return ordemValor;
    }

    public void setOrdemValor(OrdemPesquisa ordemValor) {
        this.ordemValor = ordemValor;
    }

    public OrdemPesquisa getOrdemQuantidadeAtendimentos() {
        return ordemQuantidadeAtendimentos;
    }

    public void setOrdemQuantidadeAtendimentos(OrdemPesquisa ordemQuantidadeAtendimentos) {
        this.ordemQuantidadeAtendimentos = ordemQuantidadeAtendimentos;
    }
}
