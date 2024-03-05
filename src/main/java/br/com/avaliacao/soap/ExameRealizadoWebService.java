package br.com.avaliacao.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import br.com.avaliacao.model.ExameRealizado;

@WebService
public interface ExameRealizadoWebService {
    @WebMethod
    List<ExameRealizado> listarExamesRealizados();
}
