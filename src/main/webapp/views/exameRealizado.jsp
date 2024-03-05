<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="styles/background.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Lista de exames realizados</title>
<style>
.full-height {
	min-height: 80vh;
}
</style>
</head>
<body>
    <%@include file="../menu/menu.jsp"%>

    <div class="d-flex justify-content-end">
        <a href="<s:url action='adicionarExameRealizado' namespace='/'></s:url>"
            class="btn btn-primary mr-2">Realizar exame</a>
         <a href="<s:url action='relatorio' namespace='/'></s:url>"
            class="btn btn-primary mr-2">Relatório</a>
    </div>

    <div
        class="d-flex align-items-center justify-content-center full-height">
        <div class="card" style="width: 60rem;">
            <div class="card-header text-center">
                <h2>Lista de exames realizados</h2>
                <form method="GET" action="<s:url action='listarExamesRealizados' namespace='/'></s:url>">
                    <div class="form-row">

						<div class="col-md-4">
						    <select class="form-control" name="cdFuncionario">
						        <option value="">Selecione um funcionário</option>
						        <s:iterator value="funcionarios">
						            <option value="<s:property value="cdFuncionario"/>"><s:property value="nmFuncionario"/></option>
						        </s:iterator>
						    </select>
						</div>
						<div class="col-md-4">
						    <select class="form-control" name="cdExame">
						        <option value="">Selecione um exame</option>
						        <s:iterator value="exames">
						            <option value="<s:property value="cdExame"/>"><s:property value="nmExame"/></option>
						        </s:iterator>
						    </select>
						</div>
                        <div class="col-md-4">
                            <button type="submit" class="btn btn-primary">Filtrar</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th>Código</th>
                                <th>Nome exame</th>
                                <th>Nome funcionário</th>
                                <th>Data</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="examesRealizados">
                                <tr>
                                    <td><s:property value="cdExame" /></td>
                                    <td><s:property value="nmExame" /></td>
                                    <td><s:property value="nmFuncionario" /></td>
									<td><s:property value="dtRealizacao" /></td>
                                    <td><a
                                        href="<s:url action='editarExameRealizado' namespace='/'><s:param name="cdExame" value="cdExame"/><s:param name="cdFuncionario" value="cdFuncionario"/><s:param name="nmExame" value="nmExame"/></s:url>"
                                        class="btn btn-primary mr-2">Editar</a> <a
                                        href="<s:url action='deletarExameRealizado' namespace='/'><s:param name="cdExame" value="cdExame"/><s:param name="cdFuncionario" value="cdFuncionario"/></s:url>"
                                        class="btn btn-primary">Deletar</a></td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
					<div class="pagination">
					    <a class="page-link"
					       href="<s:url action='listarExamesRealizados' namespace='/'><s:param name='pageNumber' value='%{#attr.previousPageNumber}'/><s:param name='nmExame' value='%{#attr.nmExame}'/><s:param name='icAtivo' value='%{#attr.icAtivo}'/></s:url>">Anterior</a>
					    <s:iterator begin="1" end="totalPages" var="page">
					        <li class="page-item"><a class="page-link"
					           href="<s:url action='listarExamesRealizados' namespace='/'><s:param name='pageNumber' value='%{#attr.page}'/><s:param name='nmExame' value='%{#attr.nmExame}'/><s:param name='icAtivo' value='%{#attr.icAtivo}'/></s:url>"><s:property /></a></li>
					    </s:iterator>
					    <a class="page-link"
					       href="<s:url action='listarExamesRealizados' namespace='/'><s:param name='pageNumber' value='%{#attr.nextPageNumber}'/><s:param name='nmExame' value='%{#attr.nmExame}'/><s:param name='icAtivo' value='%{#attr.icAtivo}'/></s:url>">Próximo</a>
					</div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>