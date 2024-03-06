<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="styles/background.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<title>Erro</title>
<style>
.full-height {
	min-height: 80vh;
}
</style>
</head>
<body>
    <%@include file="../menu/menu.jsp"%>
	<div
		class="d-flex align-items-center justify-content-center full-height">
		<div class="card" style="width: 25rem;">
			<div class="card-header text-center">
				<h2>Ocorreu um erro:</h2>
			</div>
			<div class="card-body">
				<p><s:actionerror/></p>
			</div>
		</div>
	</div>
</body>
</html>