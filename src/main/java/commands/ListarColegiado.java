package commands;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.AlunoDAO;
import br.edu.ifpb.collegialis.dao.ColegiadoDAO;
import br.edu.ifpb.collegialis.dao.ProfessorDAO;
import br.edu.ifpb.collegialis.entity.Aluno;
import br.edu.ifpb.collegialis.entity.Colegiado;
import br.edu.ifpb.collegialis.entity.Professor;

public class ListarColegiado implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {	
        EntityManager  em=  (EntityManager) request.getAttribute("em");
		ColegiadoDAO colegiadodao = new ColegiadoDAO(em);
		ProfessorDAO professordao = new ProfessorDAO(em);
		AlunoDAO alunodao = new AlunoDAO(em);
		List<Colegiado> colegiado = colegiadodao.findAllAtivos();
		List<Aluno> alunos = alunodao.findAll();
		List<Professor> professores = professordao.findAll();
		request.setAttribute("colegiados", colegiado);
		request.setAttribute("alunos", alunos);
		request.setAttribute("professores", professores);
        RequestDispatcher d = request.getRequestDispatcher("/colegiado.jsp");
        try {
			d.forward(request,response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}