package commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DestribuirProcessoRelator implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {	
		request.getParameter("professor");
	}
}