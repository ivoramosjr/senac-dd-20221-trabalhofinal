package petshop.model.controllers;

import petshop.exceptions.AtributosInvalidosException;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.model.dtos.FiltroPetDTO;
import petshop.model.dtos.PetDTO;
import petshop.model.services.PetService;

import java.sql.SQLException;
import java.util.List;

public class PetController {

    private PetService petService;

    public PetController(){
        petService = new PetService();
    }

    public void save(PetDTO petDTO) throws SQLException, AtributosInvalidosException {
        petService.save(petDTO);
    }

    public void update(Long idPet, PetDTO petDTO) throws RegistroNaoEncontradoException {
        petService.update(idPet, petDTO);
    }

    public List<PetDTO> listAll(){
        return petService.listAll();
    }

    public List<PetDTO> findWithFilter(FiltroPetDTO filtro){
        return petService.findWithFilter(filtro);
    }

    public String validarTamanhoMaximo(String texto,Integer tamanhoMaximo){
        return texto.length() <= tamanhoMaximo ? texto : texto.substring(0, tamanhoMaximo);
    }
}
