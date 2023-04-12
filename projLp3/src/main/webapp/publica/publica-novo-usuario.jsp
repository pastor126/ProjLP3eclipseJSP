<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Novo</title>

<link
	href="${pageContext.request.contextPath}/resources/bootstrap-5.1.3-dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script type="text/javascript">
	src = "${pageContext.request.contextPath}/resources/jquery-3.6.0-dist/jquery-3.6.0.min.js">
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- Compondo página com a barra de navegação -->
	<jsp:include page="/publica/publica-nav.jsp" />
	
		<div class="container">
		<div class="row">
			<div class="row">
				<h1>Cadastro de Usuário</h1>
					<div class="container">
		<div class="row">
			<div class="col">
				<h2>Cadastro usuário</h2>
				
				<c:if test="${mensagem != null}">
				 	<div class="alert alert-success alert-dismissible fade show">
					<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
					<span><c:out value="${mensagem}" /></span>
				</div>
				</c:if>
				
				<form
					action="${pageContext.request.contextPath}/publica?acao=inserir"
					method="post">

					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">Nome</label>
						<div class="col-sm-5">
							<input class="form-control" type="text" name="nome">
						</div>
					</div>

					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">CPF</label>
						<div class="col-sm-3">
							<input class="form-control" type="text" name="cpf">
						</div>
					</div>


					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">Nascimento</label>
						<div class="col-sm-2">
							<input class="form-control" type="text" name="nascimento">
						</div>
					</div>


					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">Email</label>
						<div class="col-sm-2">
							<input class="form-control" type="text" name="email">
						</div>
					</div>

					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">Login</label>
						<div class="col-sm-2">
							<input class="form-control" type="text" name="login">
						</div>
					</div>

					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">Senha</label>
						<div class="col-sm-2">
							<input class="form-control" type="password" name="password">
						</div>
					</div>

					<input class="btn btn-primary" type="submit" value="Gravar" />
				</form>
			</div>
		</div>
	</div>
			</div>
		</div>
	</div>

</body>
</html>