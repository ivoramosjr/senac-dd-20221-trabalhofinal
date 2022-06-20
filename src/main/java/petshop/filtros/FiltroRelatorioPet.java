package petshop.filtros;

public class FiltroRelatorioPet {
    private boolean gerarTabela = false;

    public FiltroRelatorioPet(boolean gerarTabela) {
        this.gerarTabela = gerarTabela;
    }

    public boolean isGerarTabela() {
        return gerarTabela;
    }

    public void setGerarTabela(boolean gerarTabela) {
        this.gerarTabela = gerarTabela;
    }
}
