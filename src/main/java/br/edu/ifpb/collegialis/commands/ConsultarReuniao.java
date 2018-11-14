package br.edu.ifpb.collegialis.commands;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.ReuniaoDAO;
import br.edu.ifpb.collegialis.entity.Reuniao;
//Mostrar dados de uma unica reunião buscar pelo ID
public class ConsultarReuniao implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {	
        EntityManager  em=  (EntityManager) request.getAttribute("em");
		ReuniaoDAO reuniaodao = new ReuniaoDAO(em);
		int id = Integer.parseInt(request.getParameter("id"));
		Reuniao p = reuniaodao.find(id);
		request.setAttribute("reuniao", p);
        RequestDispatcher d = request.getRequestDispatcher("/reunioes.jsp");
        try {
			d.forward(request,response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}