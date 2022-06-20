package petshop.model.dtos.response;

import petshop.model.enums.TipoAnimal;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RelatorioPetDTO {
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DecimalFormat df = new DecimalFormat("R$ 0.00");

    private final Long numeroPetsInativos;

    private final Long totalPetsCadastrados;
    private final TipoAnimal tipoMaisCadastrado;
    private final String petComMaiorPontoDeFidelidade;
    private final List<PetResponseRelatorioDTO> listaPets;

    public RelatorioPetDTO(Long numeroPetsInativos, TipoAnimal tipoMaisCadastrado,
                           Long totalPetsCadastrados, String petComMaiorPontoDeFidelidade,
                           List<PetResponseRelatorioDTO> listaPets){
        this.numeroPetsInativos = numeroPetsInativos;
        this.tipoMaisCadastrado = tipoMaisCadastrado;
        this.petComMaiorPontoDeFidelidade = petComMaiorPontoDeFidelidade;
        this.totalPetsCadastrados = totalPetsCadastrados;
        this.listaPets = listaPets;
    }

    public Long getNumeroPetsInativos() {
        return numeroPetsInativos;
    }

    public TipoAnimal getTipoMaisCadastrado() {
        return tipoMaisCadastrado;
    }

    public Long getTotalPetsCadastrados(){return totalPetsCadastrados;}

    public String getPetComMaiorPontoDeFidelidade() {
        return petComMaiorPontoDeFidelidade;
    }

    public List<PetResponseRelatorioDTO> getListaPets() {
        return listaPets;
    }

}
