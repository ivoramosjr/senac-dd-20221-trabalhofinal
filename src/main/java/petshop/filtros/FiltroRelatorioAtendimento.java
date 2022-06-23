package petshop.filtros;

public class FiltroRelatorioAtendimento {

    private boolean gerarTabela = false;

    public FiltroRelatorioAtendimento(boolean gerarTabela) {
        this.gerarTabela = gerarTabela;
    }

    public boolean isGerarTabela() {
        return gerarTabela;
    }

    public void setGerarTabela(boolean gerarTabela) {
        this.gerarTabela = gerarTabela;
    }
}
