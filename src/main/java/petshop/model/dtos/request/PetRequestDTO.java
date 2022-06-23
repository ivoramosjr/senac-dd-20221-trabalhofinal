package petshop.model.dtos.request;

import petshop.model.enums.SexoEnum;
import petshop.model.enums.TipoAnimal;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class PetRequestDTO implements Serializable {
    private Long idPet;
    private String nome;
    private LocalDate dataNascimento;
    private String nomeDono;
    private String raca;
    private TipoAnimal tipoAnimal;
    private SexoEnum sexo;

    public PetRequestDTO() {
    }

    public PetRequestDTO(Long idPet, String nome, LocalDate dataNascimento, String nomeDono, String raca, TipoAnimal tipoAnimal, SexoEnum sexo) {
        this.idPet = idPet;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nomeDono = nomeDono;
        this.raca = raca;
        this.tipoAnimal = tipoAnimal;
        this.sexo = sexo;
    }

    public Long getIdPet() {
        return idPet;
    }

    public void setIdPet(Long idPet) {
        this.idPet = idPet;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNomeDono() {
        return nomeDono;
    }

    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public TipoAnimal getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(TipoAnimal tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public SexoEnum getSexo() {
        return sexo;
    }

    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetRequestDTO entity = (PetRequestDTO) o;
        return Objects.equals(this.idPet, entity.idPet) &&
                Objects.equals(this.nome, entity.nome) &&
                Objects.equals(this.dataNascimento, entity.dataNascimento) &&
                Objects.equals(this.nomeDono, entity.nomeDono) &&
                Objects.equals(this.raca, entity.raca) &&
                Objects.equals(this.tipoAnimal, entity.tipoAnimal) &&
                Objects.equals(this.sexo, entity.sexo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPet, nome, dataNascimento, nomeDono, raca, tipoAnimal, sexo);
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
                "sexo = " + sexo + ")";
    }
}
