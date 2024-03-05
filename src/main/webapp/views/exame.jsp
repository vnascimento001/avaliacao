<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="styles/background.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Lista de Exames</title>
<style>
.full-height {
	min-height: 80vh;
}
</style>
</head>
<body>
    <%@include file="../menu/menu.jsp"%>

    <div class="d-flex justify-content-end">
        <a href="<s:url action='adicionarExame' namespace='/'></s:url>"
            class="btn btn-primary mr-2">Adicionar Exame</a>
    </div>

    <div
        class="d-flex align-items-center justify-content-center full-height">
        <div class="card" style="width: 50rem;">
            <div class="card-header text-center">
                <h2>Lista de exames</h2>
                <form method="GET" action="<s:url action='listarExames' namespace='/'></s:url>">
                    <div class="form-row">
                        <div class="col-md-4">
                            <input type="text" class="form-control" name="nmExame" placeholder="Nome do Exame" value="${nmExame}">
                        </div>
                        <div class="col-md-4">
                            <select class="form-control" name="icAtivo">
                            	<option value="" selected>Selecione o status</option>
                                <option value="1" ${icAtivo == 1 ? 'selected' : ''}>Ativo</option>
                                <option value="0" ${icAtivo == 0 ? 'selected' : ''}>Inativo</option>
                                <option value="" ${icAtivo == null ? 'selected' : ''}>Ambos</option>
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
                                <th>Nome</th>
                                <th>Detalhe</th>
                                <th>Mais detalhes</th>
                                <th>Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="exames">
                                <tr>
                                    <td><s:property value="cdExame" /></td>
                                    <td><s:property value="nmExame" /></td>
									<td class="text-truncate" style="max-width: 150px;"><s:property value="dsDetalheExame" /></td>
									<td class="text-truncate" style="max-width: 150px;"><s:property value="dsDetalheExame1" /></td>
                                    <td><a
                                        href="<s:url action='editarExame' namespace='/'><s:param name="cdExame" value="cdExame"/><s:param name="nmExame" value="nmExame"/></s:url>"
                                        class="btn btn-primary mr-2">Editar</a> <a
                                        href="<s:url action='deletarExame' namespace='/'><s:param name="cdExame" value="cdExame"/></s:url>"
                                        class="btn btn-primary">Deletar</a></td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
					<div class="pagination">
					    <a class="page-link"
					       href="<s:url action='listarExames' namespace='/'><s:param name='pageNumber' value='%{#attr.previousPageNumber}'/><s:param name='nmExame' value='%{#attr.nmExame}'/><s:param name='icAtivo' value='%{#attr.icAtivo}'/></s:url>">Anterior</a>
					    <s:iterator begin="1" end="totalPages" var="page">
					        <li class="page-item"><a class="page-link"
					           href="<s:url action='listarExames' namespace='/'><s:param name='pageNumber' value='%{#attr.page}'/><s:param name='nmExame' value='%{#attr.nmExame}'/><s:param name='icAtivo' value='%{#attr.icAtivo}'/></s:url>"><s:property /></a></li>
					    </s:iterator>
					    <a class="page-link"
					       href="<s:url action='listarExames' namespace='/'><s:param name='pageNumber' value='%{#attr.nextPageNumber}'/><s:param name='nmExame' value='%{#attr.nmExame}'/><s:param name='icAtivo' value='%{#attr.icAtivo}'/></s:url>">Próximo</a>
					</div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>