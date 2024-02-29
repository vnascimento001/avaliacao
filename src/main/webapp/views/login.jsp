<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="styles/styles.css">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body
	style="background-image: linear-gradient(to bottom, #212529, #ffffff);">
	<div class="login-container">
		<h2>Login</h2>
		<form action="login" method="post">
			<div class="form-group">
				<label for="login">Login:</label> <input type="text" id="login"
					name="login" class="form-control" required>
			</div>
			<div class="form-group">
				<label for="senha">Senha:</label> <input type="password" id="senha"
					name="senha" class="form-control" required>
			</div>
			<div class="text-center">
				<button type="submit" class="btn btn-primary">Entrar</button>
			</div>
			<s:if test="hasActionErrors()">
				<div class="error">
					<s:actionerror />
				</div>
			</s:if>
		</form>
	</div>
</body>

</html>