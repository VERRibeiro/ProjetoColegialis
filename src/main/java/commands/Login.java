package commands;

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
import br.edu.ifpb.collegialis.type.TipoPerfil;

public class Login implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		EntityManager  em=  (EntityManager) request.getAttribute("em");		
		String matricula = request.getParameter("matricula");
		ProfessorDAO professordao = new ProfessorDAO(em);
		AlunoDAO alunodao = new AlunoDAO(em);
		List<Aluno> alunos = alunodao.findAll();
		List<Professor> professores = professordao.findAll();	
		Aluno aluno = new Aluno();
		Professor professor = new Professor();
		for(Aluno a: alunos){
			if(a.getMatricula().equals(matricula)){
				HttpSession session = request.getSession();
				aluno = a;
				session.setAttribute("role", "ALUNO");
				session.setAttribute("usuario", a);
			}
		}
		for(Professor p : professores){
			if(p.getMatricula().equals(matricula)){
				HttpSession session = request.getSession();
				if(matricula.equals("1200309"))
					session.setAttribute("role", "COORDENADOR");
				else
					session.setAttribute("role", "PROFESSOR");
				professor = p;
				session.setAttribute("usuario", p);
			}
		}
		RequestDispatcher a = request.getRequestDispatcher("/login.jsp");
		if(professor == null && aluno == null) {
			try {
				a.forward(request,response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
		RequestDispatcher d = request.getRequestDispatcher("/index.jsp");
		try {
			d.forward(request,response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	}

}
