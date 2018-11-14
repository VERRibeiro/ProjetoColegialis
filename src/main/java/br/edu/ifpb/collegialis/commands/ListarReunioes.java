package br.edu.ifpb.collegialis.commands;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.dao.ReuniaoDAO;
import br.edu.ifpb.collegialis.entity.Processo;
import br.edu.ifpb.collegialis.entity.Reuniao;
//Listar todas as reuniões
public class ListarReunioes implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {	
        EntityManager  em=  (EntityManager) request.getAttribute("em");
		ReuniaoDAO reuniaodao = new ReuniaoDAO(em);
		List<Reuniao> p = reuniaodao.findAll();
		request.setAttribute("reunioes", p);
        RequestDispatcher d = request.getRequestDispatcher("/reunioes.jsp");
        try {
			d.forward(request,response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

