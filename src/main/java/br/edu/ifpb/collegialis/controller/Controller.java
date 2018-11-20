package br.edu.ifpb.collegialis.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;

import br.edu.ifpb.collegialis.dao.AlunoDAO;
import br.edu.ifpb.collegialis.dao.AssuntoDAO;
import br.edu.ifpb.collegialis.dao.ColegiadoDAO;
import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.dao.ProfessorDAO;
import br.edu.ifpb.collegialis.dao.ReuniaoDAO;
import br.edu.ifpb.collegialis.dao.VotoDAO;
import br.edu.ifpb.collegialis.entity.Aluno;
import br.edu.ifpb.collegialis.entity.Assunto;
import br.edu.ifpb.collegialis.entity.Colegiado;
import br.edu.ifpb.collegialis.entity.Processo;
import br.edu.ifpb.collegialis.entity.Professor;
import br.edu.ifpb.collegialis.entity.Reuniao;
import br.edu.ifpb.collegialis.entity.Usuario;
import br.edu.ifpb.collegialis.entity.Voto;
import br.edu.ifpb.collegialis.type.StatusReuniao;
import br.edu.ifpb.collegialis.type.TipoDecisao;
import br.edu.ifpb.collegialis.type.TipoPerfil;
import br.edu.ifpb.collegialis.type.TipoVoto;
import br.edu.ifpb.collegialis.commands.Command;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        //Executa o Comando conforme a URL
    	
    	EntityManagerFactory  emf=  Persistence.createEntityManagerFactory("collegialis");
        EntityManager  em=  emf.createEntityManager();
        ProfessorDAO professordao = new ProfessorDAO(em);
		AlunoDAO alunodao = new AlunoDAO(em);
        List<Aluno> alunos = alunodao.findAll();
		List<Professor> professores = professordao.findAll();
		Aluno aluno = new Aluno();
		Professor professor = new Professor();
		Usuario usuario = new Usuario();
		for(Aluno a: alunos){
				alunodao.beginTransaction();
				aluno = a;
				usuario.setMatricula(aluno.getMatricula());
				usuario.setSenha("12345");
				usuario.setTipoPerfil(TipoPerfil.SECRETARIO);
				aluno.setUsuario(usuario);
				alunodao.update(aluno);
				alunodao.commit();
		}
		
		for(Professor p : professores){
			professordao.beginTransaction();
			if(professor.getMatricula().equals("1200309"))
				usuario.setTipoPerfil(TipoPerfil.COORDENADOR);
			else
				usuario.setTipoPerfil(TipoPerfil.MEMBRO);
			usuario.setMatricula(professor.getMatricula());
			usuario.setSenha("12345");
			professor.setUsuario(usuario);
			professordao.update(professor);
			professordao.commit();
		}

        Command comando = null;        
	    try {
	        comando = (Command)Class.forName("commands."+request.getParameter("command")).newInstance();
	    } catch (InstantiationException e) {
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	   request.setAttribute("em",em);
       comando.execute(request, response);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request,response);
	}

}
