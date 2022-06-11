package petshop.model.services;

import petshop.exceptions.AtributosInvalidosException;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.model.business.PetBusiness;
import petshop.filtros.FiltroPet;
import petshop.model.dtos.PetDTO;

import java.sql.SQLException;
import java.util.List;

public class PetService {

    private PetBusiness petBusiness;

    public PetService(){
        petBusiness = new PetBusiness();
    }

    public void save(PetDTO petDTO) throws SQLException, AtributosInvalidosException {
        petBusiness.save(petDTO);
    }

    public void update(Long idPet, PetDTO petDTO) throws RegistroNaoEncontradoException {
        petBusiness.update(idPet, petDTO);
    }

    public List<PetDTO> listAll(){
        return petBusiness.listAll();
    }

    public List<PetDTO> findWithFilter(FiltroPet filtro){
        return petBusiness.findWithFilter(filtro);
    }
}
