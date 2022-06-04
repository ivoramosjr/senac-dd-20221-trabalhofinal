package petshop.model.dtos;

import petshop.model.entity.Servico;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ServicoDTO {

    private Long idServico;

    @NotBlank(message = "Nome não pode estar em branco ou nulo.")
    private String nome;

    @NotNull(message = "O valor do serviço não pode ser nulo.")
    private Double valor;

    @NotBlank(message = "Descrição não pode estar em branco ou nula.")
    private String descricao;

    private Integer quantidadeAtendimentos;

    public ServicoDTO() {
    }

    public ServicoDTO(Servico servico){
        this.idServico = servico.getIdServico();
        this.nome = servico.getNome();
        this.valor = servico.getValor();
        this.descricao = servico.getDescricao();
    }

    public Long getIdServico() {
        return idServico;
    }

    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidadeAtendimentos() {
        return quantidadeAtendimentos;
    }

    public void setQuantidadeAtendimentos(Integer quantidadeAtendimentos) {
        this.quantidadeAtendimentos = quantidadeAtendimentos;
    }
}
