package petshop.model.dtos.response;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RelatorioServicoDTO {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DecimalFormat df = new DecimalFormat("R$ 0.00");

    private final Long numeroServicos;
    private final Long numeroServicosInativos;
    private final String servicoMaisCaro;
    private final String servicoMaisUtilizado;
    private final String servicoMaisBarato;
    private final String ultimoServicoCadastrado;
    private final List<ServicoResponseRelatorioDTO> listaServicos;

    public RelatorioServicoDTO(Long numeroServicos, Long numeroServicosInativos, String servicoMaisCaro, String servicoMaisUtilizado, String servicoMaisBarato, String ultimoServicoCadastrado, List<ServicoResponseRelatorioDTO> listaServicos) {
        this.numeroServicos = numeroServicos;
        this.numeroServicosInativos = numeroServicosInativos;
        this.servicoMaisCaro = servicoMaisCaro;
        this.servicoMaisUtilizado = servicoMaisUtilizado;
        this.servicoMaisBarato = servicoMaisBarato;
        this.ultimoServicoCadastrado = ultimoServicoCadastrado;
        this.listaServicos = listaServicos;
    }

    public Long getNumeroServicos() {
        return numeroServicos;
    }

    public Long getNumeroServicosInativos() {
        return numeroServicosInativos;
    }

    public String getServicoMaisCaro() {
        return servicoMaisCaro;
    }

    public String getServicoMaisUtilizado() {
        return servicoMaisUtilizado;
    }

    public String getServicoMaisBarato() {
        return servicoMaisBarato;
    }

    public String getUltimoServicoCadastrado() {
        return ultimoServicoCadastrado;
    }

    public List<ServicoResponseRelatorioDTO> getListaServicos() {
        return listaServicos;
    }
}
