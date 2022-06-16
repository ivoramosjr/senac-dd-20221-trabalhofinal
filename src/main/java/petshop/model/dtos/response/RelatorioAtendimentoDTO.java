package petshop.model.dtos.response;

import petshop.model.enums.TipoAnimal;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RelatorioAtendimentoDTO implements Serializable {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormat df = new DecimalFormat("R$ 0.00");

    private final Integer numeroAtendimentos;
    private final Integer atendimentosAgendados;
    private final Integer atendimentosRealizados;
    private final Integer atendimentosCancelados;
    private final String ultimoAtendimento;
    private final String tipoPetMaisAtendido;
    private final String valorTotalAtendimentos;
    private final List<AtendimentoRelatorioDTO> listaAtendimentos;

    public RelatorioAtendimentoDTO(Integer numeroAtendimentos, Integer atendimentosAgendados, Integer atendimentosRealizados,
                                   Integer atendimentosCancelados, LocalDateTime dataUltimoAtendimento, TipoAnimal tipoAnimal,
                                   Double valorTotal, List<AtendimentoRelatorioDTO> atendimentos){
        this.numeroAtendimentos = numeroAtendimentos;
        this.atendimentosAgendados = atendimentosAgendados;
        this.atendimentosRealizados = atendimentosRealizados;
        this.atendimentosCancelados = atendimentosCancelados;
        this.ultimoAtendimento = dtf.format(dataUltimoAtendimento);
        this.tipoPetMaisAtendido = tipoAnimal.getNome();
        this.valorTotalAtendimentos = df.format(valorTotal);
        this.listaAtendimentos = atendimentos;
    }

    public Integer getNumeroAtendimentos() {
        return numeroAtendimentos;
    }

    public Integer getAtendimentosAgendados() {
        return atendimentosAgendados;
    }

    public Integer getAtendimentosRealizados() {
        return atendimentosRealizados;
    }

    public Integer getAtendimentosCancelados() {
        return atendimentosCancelados;
    }

    public String getUltimoAtendimento() {
        return ultimoAtendimento;
    }

    public String getTipoPetMaisAtendido() {
        return tipoPetMaisAtendido;
    }

    public String getValorTotalAtendimentos() {
        return valorTotalAtendimentos;
    }

    public List<AtendimentoRelatorioDTO> getListaAtendimentos() {
        return listaAtendimentos;
    }
}
