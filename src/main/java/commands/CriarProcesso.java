package commands;

import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.AlunoDAO;
import br.edu.ifpb.collegialis.dao.AssuntoDAO;
import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.dao.ProfessorDAO;
import br.edu.ifpb.collegialis.dao.ReuniaoDAO;
import br.edu.ifpb.collegialis.entity.Processo;
import br.edu.ifpb.collegialis.entity.Professor;
import br.edu.ifpb.collegialis.entity.Reuniao;
import br.edu.ifpb.collegialis.type.StatusReuniao;
import br.edu.ifpb.collegialis.type.TipoDecisao;
import br.edu.ifpb.collegialis.type.TipoStatusProcesso;
import br.edu.ifpb.collegialis.entity.Aluno;
import br.edu.ifpb.collegialis.entity.Assunto;

public class CriarProcesso implements Command{
	private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {	
		
        EntityManager  em=  (EntityManager) request.getAttribute("em");        
		ProcessoDAO processodao = new ProcessoDAO(em);
		AssuntoDAO assuntodao = new AssuntoDAO(em);
		ProfessorDAO professordao = new ProfessorDAO(em);
		AlunoDAO alunodao = new AlunoDAO(em);

		
		processodao.beginTransaction();
		Processo processo = new Processo();
		processo.setNumero(request.getParameter("numero"));
		processo.setAssunto(assuntodao.find(Integer.parseInt(request.getParameter("assunto"))));		
		processo.setRequisitante(alunodao.find(Integer.parseInt(request.getParameter("aluno"))));
		System.out.println(processo.toString());
		processodao.insert(processo);
		processodao.commit();
		
		try {
			response.sendRedirect("controller?command=ListarProcessos");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
