package model.enums;

public enum TipoAnimal {
	
	CACHORRO("Cachorro"),
	GATO("Gato"),
	RATO("Rato"),
	LONTRA("Lontra"),
	FURAO("Furão");
	
	private String nome;
	
	private TipoAnimal(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}
	
}
