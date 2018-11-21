<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="br.edu.ifpb.collegialis.type.TipoDecisao"%>
<%@ page import="br.edu.ifpb.collegialis.type.TipoStatusProcesso"%>

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
	<div class="container">
		<h3>Planejamento de Reuniões</h3>
		<hr>
		<!-- TAG DO JSP (vai gerar a tabela)-->
		<div class="meetings-container">
			<div class="row justify-content-between">
				<div class="col-md-4">
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<label class="input-group-text" for="status-select">Status</label>
						</div>
						<select class="custom-select" name="status-select"
							id="status-select">							
							<option value="Planejada">Planejada</option>
							<option value="Encerrada">Encerrada</option>
						</select>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<table class="table table-bordered" style="text-align: center;">
						<thead>
							<tr>
								<th scope="col">Reunião</th>
								<th scope="col">Data</th>
								<th scope="col">Processos</th>
								<th scope="col">Operações</th>
								<th scope="col">Status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="reuniao" items="${reunioes}">
								<fmt:formatDate var="data1" pattern="MM/yyyy"
									value="${reuniao.data}" />
								<fmt:formatDate var="data2" pattern="dd/MM/yyyy"
									value="${reuniao.data}" />
								<tr>
									<td><a href="controller?command=RealizarReuniao&id=${reuniao.id}">Reunião ${data1}</td>
									<td>${data2}</td>
									<td>${fn:length(reuniao.processos)}</td>
									<td>
										<button class="btn btn-info">PDF</button>
									</td>
									<td>
										${reuniao.status.texto}
									</td>
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
				<h3>Nova reunião</h3>
				<div class="card">
					<form id="criarReuniao" action="controller?command=CriarReuniao"
						method="POST">

						<div class="form-row justify-content-between"
							style="padding: 15px">
							<div class="col-md-4 form-group">
								<br>
								<div class="input-group">
									<div class="input-group-prepend">
										<span class="input-group-text">Data</span>
									</div>
									<input type="date" id="data-input" name="data"
										class="form-control" required>
								</div>
							</div>
							<div class="col-md-4 form-group">
								<p>Observações:</p>
								<textarea id="observacoes-input" class="form-control"
									name="observacoes" rows="3"></textarea>
							</div>
						</div>
						<div class="form-row" style="padding: 15px">
							<div class="form-group col-md-6">
								<div class="input-group">
									<div class="input-group-prepend">
										<label class="input-group-text" for="processo-select">Colegiado</label>
									</div>
									<select class="custom-select" name="colegiado"
										id="processos-select">
										<c:forEach var="colegiado" items="${colegiados}">
											<option value="${colegiado.id}">${colegiado.descricao}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>


						<div class="form-row" style="padding: 15px">
							<div class="form-group col-md-6">
								<div class="input-group">
									<div class="input-group-prepend">
										<label class="input-group-text" for="processo-select">Processos</label>
									</div>
									<select class="custom-select1" name="processo-select"
										id="processos-select">
										<option selected disabled>Escolher...</option>
										<c:forEach var="processo" items="${processos}">
											<c:if
												test="${ processo.decisao == null && processo.status != TipoStatusProcesso.JULGADO}">
												<option value="${processo.id}">${processo.numero},
													${processo.requisitante.nome}</option>
											</c:if>
										</c:forEach>
									</select>
									<div class="input-group-append">
										<button class="btn btn-success" id="processo-button"
											type="button">+</button>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<table class="table table-striped table-bordered">
									<thead>
										<tr>
											<th scope="col">Nº</th>
											<th scope="col">Requisitante</th>
											<th scope="col">Assunto</th>
											<th scope="col">Relator</th>
											<th scope="col">Excluir</th>
										</tr>
									</thead>
									<tbody id="processos-table">
									</tbody>
								</table>
								<input type="submit" id="reuniao-submit" class="btn btn-primary" value="Cadastrar Reunião">
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