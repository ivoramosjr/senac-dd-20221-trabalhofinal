package petshop.model.dtos.response;

import petshop.model.enums.SexoEnum;

import java.io.Serializable;
import java.util.Objects;

public class PetResponseListagemDTO implements Serializable {

    private final Long idPet;
    private final String nome;
    private final String nomeDono;
    private final String raca;
    private final SexoEnum sexo;

    public PetResponseListagemDTO(Long idPet, String nome, String nomeDono, String raca, SexoEnum sexo) {
        this.idPet = idPet;
        this.nome = nome;
        this.nomeDono = nomeDono;
        this.raca = raca;
        this.sexo = sexo;
    }

    public Long getIdPet() {
        return idPet;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeDono() {
        return nomeDono;
    }

    public SexoEnum getSexo() {
        return sexo;
    }

    public String getRaca() {
        return raca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetResponseListagemDTO entity = (PetResponseListagemDTO) o;
        return Objects.equals(this.idPet, entity.idPet) &&
                Objects.equals(this.nome, entity.nome) &&
                Objects.equals(this.nomeDono, entity.nomeDono) &&
                Objects.equals(this.sexo, entity.sexo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPet, nome, nomeDono, sexo);
    }

    @Override
    public String toString() {
        return "Nome: "+nome+" - Dono: "+nomeDono;
    }
}
