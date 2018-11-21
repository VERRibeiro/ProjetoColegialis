package commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.AlunoDAO;
import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.dao.ProfessorDAO;
import br.edu.ifpb.collegialis.entity.Processo;
import br.edu.ifpb.collegialis.entity.Professor;

public class ListarProcessoRelator implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {	
        EntityManager  em=  (EntityManager) request.getAttribute("em");
		ProfessorDAO professordao = new ProfessorDAO(em);
		AlunoDAO alunodao = new AlunoDAO(em);		
		List<Professor> professores = professordao.findAll();
		ProcessoDAO processodao = new ProcessoDAO(em);
		List<Processo> processos = new ArrayList<Processo>();
		List<Processo> p = processodao.findAll();				
		for(Processo processo : p){
			if(processo.getRelator() == null)
				processos.add(processo);
		}
		request.setAttribute("professores", professores);
		request.setAttribute("processos", processos);
		
        RequestDispatcher d = request.getRequestDispatcher("/destribuirProcessoRelator.jsp");
        try {
			d.forward(request,response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
