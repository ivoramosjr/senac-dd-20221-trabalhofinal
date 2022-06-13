package petshop.model.controllers;

import petshop.exceptions.AtributosInvalidosException;
import petshop.exceptions.HorarioJaMarcadoException;
import petshop.exceptions.RegistroNaoEncontradoException;
import petshop.model.dtos.request.AtendimentoRequestDTO;
import petshop.model.services.AtendimentoService;

import java.sql.SQLException;

public class AtendimentoController {

    private AtendimentoService atendimentoService;

    public AtendimentoController() {
        atendimentoService = new AtendimentoService();
    }

    public void save(AtendimentoRequestDTO atendimentoRequestDTO) throws SQLException, AtributosInvalidosException, HorarioJaMarcadoException, RegistroNaoEncontradoException {
        atendimentoService.save(atendimentoRequestDTO);
    }

}
