<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="styles/background.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>Atualizar funcionário</title>
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
                <h2>Editando funcionário ${nmFuncionario}</h2>
            </div>
            <div class="card-body">
                <s:form action="atualizarFuncionario" method="atualizarFuncionario">
                    <div class="form-group">
                        <input type="hidden" name="cdFuncionario" value='<s:property value="%{cdFuncionario}"/>'>    
                        <label for="nmFuncionario">Novo nome:</label>
                        <input type="text" class="form-control" id="nmFuncionario" name="nmFuncionario" value='<s:property value="nmFuncionario"/>'>
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