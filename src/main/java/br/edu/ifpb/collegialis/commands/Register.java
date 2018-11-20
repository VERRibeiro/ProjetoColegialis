package br.edu.ifpb.collegialis.commands;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.AlunoDAO;
import br.edu.ifpb.collegialis.dao.ProfessorDAO;
import br.edu.ifpb.collegialis.entity.Aluno;
import br.edu.ifpb.collegialis.entity.Professor;
import br.edu.ifpb.collegialis.entity.Usuario;
import br.edu.ifpb.collegialis.type.TipoPerfil;

public class Register implements Command {
	//TODO Encriptar a senha
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		EntityManager  em=  (EntityManager) request.getAttribute("em");		
		String matricula = request.getParameter("matricula");
		String senha = request.getParameter("senha");
		String role = request.getParameter("papel");
		ProfessorDAO professordao = new ProfessorDAO(em);
		AlunoDAO alunodao = new AlunoDAO(em);
		List<Aluno> alunos = alunodao.findAll();
		List<Professor> professores = professordao.findAll();
		Aluno aluno = new Aluno();
		Professor professor = new Professor();
		for(Aluno a: alunos){
			if(a.getMatricula().equals(matricula))
				aluno = a;
		}
		for(Professor p : professores){
			if(p.getMatricula().equals(matricula))
				professor = p;
		}
		TipoPerfil tipoPerfil = null;
		Usuario usuario = new Usuario();
		usuario.setMatricula(matricula);
		usuario.setSenha(senha);
		switch(role){
		case "admin": 
			usuario.setTipoPerfil(tipoPerfil.ADMIN);
			break;
		case "membro": 
			usuario.setTipoPerfil(tipoPerfil.MEMBRO);
			break;
		case "coordenador": 
			usuario.setTipoPerfil(tipoPerfil.COORDENADOR);
			break;
		case "secretario": 
			usuario.setTipoPerfil(tipoPerfil.SECRETARIO);
			break;
		}
		if(aluno != null){		
			alunodao.beginTransaction();
			aluno.setUsuario(usuario);
			alunodao.update(aluno);
			alunodao.commit();
		}else if(professor != null)
			professordao.beginTransaction();
			professor.setUsuario(usuario);
			professordao.update(professor);
			professordao.commit();
	}
		

}
