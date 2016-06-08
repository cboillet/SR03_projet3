package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annuaire1.AdminProxy;

public class ModifyAnnonce extends HttpServlet {
	public static final String ATT_ANN = "annonce";
	public static final String ATT_CAT_LIST = "list_cat";
	public static final String GEST_ANN = "/gestion_annonce";
	public static final String VUE = "/WEB-INF/modify_ann.jsp";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		Long id = getId(request);
		AdminProxy adminproxy = new AdminProxy();
        String xmlann = adminproxy.getAnnonce(id);
		request.setAttribute(ATT_ANN, xmlann);
		String xmlcat = adminproxy.listCategories();
        request.setAttribute(ATT_CAT_LIST, xmlcat);
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		Long id = getId(request);
		String nom = request.getParameter( "nom" );
		Long cat = Long.valueOf(request.getParameter( "categorie" ));
		String tel = request.getParameter( "tel" );
		Long numero = Long.valueOf(request.getParameter( "numero" ));
		String rue = request.getParameter( "rue" );
		String ville = request.getParameter( "ville" );
		String cp = request.getParameter( "cp" );
		
		AdminProxy adminproxy = new AdminProxy();
		adminproxy.modifierAnnonce(id, nom, tel, cat, numero, rue, cp, ville);
		
		String xmlann = adminproxy.getAnnonce(id);
		request.setAttribute(ATT_ANN, xmlann);
		String xmlcat = adminproxy.listCategories();
        request.setAttribute(ATT_CAT_LIST, xmlcat);
		
		response.sendRedirect( request.getContextPath() + GEST_ANN);
    }
	
	private Long getId(HttpServletRequest request) {
    	Long id = null;
    	try {
    		id = Long.valueOf(request.getParameter("id"));
		}catch(NumberFormatException e){}
    	return id;
    }
}
