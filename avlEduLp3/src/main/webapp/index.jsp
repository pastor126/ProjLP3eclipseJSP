<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Avaliação</title>

<link
	href="${pageContext.request.contextPath}/resources/bootstrap-5.1.3-dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/jquery-3.6.0-dist/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>

</head>
<body>
	<jsp:include page="/publica/pub-nav.jsp"></jsp:include>
	<div class="container">
		<div>
			<a class="btn btn-primary" href="${pageContext.request.contextPath}/pub?acao=listar">Listar Motos</a>
			<a class="btn btn-primary" href="${pageContext.request.contextPath}/pub?acao=novo">Cadastrar Moto</a>
		</div>
	</div>

</body>
</html>