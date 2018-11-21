<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Collegialis</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<style type="text/css">
	
</style>
<body>
	<nav class="navbar navbar-dark bg-primary">
  		 <a class="navbar-brand" href="/">Collegialis</a>
	</nav>
	<br>
	
	<div class="container">
		<div class="row justify-content-center">
			<div class="card col-md-6">
				<form style="padding: 15px;" method="POST" action="controller?command=Login">
					<h3 style="text-align: center;">Login</h3>
					<div class="form-group" >
					    <label for="input-email">E-mail</label>
					    <input type="text" class="form-control" name="matricula" placeholder="1522313" required>
					</div>
					<div class="form-group" >
					    <label for="input-senha">Senha</label>
					    <input type="password" class="form-control" name="senha"  placeholder="******" required>
					</div>
					<div class="form-group">
						<label for="input-tipo">Sou</label>
						<select class="custom-select">
							<option>Coordenador</option>
							<option>Relator</option>
							<option>Membro</option>
						</select>
					</div>
					<div class="form-group" style="text-align: center;">
						<button class="btn btn-primary" type="submit">Entrar</button>
					</div>
				</form>
			</div>
		</div>

	</div>

</body>
</html>


