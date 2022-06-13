package petshop.model.dtos.response;

import petshop.model.entity.Servico;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Objects;

public class ServicoResponseRelatorioDTO implements Serializable {
    private static final DecimalFormat df = new DecimalFormat("R$ 0.00");

    private final Long idServico;
    private final String nome;
    private final String valor;
    private final String descricao;
    private final String status;

    public ServicoResponseRelatorioDTO(Servico servico){
        this.idServico = servico.getIdServico();
        this.nome = servico.getNome();
        this.valor = df.format(servico.getValor());
        this.descricao = servico.getDescricao();
        this.status = servico.getStatus() == true? "Ativo":"Inativo";
    }

    public Long getIdServico() {
        return idServico;
    }

    public String getNome() {
        return nome;
    }

    public String getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicoResponseRelatorioDTO entity = (ServicoResponseRelatorioDTO) o;
        return Objects.equals(this.idServico, entity.idServico) &&
                Objects.equals(this.nome, entity.nome) &&
                Objects.equals(this.valor, entity.valor) &&
                Objects.equals(this.descricao, entity.descricao) &&
                Objects.equals(this.status, entity.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idServico, nome, valor, descricao, status);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idServico = " + idServico + ", " +
                "nome = " + nome + ", " +
                "valor = " + valor + ", " +
                "descricao = " + descricao + ", " +
                "status = " + status + ")";
    }
}
