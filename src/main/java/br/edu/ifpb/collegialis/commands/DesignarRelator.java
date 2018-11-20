package br.edu.ifpb.collegialis.commands;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.ColegiadoDAO;
import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.dao.ProfessorDAO;
import br.edu.ifpb.collegialis.entity.Colegiado;
import br.edu.ifpb.collegialis.entity.Processo;
import br.edu.ifpb.collegialis.entity.Professor;

public class DesignarRelator implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {	        
		EntityManager  em=  (EntityManager) request.getAttribute("em");
		ProcessoDAO processodao = new ProcessoDAO(em);
		ProfessorDAO professordao = new ProfessorDAO(em);
		Professor professor = professordao.find(Integer.parseInt(request.getParameter("idProfessor")));
		Processo processo = processodao.find(Integer.parseInt(request.getParameter("idProcesso")));		
		processodao.beginTransaction();
		processo.setRelator(professor);
		processodao.update(processo);
		processodao.commit();		
        RequestDispatcher d = request.getRequestDispatcher("/processos.jsp");
	}
}