package petshop.model.services;

import petshop.exceptions.AtributosInvalidosException;
import petshop.exceptions.HorarioJaMarcadoException;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.model.business.AtendimentoBusiness;
import petshop.model.dtos.request.AtendimentoRequestDTO;

import java.sql.SQLException;

public class AtendimentoService {

    private AtendimentoBusiness atendimentoBusiness;

    public AtendimentoService(){
        atendimentoBusiness = new AtendimentoBusiness();
    }

    public void save(AtendimentoRequestDTO atendimentoRequestDTO) throws SQLException, AtributosInvalidosException, HorarioJaMarcadoException, RegistroNaoEncontradoException {
        atendimentoBusiness.save(atendimentoRequestDTO);
    }

}
