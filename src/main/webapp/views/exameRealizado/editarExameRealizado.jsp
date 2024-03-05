<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="styles/background.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>Editar exame realizado</title>
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
                <h2>Editar exame realizado</h2>
            </div>
            <div class="card-body">
                <s:form action="atualizarExameRealizado" method="atualizarExameRealizado">
                    <div class="form-group">           
						<div class="col-md-4">
							<label for="nmFuncionario">Novo funcionário:</label>
						    <select class="form-control" name="cdFuncionario" required>
								<s:iterator value="funcionarios">
								    <option value="<s:property value='cdFuncionario'/>"><s:property value="nmFuncionario"/></option>
								</s:iterator>
						    </select>
						</div>
						<div class="col-md-4">
							<label for="nmExame">Novo exame:</label>
						    <select class="form-control" name="cdExame" required>
									<s:iterator value="exames">
									    <option value="<s:property value='cdExame'/>"><s:property value="nmExame"/></option>
									</s:iterator>
						    </select>
						</div>
						 <label for="dtRealizacao">Data de Realização:</label>
						 <br>
						 <input type="date" id="dtRealizacao" name="dtRealizacao" required pattern="\d{4}-\d{2}-\d{2}">
						 <span class="validity"></span>
                    </div>
                    <div class="d-flex justify-content-center">
                        <button type="submit" class="btn btn-primary me-2">Atualizar exame</button>
                        <a href="<s:url action='exameRealizado' namespace='/'></s:url>" class="btn btn-secondary">Voltar</a>
                    </div>
                </s:form>
            </div>
        </div>
    </div>
</body>
</html>