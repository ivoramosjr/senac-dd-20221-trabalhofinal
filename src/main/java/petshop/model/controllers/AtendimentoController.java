package petshop.model.controllers;

import petshop.exceptions.AtributosInvalidosException;
import petshop.exceptions.HorarioJaMarcadoException;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.filtros.FiltroAtendimento;
import petshop.filtros.FiltroRelatorioAtendimento;
import petshop.model.dtos.request.AtendimentoRequestDTO;
import petshop.model.dtos.response.AtendimentoRegistroDTO;
import petshop.model.dtos.response.AtendimentoResponseListagemDTO;
import petshop.model.dtos.response.RelatorioAtendimentoDTO;
import petshop.model.services.AtendimentoService;

import java.sql.SQLException;
import java.util.List;

public class AtendimentoController {

    private AtendimentoService atendimentoService;

    public AtendimentoController() {
        atendimentoService = new AtendimentoService();
    }

    public void save(AtendimentoRequestDTO atendimentoRequestDTO) throws SQLException, AtributosInvalidosException, HorarioJaMarcadoException, RegistroNaoEncontradoException {
        atendimentoService.save(atendimentoRequestDTO);
    }

    public List<AtendimentoResponseListagemDTO> listAll() {
        return atendimentoService.listAll();
    }

    public List<AtendimentoResponseListagemDTO> findWithFilter(FiltroAtendimento filtroAtendimento) {
        return atendimentoService.findWithFilter(filtroAtendimento);
    }

    public RelatorioAtendimentoDTO gerarRelatorio(FiltroRelatorioAtendimento filtro) {
        return atendimentoService.gerarRelatorio(filtro);
    }

    public List<AtendimentoRegistroDTO> listAllRegistrar() {
        return atendimentoService.listAllRegistrar();
    }

    public void finalizarAtendimento(Long idAtendimento) throws RegistroNaoEncontradoException {
        atendimentoService.finalizarAtendimento(idAtendimento);
    }

    public void deletarAtendimento(Long idAtendimento) throws RegistroNaoEncontradoException {
        atendimentoService.deletarAtendimento(idAtendimento);
    }
}
