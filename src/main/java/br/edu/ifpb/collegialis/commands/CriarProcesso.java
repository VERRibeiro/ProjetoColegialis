package br.edu.ifpb.collegialis.commands;

import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.AlunoDAO;
import br.edu.ifpb.collegialis.dao.AssuntoDAO;
import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.dao.ProfessorDAO;
import br.edu.ifpb.collegialis.dao.ReuniaoDAO;
import br.edu.ifpb.collegialis.entity.Processo;
import br.edu.ifpb.collegialis.entity.Professor;
import br.edu.ifpb.collegialis.entity.Reuniao;
import br.edu.ifpb.collegialis.type.StatusReuniao;
import br.edu.ifpb.collegialis.type.TipoDecisao;
import br.edu.ifpb.collegialis.type.TipoStatusProcesso;
import br.edu.ifpb.collegialis.entity.Aluno;
import br.edu.ifpb.collegialis.entity.Assunto;

public class CriarProcesso implements Command{
	private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {	
		
        EntityManager  em=  (EntityManager) request.getAttribute("em");        
		ProcessoDAO processodao = new ProcessoDAO(em);
		AssuntoDAO assuntodao = new AssuntoDAO(em);
		ProfessorDAO professordao = new ProfessorDAO(em);
		AlunoDAO alunodao = new AlunoDAO(em);
		ReuniaoDAO reuniaodao = new ReuniaoDAO(em);
		System.out.println("COMEÇOUUUUUUU");
		System.out.println(request.getParameter("numero"));
		Date dataRecepcao = new Date();
		Date dataDistribuicao = new Date();
		Date dataParecer = new Date();					 		
		String numero = request.getParameter("numero");	
		try {
			dataRecepcao = fmt.parse(request.getParameter("dataRecepcao"));
			dataDistribuicao = fmt.parse(request.getParameter("dataDistribuicao"));
			dataParecer = fmt.parse(request.getParameter("dataParecer"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
//		List<Reuniao> r = reuniaodao.findAll();
		//byte[] parecer = request.getParameter("parecer").getBytes();;
		String decisaoRelator = request.getParameter("decisaoRelator");
//		String reuniao = request.getParameter("reuniao");
//		
//		Reuniao robject = new Reuniao();
//		for(Reuniao reu : r){
//			if(reu.getData().equals(reuniao))
//				robject = reu;
//		}
		Assunto assunto = assuntodao.find(Integer.parseInt((request.getParameter("assunto"))));
		String aluno = request.getParameter("aluno");
		List<Aluno> a = alunodao.findAll();
		//TODO passar isso pra classe professorDAO
		Aluno aobject = new Aluno();
		for(Aluno alu : a){
			if(alu.getNome().equals(aluno))
				aobject = alu;
		}
		String professor = request.getParameter("professor");	
		List<Professor> p = professordao.findAll();
		//TODO passar isso pra classe professorDAO
		Professor pobject = new Professor();
		for(Professor pro : p){
			if(pro.getNome().equals(professor))
				pobject = pro;
		}		
		Processo processo = new Processo();
				
		processodao.beginTransaction();
		processo.setAssunto(assunto);
		processo.setDataDistribuicao(dataDistribuicao);
		processo.setDataRecepcao(dataRecepcao);
		processo.setDataParecer(dataParecer);
		processo.setNumero(numero);
		//processo.setParecer(parecer);
		if(request.getParameter("decisaoRelator").equals("deferido"))
			processo.setDecisao(TipoDecisao.DEFERIDO);
		else
			processo.setDecisao(TipoDecisao.INDEFERIDO);
		processo.setRelator(pobject);
		processo.setRequisitante(aobject);			
		if(request.getParameter("status").equals("julgado"))				
			processo.setStatus(TipoStatusProcesso.JULGADO);
		else
			processo.setStatus(TipoStatusProcesso.RETIRADO);
		//processo.setReuniao(robject);
		processodao.insert(processo);
		System.out.println("QUASSEEEEEEEEEEEEEEEEEEE");
		processodao.commit();
		System.out.println("CHEGOUUUUUUUUUUUU");
        RequestDispatcher d = request.getRequestDispatcher("/Login.jsp");
        try {
			d.forward(request,response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
