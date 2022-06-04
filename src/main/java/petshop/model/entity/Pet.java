package petshop.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import petshop.model.enums.TipoAnimal;

@Entity
public class Pet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PET")
	private Long idPet;
	
	private String nome;
	
	@Column(name = "DT_NASCIMENTO")
	private LocalDate dataNascimento;
	
	@Column(name = "NOME_DONO")
	private String nomeDono;
	
	private String raca;
	
	@Enumerated(EnumType.STRING)
	private TipoAnimal tipoAnimal;

	private Integer pontosFidelidade = 0;

	public Pet() {
		this.pontosFidelidade = 0;
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
