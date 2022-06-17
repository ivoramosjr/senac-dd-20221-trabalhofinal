package petshop.model.dtos.response;

import petshop.model.enums.StatusAtendimentoEnum;
import petshop.model.enums.TipoAnimal;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class AtendimentoRelatorioDTO implements Serializable {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormat df = new DecimalFormat("R$ 0.00");

    private final String petNome;
    private final String petTipoAnimal;
    private final String dataAtendimento;
    private final String servicoNome;
    private final String servicoValor;
    private final String statusAtendimento;

    public AtendimentoRelatorioDTO(String petNome, TipoAnimal petTipoAnimal, LocalDateTime dataAtendimento, String servicoNome, Double servicoValor, StatusAtendimentoEnum statusAtendimento) {
        this.petNome = petNome;
        this.petTipoAnimal = petTipoAnimal.getNome();
        this.dataAtendimento = dtf.format(dataAtendimento);
        this.servicoNome = servicoNome;
        this.servicoValor = df.format(servicoValor);
        this.statusAtendimento = statusAtendimento.getNome();
    }

    public String getPetNome() {
        return petNome;
    }

    public String getPetTipoAnimal() {
        return petTipoAnimal;
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

    public String getStatusAtendimento() {
        return statusAtendimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtendimentoRelatorioDTO entity = (AtendimentoRelatorioDTO) o;
        return Objects.equals(this.petNome, entity.petNome) &&
                Objects.equals(this.petTipoAnimal, entity.petTipoAnimal) &&
                Objects.equals(this.dataAtendimento, entity.dataAtendimento) &&
                Objects.equals(this.servicoNome, entity.servicoNome) &&
                Objects.equals(this.servicoValor, entity.servicoValor) &&
                Objects.equals(this.statusAtendimento, entity.statusAtendimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petNome, petTipoAnimal, dataAtendimento, servicoNome, servicoValor, statusAtendimento);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "petNome = " + petNome + ", " +
                "petTipoAnimal = " + petTipoAnimal + ", " +
                "dataAtendimento = " + dataAtendimento + ", " +
                "servicoNome = " + servicoNome + ", " +
                "servicoValor = " + servicoValor + ", " +
                "statusAtendimento = " + statusAtendimento + ")";
    }
}
