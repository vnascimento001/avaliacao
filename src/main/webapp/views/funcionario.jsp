<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="styles/background.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Lista de Funcionários</title>
<style>
.full-height {
	min-height: 80vh;
}
</style>
</head>
<body>
	<%@include file="../menu/menu.jsp"%>
	<div class="d-flex justify-content-end">
		<a href="<s:url action='adicionarFuncionario' namespace='/'></s:url>"
			class="btn btn-primary mr-2">Adicionar Funcionário</a>
	</div>

	<div
		class="d-flex align-items-center justify-content-center full-height">
		<div class="card" style="width: 30rem;">
			<div class="card-header text-center">
				<h2>Lista de funcionários</h2>
			</div>
			<div class="card-body">
				<table class="table">
					<thead class="thead-dark">
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
									class="btn btn-primary mr-2">Editar</a> 
									<s:url id="deleteUrl" action="deletarFuncionario" namespace="/">
									<s:param name="cdFuncionario" value="cdFuncionario" />
									</s:url>
									<s:a href="%{deleteUrl}" onclick="return confirm('Você realmente quer deletar este funcionario?');" class="btn btn-primary">
									<s:text name="Deletar" />
									</s:a>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<div class="pagination">
				    <a class="page-link" href="<s:url action='funcionario' namespace='/'><s:param name='pageNumber' value='%{#attr.previousPageNumber}'/></s:url>">Anterior</a>
				    <s:iterator begin="1" end="totalPages" var="page">
				        <li class="page-item"><a class="page-link" href="<s:url action='funcionario' namespace='/'><s:param name='pageNumber' value='%{#attr.page}'/></s:url>"><s:property/></a></li>
				    </s:iterator>
				    <a class="page-link" href="<s:url action='funcionario' namespace='/'><s:param name='pageNumber' value='%{#attr.nextPageNumber}'/></s:url>">Próximo</a>
				</div>
			</div>
		</div>
	</div>
</body>