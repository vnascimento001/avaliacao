<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="styles/background.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>Login</title>
<style>
        .full-height {
            min-height: 80vh;
        }
</style>
</head>
<body>
	<div
		class="d-flex align-items-center justify-content-center full-height">
		<div class="card" style="width: 25rem;">
			<div class="card-header text-center">
				<h2>Login</h2>
			</div>
			<div class="card-body">
				<form action="">
					<div class="form-group">
						<label for="login">Login:</label> <input type="text" id="login"
							name="login" class="form-control" required>
						<label for="senha">Senha:</label> <input type="password"
							id="senha" name="senha" class="form-control" required>
					</div>
					<div class="d-flex justify-content-center">
						<button type="submit" class="btn btn-primary">Entrar</button>
					</div>
					<s:if test="hasActionErrors()">
						<div class="error">
							<s:actionerror />
						</div>
					</s:if>
				</form>
			</div>
		</div>
	</div>
</body>

</html>