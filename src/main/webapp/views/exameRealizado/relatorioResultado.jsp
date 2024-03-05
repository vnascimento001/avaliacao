<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="styles/background.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Relatório de exames realizados</title>
<style>
.full-height {
	min-height: 80vh;
}
</style>
</head>
<body>
	<%@include file="../../menu/menu.jsp"%>
	<div
		class="d-flex align-items-center justify-content-center full-height">
		<div class="card" style="width: 50rem;">
			<div class="card-header text-center">
				<h2>Lista de funcionários</h2>
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<table class="table">
						<thead class="thead-dark">
							<tr>
								<th>Código Funcionário</th>
								<th>Nome Funcionário</th>
								<th>Código Exame</th>
								<th>Nome Exame</th>
								<th>Data Realização</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="examesRealizados">
								<tr>
									<td><s:property value="cdFuncionario" /></td>
									<td><s:property value="nmFuncionario" /></td>
									<td><s:property value="cdExame" /></td>
									<td><s:property value="nmExame" /></td>
									<td><s:property value="dtRealizacao" /></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<div class="d-flex justify-content-center">
				<a href="<s:url action='relatorio' namespace='/'></s:url>"
            	class="btn btn-primary mr-2">Relatório</a>
            	</div>
			</div>
		</div>
	</div>
</body>
</html>