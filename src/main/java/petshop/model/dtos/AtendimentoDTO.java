package petshop.model.dtos;

import petshop.model.entity.Atendimento;
import petshop.model.enums.StatusAtendimentoEnum;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AtendimentoDTO {

    private Long idAtendimento;

    private LocalDateTime dataAtendimento;

    @NotNull(message = "O status do atendimento não pode ser nulo.")
    private StatusAtendimentoEnum statusAtendimento;

    public AtendimentoDTO() {
    }

    public AtendimentoDTO(Atendimento atendimento){
        this.idAtendimento = atendimento.getIdAtendimento();
        this.dataAtendimento = atendimento.getDataAtendimento();
        //TODO ARRUMAR ISSO AQUI
        this.statusAtendimento = atendimento.getStatusAtendimento();
    }

    public Long getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(Long idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public LocalDateTime getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDateTime dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public StatusAtendimentoEnum getStatusAtendimento() {
        return statusAtendimento;
    }

    public void setStatusAtendimento(StatusAtendimentoEnum statusAtendimento) {
        this.statusAtendimento = statusAtendimento;
    }
}
