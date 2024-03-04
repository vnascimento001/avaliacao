<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="styles/background.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>Adicionar funcionário</title>
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
                <h2>Adicionar funcionário</h2>
            </div>
            <div class="card-body">
                <s:form action="salvarFuncionario" method="atualizarFuncionario">
                    <div class="form-group">           
                        <label for="nmFuncionario">Nome:</label>
                        <input type="text" class="form-control" id="nmFuncionario" name="nmFuncionario" value=''>
                    </div>
                    <div class="d-flex justify-content-center">
                        <button type="submit" class="btn btn-primary">Atualizar</button>
                    </div>
                </s:form>
            </div>
        </div>
    </div>
    <s:if test="ctr>0">
        <span style="color: red;"><s:property value="msg" /></span>
    </s:if>
    <s:else>
        <span style="color: red;"><s:property value="msg" /></span>
    </s:else>
</body>
</html>