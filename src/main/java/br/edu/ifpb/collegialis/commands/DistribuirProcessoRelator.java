package br.edu.ifpb.collegialis.commands;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.entity.Processo;

public class DistribuirProcessoRelator implements Command{
	//TODO Saber se vamos atribuir ao relator na criação do processo
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {	

	}
}