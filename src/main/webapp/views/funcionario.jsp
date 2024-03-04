<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="styles/background.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Lista de Funcionários</title>
 <style>
	.full-height {
		min-height: 80vh;
        }
</style>
</head>
<body>
	<%@include file="../menu/menu.jsp"%>
	<h1 class="d-flex justify-content-between align-items-center">
		Lista de Funcionários

		<a href="<s:url action='adicionarFuncionario' namespace='/'></s:url>" class="btn btn-primary mr-2">Adicionar Funcionário</a>
	</h1>
	<div
		class="d-flex align-items-center justify-content-center full-height">
		<div class="card" style="width: 25rem;">
			<div class="card-header text-center">
				<h2>Login</h2>
			</div>
			<div class="card-body">
				<table class="table table-striped table-dark">
					<thead>
						<tr>
							<th>Código</th>
							<th>Nome</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="funcionarios">
							<tr>
								<td><s:property value="cdFuncionario" /></td>
								<td><s:property value="nmFuncionario" /></td>
								<td><a
									href="<s:url action='editarFuncionario' namespace='/'><s:param name="cdFuncionario" value="cdFuncionario"/><s:param name="nmFuncionario" value="nmFuncionario"/></s:url>"
									class="btn btn-primary mr-2">Editar</a> <a
									href="deleteFuncionario.jsp?cdFuncionario=<s:property value='cdFuncionario'/>"
									class="btn btn-primary">Deletar</a></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>