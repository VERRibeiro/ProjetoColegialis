package commands;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.collegialis.dao.ColegiadoDAO;
import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.dao.ReuniaoDAO;
import br.edu.ifpb.collegialis.entity.Colegiado;
import br.edu.ifpb.collegialis.entity.Processo;
import br.edu.ifpb.collegialis.entity.Professor;
import br.edu.ifpb.collegialis.entity.Reuniao;
import br.edu.ifpb.collegialis.type.StatusReuniao;

public class CriarReuniao implements Command{
	private static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	public void execute(HttpServletRequest request, HttpServletResponse response) {	
        EntityManager  em=  (EntityManager) request.getAttribute("em");		
		ReuniaoDAO reuniaodao = new ReuniaoDAO(em);
		ProcessoDAO processodao = new ProcessoDAO(em);
		ColegiadoDAO colegiadodao = new ColegiadoDAO(em);
		Colegiado colegiado = colegiadodao.find(Integer.parseInt(request.getParameter("colegiado")));
		String descricao = request.getParameter("descricao");
		HttpSession session = request.getSession();
		Professor p = null;		
		String[] processoString = request.getParameterValues("processo");
		List<Processo> processoList = new ArrayList<Processo>();
//		for(int i = 0; i < processoString.length - 1; i++){
//			processoList.add(processodao.find(Integer.parseInt(processoString[i])));
//		}
//		processoList.add(processodao.find(8));
//		processoList.add(processodao.find(9));
//		System.out.println(processodao.find(9).toString());
		session.getAttribute("usuario");
		if(session.getAttribute("usuario") instanceof Professor)
			p = (Professor) session.getAttribute("usuario");					
		reuniaodao.beginTransaction();
		Reuniao reuniao = new Reuniao();			
		reuniao.setColegiado(colegiado);
		reuniao.setDescricao(descricao);
		reuniao.setProcessos(processoList);
		reuniao.setStatus(StatusReuniao.PLANEJADA);
		try {
			reuniao.setData(fmt.parse(request.getParameter("data")));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(reuniao.toString());
		reuniaodao.insert(reuniao);
		reuniaodao.commit();		
		
		try {
			response.sendRedirect("controller?command=ListarReunioes");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
}
