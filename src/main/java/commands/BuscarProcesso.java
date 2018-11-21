package commands;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.entity.Processo;

public class BuscarProcesso implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {	
        EntityManager  em=  (EntityManager) request.getAttribute("em");
		ProcessoDAO processodao = new ProcessoDAO(em);
		Processo p;
		try {
			int id = Integer.parseInt(request.getReader().readLine());
			p = processodao.find(id);
			String numero = p.getNumero();
			String requisitante = p.getRequisitante().getNome();
			String assunto = p.getAssunto().getDescricao();
			String relator = p.getRelator().getNome();
			String processoResponse = "{\"numero\":\""+ numero +"\", \"requisitante\":\""+ requisitante +"\", \"assunto\":\"" + assunto +"\", \"relator\":\"" + relator +"\"}";
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(processoResponse);
			out.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//        try {
//			d.forward(request,response);
//		} catch (ServletException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
