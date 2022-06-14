package petshop.model.services;

import petshop.exceptions.AtributosInvalidosException;
import petshop.exceptions.HorarioJaMarcadoException;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.filtros.FiltroAtendimento;
import petshop.model.business.AtendimentoBusiness;
import petshop.model.dtos.request.AtendimentoRequestDTO;
import petshop.model.dtos.response.AtendimentoResponseListagemDTO;

import java.sql.SQLException;
import java.util.List;

public class AtendimentoService {

    private AtendimentoBusiness atendimentoBusiness;

    public AtendimentoService(){
        atendimentoBusiness = new AtendimentoBusiness();
    }

    public void save(AtendimentoRequestDTO atendimentoRequestDTO) throws SQLException, AtributosInvalidosException, HorarioJaMarcadoException, RegistroNaoEncontradoException {
        atendimentoBusiness.save(atendimentoRequestDTO);
    }

    public List<AtendimentoResponseListagemDTO> listAll() {
        return atendimentoBusiness.listAll();
    }

    public List<AtendimentoResponseListagemDTO> findWithFilter(FiltroAtendimento filtroAtendimento) {
        return atendimentoBusiness.findWithFilter(filtroAtendimento);
    }
}
