package commands;

import java.io.IOException;
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
//Listar todas as reuni�es
public class ListarReunioes implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {	
        EntityManager  em=  (EntityManager) request.getAttribute("em");
		ReuniaoDAO reuniaodao = new ReuniaoDAO(em);
		ProcessoDAO processodao = new ProcessoDAO(em);
		ColegiadoDAO colegiadodao = new ColegiadoDAO(em);
		List<Processo> processo = processodao.findAll();
		List<Reuniao> p = reuniaodao.findAll();
		List<Colegiado> colegiado = colegiadodao.findAllAtivos();
		request.setAttribute("reunioes", p);
		request.setAttribute("processos", processo);
		request.setAttribute("colegiados", colegiado);
		
        RequestDispatcher d = request.getRequestDispatcher("/reunioes.jsp");
        try {
			d.forward(request,response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

