package br.com.avaliacao.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import br.com.avaliacao.model.Exame;

@WebService
public interface ExameWebService {
    @WebMethod
    List<Exame> listarExames();
}
