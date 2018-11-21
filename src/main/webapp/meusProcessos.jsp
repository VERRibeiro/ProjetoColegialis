<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Colegialis</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
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
				<c:if test="${role == 'PROFESSOR' || role == 'COORDENADOR'}">
					<li class="nav-item"><a class="nav-link" href="controller?command=ListarReunioes">Reuniões</a>
					</li>
				</c:if>
				<li class="nav-item"><a class="nav-link" href="controller?command=ListarMeusProcessos">Meus Processos</a>
				</li>
				<c:if test="${role == 'COORDENADOR'}">
					<li class="nav-item dropdown">
				        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				          Operações
				        </a>
				        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
				          <a class="dropdown-item" href="controller?command=ListarProcessos">Listar processos cadastrados</a>
				          <a class="dropdown-item" href="controller?command=Login">Distribuir processo a relator</a>
						  <a class="dropdown-item" href="controller?command=ListarColegiado">Cadastrar colegiado</a>
				        </div>
				      </li>
				</c:if>
			</ul>
			<span class="navbar-text"> <a style="padding: 0px;"
				class="nav-link" href="controller?command=Logout">Logout</a>
			</span>
		</div>
	</nav>
	<br>
	<div class="container">
		<h3>Processos</h3>
		<hr>
		<!-- TAG DO JSP (vai gerar a tabela)-->
		<div class="meetings-container">

			<div class="row">
				<div class="col-md-12">
					<table class="table table-bordered"  style="text-align: center;">
					  <thead>
					    <tr>
					      <th scope="col">Numero</th>
					      <th scope="col">Assunto</th>
					      <th scope="col">Relator</th>
					      <th scope="col">Requisitante</th>		
					      <th scope="col">Parecer</th>			     
					    </tr>
					  </thead>
					  <tbody>
					  	<c:forEach var="processo" items="${processos}">
					  		<tr>
						      <td>${processo.numero}</td>
						      <td>${processo.assunto.descricao}</td>
						      <td>${processo.relator.nome}</td>
						      <td>${processo.requisitante.nome}</td>
						      <td>${processo.decisao.texto}</td>									     
						    </tr>
					  	</c:forEach>
	
					  </tbody>
					</table>
				</div>
			</div>

		</div>
		<br>
				<div class="newMeeting-container">
			<h3>Redigir Parecer</h3>
			<div class="card">
				<form action="controller?command=RedigirParecer" method="POST">

					


					<div class="form-row" style="padding: 15px">
											<div class="col-md-12">	
						<div class="input-group">
						<div class="input-group-prepend" class="col-md-5 form-group">
									<span class="input-group-text">Processo</span>
						<select name="processo">				
						  <c:forEach var="processo" items="${processos}">				   					
							  <option value="${processo.id}">${processo.numero}</option>													  	
						  </c:forEach>
						  </select>
						  </div>
	
						  <div class="input-group">
								<div class="input-group-prepend" class="col-md-5 form-group" style="margin-top:20px;">
									<span class="input-group-text">Decisao</span>
						  						<select name="decisao">				
							  <option value="deferido">Deferido</option>
							  <option value="indeferido">Indeferido</option>													  							  
						  </select>
						  </div>
						  
						  </div>
						  <input type="submit">							
						</div>
					</div>	
					</div>


				</form>
			</div>
		</div>


	</div>
<script src="assets/js/jquery/jquery-3.3.1.min.js"></script>
<script src="assets/js/bootstrap/bootstrap.min.js"></script>
<script src="assets/js/reunioes.js"></script>
</body>
</html>