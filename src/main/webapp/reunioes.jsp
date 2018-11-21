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
	<nav class="navbar navbar-dark bg-primary">
  		 <a class="navbar-brand" href="/">Colegialis</a>
	</nav>
	<br>
	<div class="container">
		<h3>Planejamento de Reuniões</h3>
		<hr>
		<!-- TAG DO JSP (vai gerar a tabela)-->
		<div class="meetings-container">
			<div class="row justify-content-between">
				<div class="col-md-2">
					<button class="btn btn-primary" type="button">Novo</button>
				</div>
				<div class="col-md-4">
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<label class="input-group-text" for="status-select">Status</label>
						</div>
					  	<select class="custom-select" name="status-select" id="status-select">
						    <option selected disabled>Escolher...</option>
						    <option value="Planejada">Planejada</option>
						    <option value="Encerrada">Encerrada</option>
						</select>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<table class="table table-bordered"  style="text-align: center;">
					  <thead>
					    <tr>
					      <th scope="col">Reunião</th>
					      <th scope="col">Data</th>
					      <th scope="col">Processos</th>
					      <th scope="col">Operações</th>
					      <th scope="col">Excluir</th>
					    </tr>
					  </thead>
					  <tbody>
					  	<c:forEach var="reuniao" items="${reunioes}">
					  		<fmt:formatDate var="data1" pattern="MM/yyyy" value="${reuniao.data}" />
					  		<fmt:formatDate var="data2" pattern="dd/MM/yyyy" value="${reuniao.data}" />
					  		<tr>
						      <td>Reunião ${data1}</td>
						      <td>${data2}</td>
						      <td>${fn:length(reuniao.processos)}</td>
						      <td>
						      	<button class="btn btn-info">PDF</button>
						      </td>
						      <td>
						      	<button class="btn btn-danger">X</button>
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
		<div class="newMeeting-container">
			<h3>Nova reunião</h3>
			<div class="card">
				<form>

					<div class="form-row justify-content-between" style="padding: 15px">
						<div class="col-md-4 form-group">
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text">Reunião</span>
								</div>
							    <input type="text" id="mes-input" aria-label="mes" class="form-control" placeholder="Mês" required>
							    <input type="text" id="ano-input" aria-label="ano" class="form-control" placeholder="Ano" required>
							</div>
							<br>
							<div class="input-group">
								<div class="input-group-prepend">
									<span class="input-group-text">Data</span>
								</div>							    
								<input type="date" id="data-input" name="data" class="form-control" required>
							</div>
						</div>
						<div class="col-md-4 form-group">
							<p>Observações: </p>
							<textarea id="observacoes-input" class="form-control" name="observacoes" rows="3"></textarea>
						</div>
					</div>


					<div class="form-row" style="padding: 15px">
						<div class="form-group col-md-6">
							<div class="input-group">
								<div class="input-group-prepend">
									<label class="input-group-text" for="processo-select">Processos</label>
								</div>
							  	<select class="custom-select" name="processo-select" id="processos-select">
								    <option selected disabled>Escolher...</option>
								    <c:forEach var="processo" items="${processos}">
								    	<c:if test="${ processo.decisao == null && processo.status != TipoStatusProcesso.JULGADO}">
								    		<option value="${processo.id}">${processo.numero}, ${processo.requisitante.nome}</option>
								    	</c:if>
								    </c:forEach>
								</select>
								<div class="input-group-append">
									<button class="btn btn-success" id="processo-button" type="button">+</button>
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
							<button id="reuniao-submit" class="btn btn-primary" type="button">Salvar</button>
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