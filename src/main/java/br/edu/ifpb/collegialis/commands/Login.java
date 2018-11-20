package br.edu.ifpb.collegialis.commands;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.collegialis.dao.AlunoDAO;
import br.edu.ifpb.collegialis.dao.ProfessorDAO;
import br.edu.ifpb.collegialis.entity.Aluno;
import br.edu.ifpb.collegialis.entity.Professor;

public class Login implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		EntityManager  em=  (EntityManager) request.getAttribute("em");		
		String matricula = request.getParameter("matricula");
		String senha = request.getParameter("senha");
		ProfessorDAO professordao = new ProfessorDAO(em);
		AlunoDAO alunodao = new AlunoDAO(em);
		List<Aluno> alunos = alunodao.findAll();
		List<Professor> professores = professordao.findAll();				
		for(Aluno a: alunos){
			if(a.getMatricula().equals(matricula) && a.getUsuario().getSenha().equals(senha)){
				HttpSession session = request.getSession();
				session.setAttribute("usuario", a);
			}
		}
		for(Professor p : professores){
			if(p.getMatricula().equals(matricula)&& p.getUsuario().getSenha().equals(senha)){
				HttpSession session = request.getSession();
				session.setAttribute("usuario", p);
			}
		}
		
		RequestDispatcher d = request.getRequestDispatcher("/index.jsp");
		
	}
}
