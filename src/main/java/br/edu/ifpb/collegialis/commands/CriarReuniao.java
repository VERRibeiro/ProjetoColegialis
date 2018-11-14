package br.edu.ifpb.collegialis.commands;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.ColegiadoDAO;
import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.dao.ReuniaoDAO;
import br.edu.ifpb.collegialis.entity.Colegiado;
import br.edu.ifpb.collegialis.entity.Processo;
import br.edu.ifpb.collegialis.entity.Reuniao;
import br.edu.ifpb.collegialis.type.StatusReuniao;

public class CriarReuniao implements Command{
	private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	public void execute(HttpServletRequest request, HttpServletResponse response) {	
        EntityManager  em=  (EntityManager) request.getAttribute("em");		
		ReuniaoDAO reuniaodao = new ReuniaoDAO(em);
		ColegiadoDAO colegiadodao = new ColegiadoDAO(em);
		String descricao = request.getParameter("descricao");
		Colegiado colegiado = colegiadodao.find(Integer.parseInt(request.getParameter("colegiado")));
		Date data = new Date();
		reuniaodao.beginTransaction();
		Reuniao reuniao = new Reuniao();		
		reuniao.setColegiado(colegiado);
		reuniao.setDescricao(descricao);
		try {
			data = fmt.parse(request.getParameter("data"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(request.getParameter("statusReuniao").equals("encerrada"))
			reuniao.setStatus(StatusReuniao.ENCERRADA);
		else
			reuniao.setStatus(StatusReuniao.PLANEJADA);
		reuniao.setData(data);
		reuniaodao.insert(reuniao);
		reuniaodao.commit();		
		RequestDispatcher d = request.getRequestDispatcher("/Login.jsp");//TODO Pagina de listagem
        try {
			d.forward(request,response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
