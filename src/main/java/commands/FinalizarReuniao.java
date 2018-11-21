package commands;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.ReuniaoDAO;
import br.edu.ifpb.collegialis.entity.Reuniao;
import br.edu.ifpb.collegialis.type.StatusReuniao;

public class FinalizarReuniao implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		EntityManager  em=  (EntityManager) request.getAttribute("em");	
		ReuniaoDAO reuniaodao = new ReuniaoDAO(em);
		Reuniao reuniao = reuniaodao.find(Integer.parseInt(request.getParameter("id")));
		reuniaodao.beginTransaction();
		reuniao.setStatus(StatusReuniao.ENCERRADA);
		reuniaodao.update(reuniao);
		reuniaodao.commit();
		try {
			response.sendRedirect("controller?command=ListarReunioes");
		} catch (IOException e) {																		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}