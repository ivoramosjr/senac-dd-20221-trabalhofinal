package petshop.model.enums;

public enum StatusAtendimentoEnum {
	
	AGENDADO("Agendado", "Atendimento agendado"),
	REALIZADO("Realizado", "Atendimento realizado por completo"),
	DESMARCADO("Desmarcado", "Atendimento foi desmarcado");
	
	private String nome;
	private String descricao;
	
	private StatusAtendimentoEnum(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

}
