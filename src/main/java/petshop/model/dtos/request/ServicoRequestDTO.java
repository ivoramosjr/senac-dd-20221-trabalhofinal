package petshop.model.dtos.request;

import petshop.model.dtos.response.ServicoResponseDTO;

import java.io.Serializable;
import java.util.Objects;

public class ServicoRequestDTO implements Serializable {

    private Long idServico;
    private String nome;
    private Double valor;
    private String descricao;

    public ServicoRequestDTO() {

    }

    public ServicoRequestDTO(Long idServico, String nome, Double valor, String descricao) {
        this.idServico = idServico;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
    }

    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdServico() {
        return idServico;
    }

    public String getNome() {
        return nome;
    }

    public Double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicoRequestDTO entity = (ServicoRequestDTO) o;
        return Objects.equals(this.idServico, entity.idServico) &&
                Objects.equals(this.nome, entity.nome) &&
                Objects.equals(this.valor, entity.valor) &&
                Objects.equals(this.descricao, entity.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idServico, nome, valor, descricao);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idServico = " + idServico + ", " +
                "nome = " + nome + ", " +
                "valor = " + valor + ", " +
                "descricao = " + descricao + ")";
    }
}
