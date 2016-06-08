package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annuaire1.AdminProxy;

public class ModifyCategorie extends HttpServlet {
	public static final String ATT_CAT = "categorie";
	public static final String GEST_CAT = "/gestion_categorie";
	public static final String VUE = "/WEB-INF/modify_cat.jsp";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		Long id = getId(request);
		AdminProxy adminproxy = new AdminProxy();
        String xmlcat = adminproxy.getCategorie(id);
		request.setAttribute(ATT_CAT, xmlcat);
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		Long id = getId(request);
		String nom = request.getParameter( "nom" );
		
		AdminProxy adminproxy = new AdminProxy();
		adminproxy.modifierCategorie(id, nom);
		
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
