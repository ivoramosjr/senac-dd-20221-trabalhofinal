package petshop.model.dtos.response;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Objects;

public class ServicoResponseDTO implements Serializable {
    private static final DecimalFormat df = new DecimalFormat("R$ 0.00");

    private final Long idServico;
    private final String nome;
    private final String valor;
    private final Long quantidadeAtendimentos;

    public ServicoResponseDTO(Long idServico, String nome, Double valor, Long quantidadeAtendimentos) {
        this.idServico = idServico;
        this.nome = nome;
        this.valor = df.format(valor);
        this.quantidadeAtendimentos = quantidadeAtendimentos.longValue();
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

    public Long getQuantidadeAtendimentos() {
        return quantidadeAtendimentos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicoResponseDTO entity = (ServicoResponseDTO) o;
        return Objects.equals(this.idServico, entity.idServico) &&
                Objects.equals(this.nome, entity.nome) &&
                Objects.equals(this.valor, entity.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idServico, nome, valor);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idServico = " + idServico + ", " +
                "nome = " + nome + ", " +
                "valor = " + valor + ")";
    }
}
