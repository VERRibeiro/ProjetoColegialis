package commands;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import br.edu.ifpb.collegialis.dao.AlunoDAO;
import br.edu.ifpb.collegialis.dao.ColegiadoDAO;
import br.edu.ifpb.collegialis.dao.ProfessorDAO;
import br.edu.ifpb.collegialis.entity.Aluno;
import br.edu.ifpb.collegialis.entity.Colegiado;
import br.edu.ifpb.collegialis.entity.Professor;

public class CriarColegiado implements Command{
	private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	public void execute(HttpServletRequest request, HttpServletResponse response) {	
        EntityManager em = (EntityManager) request.getAttribute("em");		
		ColegiadoDAO colegiadodao = new ColegiadoDAO(em);
		ProfessorDAO professordao = new ProfessorDAO(em);
		AlunoDAO alunodao = new AlunoDAO(em);
		Aluno representante = alunodao.find(Integer.parseInt(request.getParameter("aluno")));
		List<Professor> membrosTSI = new ArrayList<Professor>();
		Professor professor = new Professor();
		String[] membros = request.getParameterValues("professor");		
		for(String s :membros){			
			membrosTSI.add(professordao.find(Integer.parseInt(s)));
		}

				
		colegiadodao.beginTransaction();
		Colegiado colegiado = new Colegiado();
		colegiado.setCurso(request.getParameter("curso"));
		colegiado.setDescricao(request.getParameter("descricao"));
		colegiado.setPortaria(request.getParameter("portaria"));
//		try {
//			colegiado.setDataFim(fmt.parse(request.getParameter("dataFim")));
//			colegiado.setDataInicio(fmt.parse(request.getParameter("dataInicio")));
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		colegiado.setMembros(membrosTSI);
		colegiado.setRepresentante(representante);
		
		colegiadodao.insert(colegiado);
		colegiadodao.commit();
		try {
			response.sendRedirect("controller?command=ListarColegiado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
