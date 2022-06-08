package petshop.model.services;

import petshop.exceptions.AtributosInvalidosException;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.model.business.ServicoBusiness;
import petshop.model.dtos.FiltroServicoDTO;
import petshop.model.dtos.ServicoDTO;

import java.sql.SQLException;
import java.util.List;

public class ServicoService {

    private ServicoBusiness servicoBusiness;

    public ServicoService(){
        servicoBusiness = new ServicoBusiness();
    }

    public void save(ServicoDTO servicoDTO) throws SQLException, AtributosInvalidosException {
        this.servicoBusiness.save(servicoDTO);
    }

    public void update(Long idServico, ServicoDTO servicoDTO) throws AtributosInvalidosException, RegistroNaoEncontradoException {
        this.servicoBusiness.update(idServico, servicoDTO);
    }

    public List<ServicoDTO> listAll(){
        return this.servicoBusiness.listAll();
    }

    public List<ServicoDTO> findWithFilter(FiltroServicoDTO filtro){
        return this.servicoBusiness.findWithFilter(filtro);
    }

    public void delete(Long idServico) throws RegistroNaoEncontradoException {
        this.servicoBusiness.delete(idServico);
    }
}
