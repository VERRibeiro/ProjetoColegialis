package commands;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.AlunoDAO;
import br.edu.ifpb.collegialis.dao.AssuntoDAO;
import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.dao.ProfessorDAO;
import br.edu.ifpb.collegialis.entity.Processo;

public class DestribuirProcessoRelator implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {	
        EntityManager  em=  (EntityManager) request.getAttribute("em");        
		ProcessoDAO processodao = new ProcessoDAO(em);
		AssuntoDAO assuntodao = new AssuntoDAO(em);
		ProfessorDAO professordao = new ProfessorDAO(em);
		AlunoDAO alunodao = new AlunoDAO(em);
		Processo processo = processodao.find(Integer.parseInt(request.getParameter("processo")));
		processo.setRelator(professordao.find(Integer.parseInt(request.getParameter("professor"))));
		processodao.beginTransaction();
		processodao.update(processo);
		processodao.commit();
		
		try {
			response.sendRedirect("controller?command=ListarProcessoRelator");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}