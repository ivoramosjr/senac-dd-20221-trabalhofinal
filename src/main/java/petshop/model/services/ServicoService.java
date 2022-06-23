package petshop.model.services;

import petshop.exceptions.AtributosInvalidosException;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.model.business.ServicoBusiness;
import petshop.filtros.FiltroServico;
import petshop.model.dtos.request.ServicoRequestDTO;
import petshop.model.dtos.response.RelatorioServicoDTO;
import petshop.model.dtos.response.ServicoResponseDTO;

import java.sql.SQLException;
import java.util.List;

public class ServicoService {

    private ServicoBusiness servicoBusiness;

    public ServicoService(){
        servicoBusiness = new ServicoBusiness();
    }

    public void save(ServicoRequestDTO servico) throws SQLException, AtributosInvalidosException {
        this.servicoBusiness.save(servico);
    }

    public void update(Long idServico, ServicoRequestDTO servicoDTO) throws AtributosInvalidosException, RegistroNaoEncontradoException {
        this.servicoBusiness.update(idServico, servicoDTO);
    }

    public List<ServicoResponseDTO> listAll(){
        return this.servicoBusiness.listAll();
    }

    public List<ServicoResponseDTO> findWithFilter(FiltroServico filtro){
        return this.servicoBusiness.findWithFilter(filtro);
    }

    public void delete(Long idServico) throws RegistroNaoEncontradoException {
        this.servicoBusiness.delete(idServico);
    }

    public ServicoRequestDTO findByIdToEdit(Long idServico) throws AtributosInvalidosException, RegistroNaoEncontradoException {
        return this.servicoBusiness.findByIdToEdit(idServico);
    }

    public RelatorioServicoDTO gerarRelatorio() {
        return this.servicoBusiness.gerarRelatorio();
    }
}
