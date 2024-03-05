<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="styles/background.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Adicionar exame realizado</title>
<style>
.full-height {
	min-height: 80vh;
}
</style>
</head>
<body style="background-color: #a19d9d">
	<%@include file="../../menu/menu.jsp"%>
	<div
		class="d-flex align-items-center justify-content-center full-height">
		<div class="card" style="width: 20rem;">
			<div class="card-header text-center">
				<h2>Intervalo de datas</h2>
			</div>
			<div class="card-body">
				<s:form action="gerarRelatorio" method="post">
					<div class="form-group">
						
							<label for="dtInicial">Data inicial:</label> <br> <input
								type="date" id="dtInicial" name="dtInicial" required
								pattern="\d{4}-\d{2}-\d{2}"> <span class="validity"></span>
							<br> <label for="dtFinal">Data Final:</label> <br> <input
								type="date" id="dtFinal" name="dtFinal" required
								pattern="\d{4}-\d{2}-\d{2}"> <span class="validity"></span>
						
					</div>
					<div class="d-flex justify-content-center">
						<button type="submit" class="btn btn-primary">Gerar relatório</button>
					</div>
				</s:form>
			</div>
		</div>
	</div>
</body>
</html>