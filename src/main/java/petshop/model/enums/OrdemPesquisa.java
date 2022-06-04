package petshop.model.enums;

public enum OrdemPesquisa {

    DESC("Decrescente", "DESC"),
    ASC("Crescente", "ASC");

    private String nome;
    private String descricao;

    private OrdemPesquisa(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome(){
        return this.nome;
    }

    public String getDescricao(){
        return this.descricao;
    }
}
