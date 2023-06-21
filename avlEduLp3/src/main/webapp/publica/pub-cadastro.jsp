<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Cadastro</title>

    <link href="${pageContext.request.contextPath}/resources/bootstrap-5.1.3-dist/css/bootstrap.min.css" rel="stylesheet" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-3.6.0-dist/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>

</head>

<body>
    <div class="container">
        <div class="row">
            <h1>Cadastrar Moto</h1>
        </div>
      
        <div class="row">
            <div class="col">
                <c:if test="${mensagem != null}">
                    <div class="alert alert-success alert-dismissible fade show">
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                        <span><c:out value="${mensagem}" /></span>
                    </div>
                </c:if>
 
                        <form action="${pageContext.request.contextPath}/pub?acao=inserir" method="post">
                            <div class="row mb-3">
                                <label class="col-sm-1 col-form-label">Marca</label>
						<div class="col-sm-5">
							<input class="form-control" type="text" name="marca">
						</div>
					</div>

					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">Modelo</label>
						<div class="col-sm-3">
							<input class="form-control" type="text" name="modelo">
						</div>
					</div>


					<div class="row mb-3">
						<label class="col-sm-1 col-form-label">Cor</label>
						<div class="col-sm-2">
							<input class="form-control" type="text" name="cor">
						</div>
					</div>


					
					<input class="btn btn-success" type="submit" value="Cadastrar" />
				</form>
				
			</div>
		</div>
	</div>
		
</body>
</html>