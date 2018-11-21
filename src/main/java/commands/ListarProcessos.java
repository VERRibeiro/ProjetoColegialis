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
import br.edu.ifpb.collegialis.dao.AssuntoDAO;
import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.dao.ProfessorDAO;
import br.edu.ifpb.collegialis.entity.Aluno;
import br.edu.ifpb.collegialis.entity.Assunto;
import br.edu.ifpb.collegialis.entity.Processo;
import br.edu.ifpb.collegialis.entity.Professor;

//Listar todos os processos
public class ListarProcessos implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {	
        EntityManager  em=  (EntityManager) request.getAttribute("em");
		ProfessorDAO professordao = new ProfessorDAO(em);
		AlunoDAO alunodao = new AlunoDAO(em);
		List<Aluno> alunos = alunodao.findAll();
		List<Professor> professores = professordao.findAll();
		ProcessoDAO processodao = new ProcessoDAO(em);
		AssuntoDAO assuntodao = new AssuntoDAO(em);
		List<Assunto> assuntos = assuntodao.findAll();
		List<Processo> p = processodao.findAll();		
		request.setAttribute("alunos", alunos);
		request.setAttribute("professores", professores);
		request.setAttribute("processos", p);
		request.setAttribute("assuntos", assuntos);
        RequestDispatcher d = request.getRequestDispatcher("/processos.jsp");
        try {
			d.forward(request,response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
