<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="br.edu.ifpb.collegialis.type.TipoDecisao" %>
<%@ page import="br.edu.ifpb.collegialis.type.TipoStatusProcesso" %>

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
				          <a class="dropdown-item" href="controller?command=Login">Cadastrar/modificar processo</a>
				          <a class="dropdown-item" href="controller?command=ListarProcessos">Listar processos cadastrados</a>
				          <a class="dropdown-item" href="controller?command=Login">Distribuir processo a relator</a>
						  <a class="dropdown-item" href="controller?command=ListarColegiado">Cadastrar colegiado</a>
						  <a class="dropdown-item" href="controller?command=ListarReunioes">Criar/Listar reunião</a>
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
		<h3>Colegiados</h3>
		<hr>
		<!-- TAG DO JSP (vai gerar a tabela)-->
		<div class="meetings-container">

			<div class="row">
				<div class="col-md-12">
					<table class="table table-bordered"  style="text-align: center;">
					  <thead>
					    <tr>
					      <th scope="col">Descrição</th>
					      <th scope="col">Portaria</th>
					      <th scope="col">Curso</th>				     
					    </tr>
					  </thead>
					  <tbody>
					  	<c:forEach var="colegiado" items="${colegiados}">
					  		<tr>
						      <td>Colegiado ${colegiado.descricao}</td>
						      <td>${colegiado.portaria}</td>
						      <td>${colegiado.curso}</td>							     
						    </tr>
					  	</c:forEach>
	
					  </tbody>
					</table>
				</div>
			</div>

		</div>
		<br>



		<!-- MODAL -->
		<c:if test="${role == 'COORDENADOR'}">
		<div class="newMeeting-container">
			<h3>Novo Colegiado</h3>
			<div class="card">
				<form action="controller?command=CriarColegiado" method="POST">

					<div class="form-row justify-content-between" style="padding: 15px">
						<div class="col-md-12 form-group">
							<div class="input-group">
						    
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-prepend" class="col-md-5 form-group">
									<span class="input-group-text">Data Inicio</span>
								</div>							    
								<input type="date" id="data-input" name="dataInicio" class="form-control" required>
								<div class="input-group-prepend" class="col-md-5 form-group">
									<span class="input-group-text">Data Fim</span>
								</div>						    
								<input type="date" id="data-input" name="dataFim" class="form-control" required>
								</br>	
								<div class="input-group-prepend" >
									<span class="input-group-text">Curso</span>
								</div>							    
								<input type="text" id="data-input" name="curso" class="form-control" required>
							</div>
	
							
						</div>
					</div>
					<div class="col-md-12">						
						<div class="input-group">						
								<div class="input-group-prepend" class="col-md-5 form-group">
								<span class="input-group-text">Descrição</span>
								<input type="text" name="descricao">
								</div>
						</div>
					</div>
					<div class="form-row" style="margin: 15px">
					<div class="col-md-12">						
						<div class="input-group">						
								<div class="input-group-prepend" class="col-md-5 form-group">
								<span class="input-group-text">Portaria</span>
									<input type="text" name="portaria">
								</div>
						</div>
					</div>
					</div>

					<div class="form-row" style="padding: 15px">

						<div class="col-md-12">		
						<div class="input-group">
								<div class="input-group-prepend" class="col-md-5 form-group">
									<span class="input-group-text">Professores</span>
						<select multiple name="professor">				
						  <c:forEach var="professor" items="${professores}">						   					
							  <option value="${professor.id}">${professor.nome}</option>													  	
						  </c:forEach>
						  </select>
						  </div>
						  
						  </div>
						  <div class="input-group">
								<div class="input-group-prepend" class="col-md-5 form-group" style="margin-top:20px;">
									<span class="input-group-text">Aluno</span>
						  						<select name="aluno">				
						  <c:forEach var="aluno" items="${alunos}">						   					
							  <option value="${aluno.id}">${aluno.nome}</option>													  	
						  </c:forEach>
						  </select>
						  </div>
						  
						  </div>
						  <input type="submit">							
						</div>
					</div>	


				</form>
			</div>
		</div>
		</c:if>

	</div>
<script src="assets/js/jquery/jquery-3.3.1.min.js"></script>
<script src="assets/js/bootstrap/bootstrap.min.js"></script>
<script src="assets/js/reunioes.js"></script>
</body>
</html>