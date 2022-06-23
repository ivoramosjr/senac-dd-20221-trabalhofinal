package petshop.model.controllers;

import petshop.exceptions.AtributosInvalidosException;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.filtros.FiltroPet;
import petshop.filtros.FiltroRelatorioAtendimento;
import petshop.filtros.FiltroRelatorioPet;
import petshop.model.dtos.request.PetRequestDTO;
import petshop.model.dtos.response.*;
import petshop.model.services.PetService;

import java.sql.SQLException;
import java.util.List;

public class PetController {

    private PetService petService;

    public PetController(){
        petService = new PetService();
    }

    public void save(PetRequestDTO petDTO) throws SQLException, AtributosInvalidosException {
        petService.save(petDTO);
    }

    public void update(Long idPet, PetRequestDTO petDTO) throws RegistroNaoEncontradoException {
        petService.update(idPet, petDTO);
    }

    public List<PetResponseListagemDTO> listAll(){
        return petService.listAll();
    }

    public List<PetResponseListagemDTO> findWithFilter(FiltroPet filtro){
        return petService.findWithFilter(filtro);
    }

    public String validarTamanhoMaximo(String texto,Integer tamanhoMaximo){
        return texto.length() <= tamanhoMaximo ? texto : texto.substring(0, tamanhoMaximo);
    }

    public PetRequestDTO findByIdToEdit(Long idPet) throws AtributosInvalidosException, RegistroNaoEncontradoException {
        return petService.findByIdToEdit(idPet);
    }

    public List<String> getRacas() {
        return petService.getRacas();
    }

    public void delete(Long idPet) throws RegistroNaoEncontradoException {
        petService.delete(idPet);
    }

    public List<PetResponseRelatorioDTO> listAllRelatorio() {
        return petService.listAllRelatorio();
    }

    public RelatorioPetDTO gerarRelatorio(FiltroRelatorioPet filtro) {
        return petService.gerarRelatorio(filtro);
    }
}
