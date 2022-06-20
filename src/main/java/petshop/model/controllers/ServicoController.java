package petshop.model.controllers;

import petshop.exceptions.AtributosInvalidosException;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.filtros.FiltroServico;
import petshop.model.dtos.request.ServicoRequestDTO;
import petshop.model.dtos.response.RelatorioServicoDTO;
import petshop.model.dtos.response.ServicoResponseDTO;
import petshop.model.dtos.response.ServicoResponseRelatorioDTO;
import petshop.model.services.ServicoService;

import java.sql.SQLException;
import java.util.List;

public class ServicoController {

    private ServicoService servicoService;

    public ServicoController(){
        servicoService = new ServicoService();
    }

    public void save(ServicoRequestDTO servico) throws SQLException, AtributosInvalidosException {
        this.servicoService.save(servico);
    }

    public void update(Long idServico, ServicoRequestDTO servicoDTO) throws AtributosInvalidosException, RegistroNaoEncontradoException {
        this.servicoService.update(idServico, servicoDTO);
    }

    public List<ServicoResponseDTO> listAll(){
        return this.servicoService.listAll();
    }

    public List<ServicoResponseDTO> findWithFilter(FiltroServico filtro){
        return this.servicoService.findWithFilter(filtro);
    }

    public void delete(Long idServico) throws RegistroNaoEncontradoException {
        this.servicoService.delete(idServico);
    }

    public ServicoRequestDTO findByIdToEdit(Long idServico) throws AtributosInvalidosException, RegistroNaoEncontradoException {
        return this.servicoService.findByIdToEdit(idServico);
    }

    public RelatorioServicoDTO gerarRelatorio() {
        return this.servicoService.gerarRelatorio();
    }
}
