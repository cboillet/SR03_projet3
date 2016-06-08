package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annuaire1.AdminProxy;

public class FindByVille extends HttpServlet {
	public static final String ATT_ANN_LIST = "list_ann";
	public static final String ATT_CAT_LIST = "list_cat";
	public static final String VUE = "/WEB-INF/find_ville.jsp";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		String ville = request.getParameter( "ville" );
        AdminProxy adminproxy = new AdminProxy();
        
        String xmlann = adminproxy.listAnnoncesParVille(ville);
        request.setAttribute(ATT_ANN_LIST, xmlann);
        String xmlcat = adminproxy.listCategories();
        request.setAttribute(ATT_CAT_LIST, xmlcat);
        
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}
