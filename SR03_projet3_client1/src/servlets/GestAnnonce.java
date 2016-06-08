package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annuaire1.AdminProxy;

public class GestAnnonce extends HttpServlet {
	public static final String ATT_ANN_LIST = "list_ann";
	public static final String ATT_CAT_LIST = "list_cat";
	public static final String VUE = "/WEB-INF/gest_ann.jsp";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		AdminProxy adminproxy = new AdminProxy();
        String xmlann = adminproxy.listAnnonces();
        request.setAttribute(ATT_ANN_LIST, xmlann);
        String xmlcat = adminproxy.listCategories();
        request.setAttribute(ATT_CAT_LIST, xmlcat);
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		String nom = request.getParameter( "nom" );
		Long cat = Long.valueOf(request.getParameter( "categorie" ));
		Long tel = Long.valueOf(request.getParameter( "tel" ));
		Long numero = Long.valueOf(request.getParameter( "numero" ));
		String rue = request.getParameter( "rue" );
		String ville = request.getParameter( "ville" );
		Long cp = Long.valueOf(request.getParameter( "cp" ));
        AdminProxy adminproxy = new AdminProxy();
        adminproxy.creerAnnonce(nom, tel, cat, numero, rue, cp, ville);
        
        String xmlann = adminproxy.listAnnonces();
        request.setAttribute(ATT_ANN_LIST, xmlann);
        String xmlcat = adminproxy.listCategories();
        request.setAttribute(ATT_CAT_LIST, xmlcat);
        
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}
