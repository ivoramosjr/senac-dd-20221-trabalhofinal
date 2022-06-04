package petshop.model.dtos;

import petshop.model.entity.Pet;
import petshop.model.enums.TipoAnimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PetDTO {

    private Long idPet;

    @NotBlank(message = "O nome não pode estar em branco ou nulo!")
    private String nome;

    @NotNull(message = "Data de nascimento não pode ser nula!")
    private LocalDate dataNascimento;

    private String nomeDono;

    private String raca;

    @NotNull(message = "Tipo do animal não pode ser nulo!")
    private TipoAnimal tipoAnimal;

    @Min(value = 0, message = "Pontos de fidelidades não pode ser negativos!")
    private int pontosFidelidade;

    public PetDTO() {
    }

    public PetDTO(Pet pet){
        this.idPet = pet.getIdPet();
        this.nome = pet.getNome();
        this.dataNascimento = pet.getDataNascimento();
        this.nomeDono = pet.getNomeDono();
        this.raca = pet.getRaca();
        this.tipoAnimal = pet.getTipoAnimal();
        this.pontosFidelidade = pet.getPontosFidelidade();
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

    public int getPontosFidelidade() {
        return pontosFidelidade;
    }

    public void setPontosFidelidade(int pontosFidelidade) {
        this.pontosFidelidade = pontosFidelidade;
    }
}
