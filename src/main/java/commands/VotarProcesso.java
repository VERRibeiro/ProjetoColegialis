package commands;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.entity.Processo;
import br.edu.ifpb.collegialis.type.TipoDecisao;
import br.edu.ifpb.collegialis.type.TipoStatusProcesso;

public class VotarProcesso implements Command{
	private static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	public void execute(HttpServletRequest request, HttpServletResponse response) {	
		EntityManager  em=  (EntityManager) request.getAttribute("em");
		ProcessoDAO processodao = new ProcessoDAO(em);
		String parecer = request.getParameter("decisao");		
		processodao.beginTransaction();
		Processo processo = processodao.find(Integer.parseInt(request.getParameter("processo")));		
		if(parecer.equals("deferido")){			
			processo.setDecisao(TipoDecisao.DEFERIDO);
		}else{
			processo.setDecisao(TipoDecisao.INDEFERIDO);
		}
		processo.setStatus(TipoStatusProcesso.JULGADO);
		
		processodao.update(processo);
		processodao.commit();
		
		try {
			response.sendRedirect("controller?command=RealizarReuniao");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}