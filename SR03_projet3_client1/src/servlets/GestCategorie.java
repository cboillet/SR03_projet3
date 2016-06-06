package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annuaire1.AdminProxy;

public class GestCategorie extends HttpServlet {
	public static final String ATT_CAT_LIST = "list_cat";
	public static final String VUE = "/WEB-INF/gest_cat.jsp";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		AdminProxy adminproxy = new AdminProxy();
        String xmlcat = "<liste><categorie><id>1</id><nom>categorie1</nom></categorie><categorie><id>2</id><nom>categorie2</nom></categorie></liste>";//adminproxy.listCategorie();
        request.setAttribute(ATT_CAT_LIST, xmlcat);
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		String nom = request.getParameter( "nom" );
        AdminProxy adminproxy = new AdminProxy();
        adminproxy.creerCategorie(nom);
        
        String xmlcat = "<liste><categorie><id>1</id><nom>categorie1</nom></categorie><categorie><id>2</id><nom>categorie2</nom></categorie></liste>";//adminproxy.listCategorie();
        request.setAttribute(ATT_CAT_LIST, xmlcat);
        
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}
