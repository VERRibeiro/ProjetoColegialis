package commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.dao.ProfessorDAO;
import br.edu.ifpb.collegialis.entity.Processo;
import br.edu.ifpb.collegialis.entity.Professor;
//TODO terminar essa classe
public class ListarMeusProcessos implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {	
        EntityManager  em=  (EntityManager) request.getAttribute("em");
        ProcessoDAO processodao = new ProcessoDAO(em);
		ProfessorDAO professordao = new ProfessorDAO(em);		
		HttpSession session = request.getSession();		
		Professor p = null;		
		session.getAttribute("usuario");
		if(session.getAttribute("usuario") instanceof Professor)
			p = (Professor) session.getAttribute("usuario");
		List<Processo> processos = processodao.findAll();
		List<Processo> processosReturn = new ArrayList<Processo>();
		
		for(Processo processo : processos){
			if(processo.getRelator().getId().equals(p.getId())){				
				processosReturn.add(processo);
			}
		}
		
		request.setAttribute("processos", processosReturn);
        RequestDispatcher d = request.getRequestDispatcher("/meusProcessos.jsp");
        try {
			d.forward(request,response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}