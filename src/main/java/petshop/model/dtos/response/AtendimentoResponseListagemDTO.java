package petshop.model.dtos.response;

import petshop.model.enums.StatusAtendimentoEnum;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class AtendimentoResponseListagemDTO implements Serializable {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
    private static final DecimalFormat df = new DecimalFormat("R$ 0.00");

    private final Long idAtendimento;
    private final String petNome;
    private final String petRaca;
    private final String dataAtendimento;
    private final String horaAtendimento;
    private final String servicoNome;
    private final String servicoValor;
    private final String statusAtendimento;

    public AtendimentoResponseListagemDTO(Long idAtendimento, String petNome, String petRaca, LocalDateTime dataAtendimento, String servicoNome, Double servicoValor, StatusAtendimentoEnum statusAtendimento) {
        this.idAtendimento = idAtendimento;
        this.petNome = petNome;
        this.petRaca = petRaca;
        this.dataAtendimento = dtf.format(dataAtendimento);
        this.horaAtendimento = tf.format(dataAtendimento);
        this.servicoNome = servicoNome;
        this.servicoValor = df.format(servicoValor);
        this.statusAtendimento = statusAtendimento.getNome();
    }

    public Long getIdAtendimento() {
        return idAtendimento;
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

    public String getServicoNome() {
        return servicoNome;
    }

    public String getServicoValor() {
        return servicoValor;
    }

    public String getStatusAtendimento() {
        return statusAtendimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtendimentoResponseListagemDTO entity = (AtendimentoResponseListagemDTO) o;
        return Objects.equals(this.idAtendimento, entity.idAtendimento) &&
                Objects.equals(this.petNome, entity.petNome) &&
                Objects.equals(this.petRaca, entity.petRaca) &&
                Objects.equals(this.servicoNome, entity.servicoNome) &&
                Objects.equals(this.servicoValor, entity.servicoValor) &&
                Objects.equals(this.statusAtendimento, entity.statusAtendimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAtendimento,petNome, petRaca, servicoNome, servicoValor, statusAtendimento);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idAtendimento = " + idAtendimento + ", " +
                "petNome = " + petNome + ", " +
                "petRaca = " + petRaca + ", " +
                "servicoNome = " + servicoNome + ", " +
                "servicoValor = " + servicoValor + ", " +
                "statusAtendimento = " + statusAtendimento + ")";
    }
}
