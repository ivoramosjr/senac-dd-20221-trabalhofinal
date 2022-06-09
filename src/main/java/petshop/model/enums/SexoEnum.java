package petshop.model.enums;

public enum SexoEnum {

    MACHO("Macho"),
    FEMEA("Fêmea");


    private String nome;

    private SexoEnum(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
