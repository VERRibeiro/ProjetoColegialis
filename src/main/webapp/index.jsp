<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Colegialis</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<a class="navbar-brand" href="#">Collegialis</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarText" aria-controls="navbarText"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarText">
			<ul class="navbar-nav mr-auto">
				
					<li class="nav-item"><a class="nav-link"
						href="controller?command=ListarReunioes">Reuniões</a></li>				
				<c:if test="${role != 'ALUNO'}">
				<li class="nav-item"><a class="nav-link" href="controller?command=ListarMeusProcessos">Meus
						Processos</a></li>
				<c:if test="${role == 'COORDENADOR'}">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false"> Operações </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdown">
								 <a class="dropdown-item"
								href="controller?command=ListarProcessos">Listar processos
								cadastrados</a> <a class="dropdown-item"
								href="controller?command=ListarProcessoRelator">Distribuir processo a
								relator</a> <a class="dropdown-item"
								href="controller?command=ListarColegiado">Cadastrar
								colegiado</a>
						</div></li>
						</c:if>
				</c:if>
			</ul>
			<span class="navbar-text"> <a style="padding: 0px;"
				class="nav-link" href="controller?command=Logout">Logout</a>
			</span>
		</div>
	</nav>


	<br>
<script src="assets/js/jquery/jquery-3.3.1.min.js"></script>
<script src="assets/js/bootstrap/bootstrap.min.js"></script>
<script src="assets/js/index.js"></script>
</body>
</html>