package petshop.model.dtos.response;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class AtendimentoRegistroDTO implements Serializable {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DecimalFormat df = new DecimalFormat("R$ 0.00");

    private final Long idAtendimento;
    private final String petNome;
    private final String dataAtendimento;
    private final String servicoNome;
    private final String servicoValor;

    public AtendimentoRegistroDTO(Long idAtendimento, String petNome, LocalDateTime dataAtendimento, String servicoNome, Double servicoValor) {
        this.idAtendimento = idAtendimento;
        this.petNome = petNome;
        this.dataAtendimento = dtf.format(dataAtendimento);
        this.servicoNome = servicoNome;
        this.servicoValor = df.format(servicoValor);
    }

    public Long getIdAtendimento() {
        return idAtendimento;
    }

    public String getPetNome() {
        return petNome;
    }

    public String getDataAtendimento() {
        return dataAtendimento;
    }

    public String getServicoNome() {
        return servicoNome;
    }

    public String getServicoValor() {
        return servicoValor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtendimentoRegistroDTO entity = (AtendimentoRegistroDTO) o;
        return Objects.equals(this.idAtendimento, entity.idAtendimento) &&
                Objects.equals(this.petNome, entity.petNome) &&
                Objects.equals(this.dataAtendimento, entity.dataAtendimento) &&
                Objects.equals(this.servicoNome, entity.servicoNome) &&
                Objects.equals(this.servicoValor, entity.servicoValor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAtendimento, petNome, dataAtendimento, servicoNome, servicoValor);
    }

    @Override
    public String toString() {
        return "ID Atendimento: "+this.getIdAtendimento();
    }
}
