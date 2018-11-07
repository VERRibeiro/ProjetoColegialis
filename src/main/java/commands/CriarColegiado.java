package commands;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.ColegiadoDAO;
import br.edu.ifpb.collegialis.dao.ReuniaoDAO;
import br.edu.ifpb.collegialis.entity.Colegiado;

public class CriarColegiado implements Command{
	private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	public void execute(HttpServletRequest request, HttpServletResponse response) {	
        EntityManager em = (EntityManager) request.getAttribute("em");		
		ColegiadoDAO colegiadodao = new ColegiadoDAO(em);
		Colegiado colegiado = new Colegiado();
		colegiado.setCurso(request.getParameter("curso"));
		colegiado.setDescricao(request.getParameter("descricao"));
		colegiado.setPortaria(request.getParameter("portaria"));
		colegiado.setDataFim(dataFim);
		colegiado.setDataInicio(dataInicio);
		colegiado.setMembros(membros);
		colegiado.setRepresentante(representante);
		colegiado.setReunioes(reunioes);
		String descricao = request.getParameter("descricao");		
		Date data = new Date();
	}
}
