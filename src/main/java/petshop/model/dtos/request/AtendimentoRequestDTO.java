package petshop.model.dtos.request;

import petshop.model.enums.StatusAtendimentoEnum;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class AtendimentoRequestDTO implements Serializable {
    private Long idAtendimento;
    private Long petIdPet;
    private LocalDateTime dataAtendimento;
    private Long servicoIdServico;

    private StatusAtendimentoEnum statusAtendimentoEnum;

    public AtendimentoRequestDTO() {
    }

    public AtendimentoRequestDTO(Long idAtendimento, Long petIdPet, LocalDateTime dataAtendimento, Long servicoIdServico, StatusAtendimentoEnum statusAtendimentoEnum) {
        this.idAtendimento = idAtendimento;
        this.petIdPet = petIdPet;
        this.dataAtendimento = dataAtendimento;
        this.servicoIdServico = servicoIdServico;
        this.statusAtendimentoEnum = statusAtendimentoEnum;
    }

    public Long getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(Long idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public Long getPetIdPet() {
        return petIdPet;
    }

    public void setPetIdPet(Long petIdPet) {
        this.petIdPet = petIdPet;
    }

    public LocalDateTime getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDateTime dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public Long getServicoIdServico() {
        return servicoIdServico;
    }

    public void setServicoIdServico(Long servicoIdServico) {
        this.servicoIdServico = servicoIdServico;
    }

    public StatusAtendimentoEnum getStatusAtendimentoEnum() {
        return statusAtendimentoEnum;
    }

    public void setStatusAtendimentoEnum(StatusAtendimentoEnum statusAtendimentoEnum) {
        this.statusAtendimentoEnum = statusAtendimentoEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtendimentoRequestDTO entity = (AtendimentoRequestDTO) o;
        return Objects.equals(this.idAtendimento, entity.idAtendimento) &&
                Objects.equals(this.petIdPet, entity.petIdPet) &&
                Objects.equals(this.dataAtendimento, entity.dataAtendimento) &&
                Objects.equals(this.servicoIdServico, entity.servicoIdServico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAtendimento, petIdPet, dataAtendimento, servicoIdServico);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idAtendimento = " + idAtendimento + ", " +
                "petIdPet = " + petIdPet + ", " +
                "dataAtendimento = " + dataAtendimento + ", " +
                "servicoIdServico = " + servicoIdServico + ")";
    }
}
