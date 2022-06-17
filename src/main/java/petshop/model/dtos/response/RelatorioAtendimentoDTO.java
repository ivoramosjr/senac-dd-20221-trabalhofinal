package petshop.model.dtos.response;

import petshop.model.enums.TipoAnimal;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RelatorioAtendimentoDTO implements Serializable {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DecimalFormat df = new DecimalFormat("R$ 0.00");

    private final Long numeroAtendimentos;
    private final Long atendimentosAgendados;
    private final Long atendimentosRealizados;
    private final Long atendimentosCancelados;
    private final String ultimoAtendimento;
    private final String tipoPetMaisAtendido;
    private final String valorTotalAtendimentosRealizados;
    private final List<AtendimentoRelatorioDTO> listaAtendimentos;

    public RelatorioAtendimentoDTO(Long numeroAtendimentos, Long atendimentosAgendados, Long atendimentosRealizados,
                                   Long atendimentosCancelados, LocalDateTime dataUltimoAtendimento, TipoAnimal tipoAnimal,
                                   Double valorTotal, List<AtendimentoRelatorioDTO> atendimentos){
        this.numeroAtendimentos = numeroAtendimentos;
        this.atendimentosAgendados = atendimentosAgendados;
        this.atendimentosRealizados = atendimentosRealizados;
        this.atendimentosCancelados = atendimentosCancelados;
        this.ultimoAtendimento = dataUltimoAtendimento == null? "Nenhum atendimento":dtf.format(dataUltimoAtendimento);
        this.tipoPetMaisAtendido = tipoAnimal == null? "Nenhum atendimento":tipoAnimal.getNome();
        this.valorTotalAtendimentosRealizados = valorTotal == null? df.format(0):df.format(valorTotal);
        this.listaAtendimentos = atendimentos;
    }

    public Long getNumeroAtendimentos() {
        return numeroAtendimentos;
    }

    public Long getAtendimentosAgendados() {
        return atendimentosAgendados;
    }

    public Long getAtendimentosRealizados() {
        return atendimentosRealizados;
    }

    public Long getAtendimentosCancelados() {
        return atendimentosCancelados;
    }

    public String getUltimoAtendimento() {
        return ultimoAtendimento;
    }

    public String getTipoPetMaisAtendido() {
        return tipoPetMaisAtendido;
    }

    public String getValorTotalAtendimentosRealizados() {
        return valorTotalAtendimentosRealizados;
    }

    public List<AtendimentoRelatorioDTO> getListaAtendimentos() {
        return listaAtendimentos;
    }
}
