package br.edu.ifpb.collegialis.commands;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import br.edu.ifpb.collegialis.dao.AlunoDAO;
import br.edu.ifpb.collegialis.dao.ColegiadoDAO;
import br.edu.ifpb.collegialis.dao.ProfessorDAO;
import br.edu.ifpb.collegialis.dao.ReuniaoDAO;
import br.edu.ifpb.collegialis.entity.Colegiado;
import br.edu.ifpb.collegialis.entity.Professor;

public class CriarColegiado implements Command{
	private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	public void execute(HttpServletRequest request, HttpServletResponse response) {	
        EntityManager em = (EntityManager) request.getAttribute("em");		
		ColegiadoDAO colegiadodao = new ColegiadoDAO(em);
		ProfessorDAO professordao = new ProfessorDAO(em);
		AlunoDAO alunodao = new AlunoDAO(em);
				
		List<Professor> membrosTSI = new ArrayList<Professor>();
		Professor professor = new Professor();
		String[] membros = request.getParameterValues("membros");
		String representante = request.getParameter("representante");
		for(String s :membros){			
			membrosTSI.add(professordao.find(Integer.parseInt(s)));
		}
		Data dataInicio = (Data) new Date();
		Data dataFim = (Data) new Date();
		try {
			dataInicio = (Data) fmt.parse(request.getParameter("dataInicio"));
			dataFim =(Data) fmt.parse(request.getParameter("dataFim"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		colegiadodao.beginTransaction();
		Colegiado colegiado = new Colegiado();
		colegiado.setCurso(request.getParameter("curso"));
		colegiado.setDescricao(request.getParameter("descricao"));
		colegiado.setPortaria(request.getParameter("portaria"));
		colegiado.setDataFim((Date) dataFim);
		colegiado.setDataInicio((Date) dataInicio);
		colegiado.setMembros(membrosTSI);
		colegiado.setRepresentante(alunodao.find(Integer.parseInt(representante)));
		
		colegiadodao.insert(colegiado);
		colegiadodao.commit();
	}
}
