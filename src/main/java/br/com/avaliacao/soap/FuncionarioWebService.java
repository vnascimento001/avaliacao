package br.com.avaliacao.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import br.com.avaliacao.model.Funcionario;
@WebService
public interface FuncionarioWebService {
    @WebMethod
    List<Funcionario> listarFuncionarios();
}
