package petshop.model.dtos.response;

import petshop.model.entity.Pet;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class PetResponseRelatorioDTO implements Serializable {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final Long idPet;
    private final String nome;
    private final String dataNascimento;
    private final String nomeDono;
    private final String raca;
    private final String tipoAnimal;
    private final String sexo;
    private final String ativo;
    private final Integer pontosFidelidade;

    public PetResponseRelatorioDTO(Pet pet){
        this.idPet = pet.getIdPet();
        this.nome = pet.getNome();
        this.dataNascimento = dtf.format(pet.getDataNascimento());
        this.nomeDono = pet.getNomeDono();
        this.raca = pet.getRaca();
        this.tipoAnimal = pet.getTipoAnimal().getNome();
        this.sexo = pet.getSexo().getNome();
        this.ativo = pet.isAtivo()?"Ativo":"Inativo";
        this.pontosFidelidade = pet.getPontosFidelidade();
    }

    public Long getIdPet() {
        return idPet;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getNomeDono() {
        return nomeDono;
    }

    public String getRaca() {
        return raca;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public String getSexo() {
        return sexo;
    }

    public String getAtivo() {
        return ativo;
    }

    public Integer getPontosFidelidade() {
        return pontosFidelidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetResponseRelatorioDTO entity = (PetResponseRelatorioDTO) o;
        return Objects.equals(this.idPet, entity.idPet) &&
                Objects.equals(this.nome, entity.nome) &&
                Objects.equals(this.dataNascimento, entity.dataNascimento) &&
                Objects.equals(this.nomeDono, entity.nomeDono) &&
                Objects.equals(this.raca, entity.raca) &&
                Objects.equals(this.tipoAnimal, entity.tipoAnimal) &&
                Objects.equals(this.sexo, entity.sexo) &&
                Objects.equals(this.ativo, entity.ativo) &&
                Objects.equals(this.pontosFidelidade, entity.pontosFidelidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPet, nome, dataNascimento, nomeDono, raca, tipoAnimal, sexo, ativo, pontosFidelidade);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idPet = " + idPet + ", " +
                "nome = " + nome + ", " +
                "dataNascimento = " + dataNascimento + ", " +
                "nomeDono = " + nomeDono + ", " +
                "raca = " + raca + ", " +
                "tipoAnimal = " + tipoAnimal + ", " +
                "sexo = " + sexo + ", " +
                "ativo = " + ativo + ", " +
                "pontosFidelidade = " + pontosFidelidade + ")";
    }
}
