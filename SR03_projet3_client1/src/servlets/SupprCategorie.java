package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annuaire1.AdminProxy;

public class SupprCategorie extends HttpServlet {
	public static final String GEST_CAT = "/gestion_categorie";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		Long id = getId(request);
		AdminProxy adminproxy = new AdminProxy();
        adminproxy.supprimerCategorie(id);
        
        response.sendRedirect( request.getContextPath() + GEST_CAT);
    }
	
	private Long getId(HttpServletRequest request) {
    	Long id = null;
    	try {
    		id = Long.valueOf(request.getParameter("id"));
		}catch(NumberFormatException e){}
    	return id;
    }
}
