package br.edu.ifpb.collegialis.commands;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.dao.ProfessorDAO;
import br.edu.ifpb.collegialis.entity.Processo;
import br.edu.ifpb.collegialis.entity.Professor;
//TODO terminar essa classe
public class ListarMeusProcessos implements Command{
	//TODO Refatorar pra fazer busca no DAO
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {	
        EntityManager  em=  (EntityManager) request.getAttribute("em");		
		ProcessoDAO processodao = new ProcessoDAO(em);
		List<Processo> processos = processodao.findAll();
		List<Processo> processosProfessor = null;
		for(Processo processo : processos){
			if(processo.getRelator().getId().equals(Integer.parseInt(request.getParameter("id"))));
			processosProfessor.add(processo);
		}
		
		request.setAttribute("processos", processosProfessor);
        RequestDispatcher d = request.getRequestDispatcher("/processos.jsp");
        try {
			d.forward(request,response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}