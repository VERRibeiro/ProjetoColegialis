package commands;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.dao.ProfessorDAO;
import br.edu.ifpb.collegialis.dao.ReuniaoDAO;
import br.edu.ifpb.collegialis.entity.Reuniao;

public class RealizarReuniao implements Command{
	private static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	public void execute(HttpServletRequest request, HttpServletResponse response) {	
		EntityManager  em=  (EntityManager) request.getAttribute("em");
        ProcessoDAO processodao = new ProcessoDAO(em);
		ProfessorDAO professordao = new ProfessorDAO(em);
		ReuniaoDAO reuniaodao = new ReuniaoDAO(em);
		Reuniao reuniao = reuniaodao.find(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("reuniao", reuniao);
		request.setAttribute("processos", reuniao.getProcessos());
		request.setAttribute("professores", reuniao.getColegiado().getMembros());
		System.out.println(reuniao.getDescricao());
        RequestDispatcher d = request.getRequestDispatcher("/realizarReuniao.jsp");
        try {
			d.forward(request,response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}