package commands;

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

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {	
        EntityManager  em=  (EntityManager) request.getAttribute("em");
		ProfessorDAO professordao = new ProfessorDAO(em);
		Professor professor = professordao.find(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("processos", p);
        RequestDispatcher d = request.getRequestDispatcher("/processos.jsp");
        try {
			d.forward(request,response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}