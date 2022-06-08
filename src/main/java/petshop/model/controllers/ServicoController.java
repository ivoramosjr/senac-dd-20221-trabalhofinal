package petshop.model.controllers;

import petshop.exceptions.AtributosInvalidosException;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.model.dtos.FiltroServicoDTO;
import petshop.model.dtos.ServicoDTO;
import petshop.model.services.ServicoService;

import java.sql.SQLException;
import java.util.List;

public class ServicoController {

    private ServicoService servicoService;

    public ServicoController(){
        servicoService = new ServicoService();
    }

    public void save(ServicoDTO servicoDTO) throws SQLException, AtributosInvalidosException {
        this.servicoService.save(servicoDTO);
    }

    public void update(Long idServico, ServicoDTO servicoDTO) throws AtributosInvalidosException, RegistroNaoEncontradoException {
        this.servicoService.update(idServico, servicoDTO);
    }

    public List<ServicoDTO> listAll(){
        return this.servicoService.listAll();
    }

    public List<ServicoDTO> findWithFilter(FiltroServicoDTO filtro){
        return this.servicoService.findWithFilter(filtro);
    }

    public void delete(Long idServico) throws RegistroNaoEncontradoException {
        this.servicoService.delete(idServico);
    }
}
