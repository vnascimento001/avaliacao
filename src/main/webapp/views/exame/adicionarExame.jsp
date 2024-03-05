<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="styles/background.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>Adicionar exame</title>
    <style>
        .full-height {
            min-height: 80vh;
        }
    </style>
</head>
<body style="background-color: #a19d9d">
	<%@include file="../../menu/menu.jsp"%>
    <div class="d-flex align-items-center justify-content-center full-height">
        <div class="card" style="width: 25rem;">
            <div class="card-header text-center">
                <h2>Adicionar exame</h2>
            </div>
            <div class="card-body">
                <s:form action="salvarExame" method="atualizarExame">
                    <div class="form-group">           
                        <label for="nmFuncionario">Nome do exame:</label>
                        <input type="text" class="form-control" id="nmExame" name="nmExame" value='' required>
                        <label for="icAtivo">Ativo:</label><br>
						<label>
						 <input type="radio" name="icAtivo" value="1" required> Sim
						</label>
						<label>
						 <input type="radio" name="icAtivo" value="0" required> Não
						</label>
					    <br>
					    <label for="dsDetalheExame">Detalhes:</label>
                        <input type="text" class="form-control" id="dsDetalheExame" name="dsDetalheExame" value='' required>
                        <label for="dsDetalheExame1">Mais detalhes:</label>
                        <input type="text" class="form-control" id="dsDetalheExame1" name="dsDetalheExame1" value='' required>
                    </div>
                    <div class="d-flex justify-content-center">
                        <button type="submit" class="btn btn-primary me-2">Adicionar</button>
                        <a href="<s:url action='exame' namespace='/'></s:url>" class="btn btn-secondary">Voltar</a>
                    </div>
                </s:form>
            </div>
        </div>
    </div>
</body>
</html>