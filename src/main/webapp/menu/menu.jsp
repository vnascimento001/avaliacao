<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <div class="container-fluid">
            <s:a action="home" namespace="/"  class="navbar-brand">SOC</s:a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="mynavbar">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item"><s:a action="funcionario" namespace="/" class="nav-link">Funcionários</s:a></li>
					<li class="nav-item"><s:a action="exame" namespace="/" class="nav-link">Exames</s:a></li>
                    <li class="nav-item"><s:a action="exameRealizado" namespace="/" class="nav-link">Exames Realizados</s:a></li>
                </ul>
                <form class="d-flex">
                    <s:if test="#session.usuarioLogado != null">
                        <div class="dropdown">
                            <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                                Bem-vindo, <s:property value="#session.usuarioLogado.nmLogin" />
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                <li><s:a action="logout" namespace="/" class="dropdown-item">Logout</s:a></li>
                            </ul>
                        </div>
                    </s:if>
                </form>
            </div>
        </div>
    </nav>
</body>
</html>