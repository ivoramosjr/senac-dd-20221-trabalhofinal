package petshop.model.services;

import petshop.exceptions.AtributosInvalidosException;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.filtros.FiltroRelatorioAtendimento;
import petshop.filtros.FiltroRelatorioPet;
import petshop.model.business.PetBusiness;
import petshop.filtros.FiltroPet;
import petshop.model.dtos.request.PetRequestDTO;
import petshop.model.dtos.response.PetResponseListagemDTO;
import petshop.model.dtos.response.PetResponseRelatorioDTO;
import petshop.model.dtos.response.RelatorioAtendimentoDTO;
import petshop.model.dtos.response.RelatorioPetDTO;

import java.sql.SQLException;
import java.util.List;

public class PetService {

    private PetBusiness petBusiness;

    public PetService(){
        petBusiness = new PetBusiness();
    }

    public void save(PetRequestDTO petDTO) throws SQLException, AtributosInvalidosException {
        petBusiness.save(petDTO);
    }

    public void update(Long idPet, PetRequestDTO petDTO) throws RegistroNaoEncontradoException {
        petBusiness.update(idPet, petDTO);
    }

    public List<PetResponseListagemDTO> listAll(){
        return petBusiness.listAll();
    }

    public List<PetResponseListagemDTO> findWithFilter(FiltroPet filtro){
        return petBusiness.findWithFilter(filtro);
    }

    public PetRequestDTO findByIdToEdit(Long idPet) throws AtributosInvalidosException, RegistroNaoEncontradoException {
        return petBusiness.findByIdToEdit(idPet);
    }

    public List<String> getRacas() {
        return petBusiness.getRacas();
    }

    public void delete(Long idPet) throws RegistroNaoEncontradoException {
        petBusiness.delete(idPet);
    }

    public List<PetResponseRelatorioDTO> listAllRelatorio() {
        return petBusiness.listAllRelatorio();
    }

    public RelatorioPetDTO gerarRelatorio(FiltroRelatorioPet filtro) {
        return petBusiness.gerarRelatorio(filtro);
    }
}
