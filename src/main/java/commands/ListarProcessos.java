package commands;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.entity.Processo;

public class ListarProcessos implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		EntityManagerFactory  emf=  Persistence.createEntityManagerFactory("collegialis");
        EntityManager  em=  emf.createEntityManager();
		ProcessoDAO processodao = new ProcessoDAO(em);
//		InsereDadosBanco teste = new InsereDadosBanco();
//		teste.init();
		List<Processo> p = processodao.findAll();
		for(Processo processo : p ){
			System.out.println(p);
		}
	}

}
