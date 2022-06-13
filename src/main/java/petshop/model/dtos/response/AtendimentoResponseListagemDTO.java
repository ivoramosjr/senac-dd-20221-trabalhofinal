package petshop.model.dtos.response;

import petshop.model.enums.StatusAtendimentoEnum;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class AtendimentoResponseListagemDTO implements Serializable {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormat df = new DecimalFormat("R$ 0.00");

    private final Long idAtendimento;
    private final Long petIdPet;
    private final String petNome;
    private final String petRaca;
    private final String dataAtendimento;
    private final String horaAtendimento;
    private final Long servicoIdServico;
    private final String servicoNome;
    private final String servicoValor;
    private final StatusAtendimentoEnum statusAtendimento;

    public AtendimentoResponseListagemDTO(Long idAtendimento, Long petIdPet, String petNome, String petRaca, LocalDateTime dataAtendimento, Long servicoIdServico, String servicoNome, Double servicoValor, StatusAtendimentoEnum statusAtendimento) {
        this.idAtendimento = idAtendimento;
        this.petIdPet = petIdPet;
        this.petNome = petNome;
        this.petRaca = petRaca;
        this.dataAtendimento = dtf.format(dataAtendimento);
        this.horaAtendimento = dataAtendimento.getHour()+":"+dataAtendimento.getMinute();
        this.servicoIdServico = servicoIdServico;
        this.servicoNome = servicoNome;
        this.servicoValor = df.format(servicoValor);
        this.statusAtendimento = statusAtendimento;
    }

    public Long getIdAtendimento() {
        return idAtendimento;
    }

    public Long getPetIdPet() {
        return petIdPet;
    }

    public String getPetNome() {
        return petNome;
    }

    public String getPetRaca() {
        return petRaca;
    }

    public String getDataAtendimento() {
        return dataAtendimento;
    }

    public String getHoraAtendimento(){
        return horaAtendimento;
    }

    public Long getServicoIdServico() {
        return servicoIdServico;
    }

    public String getServicoNome() {
        return servicoNome;
    }

    public String getServicoValor() {
        return servicoValor;
    }

    public StatusAtendimentoEnum getStatusAtendimento() {
        return statusAtendimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtendimentoResponseListagemDTO entity = (AtendimentoResponseListagemDTO) o;
        return Objects.equals(this.idAtendimento, entity.idAtendimento) &&
                Objects.equals(this.petIdPet, entity.petIdPet) &&
                Objects.equals(this.petNome, entity.petNome) &&
                Objects.equals(this.petRaca, entity.petRaca) &&
                Objects.equals(this.servicoIdServico, entity.servicoIdServico) &&
                Objects.equals(this.servicoNome, entity.servicoNome) &&
                Objects.equals(this.servicoValor, entity.servicoValor) &&
                Objects.equals(this.statusAtendimento, entity.statusAtendimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAtendimento, petIdPet, petNome, petRaca, servicoIdServico, servicoNome, servicoValor, statusAtendimento);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idAtendimento = " + idAtendimento + ", " +
                "petIdPet = " + petIdPet + ", " +
                "petNome = " + petNome + ", " +
                "petRaca = " + petRaca + ", " +
                "servicoIdServico = " + servicoIdServico + ", " +
                "servicoNome = " + servicoNome + ", " +
                "servicoValor = " + servicoValor + ", " +
                "statusAtendimento = " + statusAtendimento + ")";
    }
}