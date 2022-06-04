package petshop.model.dtos;

import petshop.model.entity.Atendimento;
import petshop.model.enums.StatusAtendimentoEnum;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AtendimentoDTO {

    private Long idAtendimento;

    @NotNull(message = "O pet não pode ser nulo.")
    private PetDTO pet;

    private LocalDateTime dataAtendimento;

    @NotNull(message = "O serviço não pode ser nulo.")
    private ServicoDTO servico;

    @NotNull(message = "O status do atendimento não pode ser nulo.")
    private StatusAtendimentoEnum statusAtendimento;

    public AtendimentoDTO() {
    }

    public AtendimentoDTO(Atendimento atendimento){
        this.idAtendimento = atendimento.getIdAtendimento();
        this.dataAtendimento = atendimento.getDataAtendimento();
        this.pet = new PetDTO(atendimento.getPet());
        this.servico = new ServicoDTO(atendimento.getServico());
        this.statusAtendimento = atendimento.getStatusAtendimento();
    }

    public Long getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(Long idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public PetDTO getPet() {
        return pet;
    }

    public void setPet(PetDTO pet) {
        this.pet = pet;
    }

    public LocalDateTime getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDateTime dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public ServicoDTO getServico() {
        return servico;
    }

    public void setServico(ServicoDTO servico) {
        this.servico = servico;
    }

    public StatusAtendimentoEnum getStatusAtendimento() {
        return statusAtendimento;
    }

    public void setStatusAtendimento(StatusAtendimentoEnum statusAtendimento) {
        this.statusAtendimento = statusAtendimento;
    }
}
