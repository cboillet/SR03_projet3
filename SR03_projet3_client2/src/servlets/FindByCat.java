package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annuaire1.AdminProxy;

public class FindByCat extends HttpServlet {
	public static final String ATT_ANN_LIST = "list_ann";
	public static final String ATT_CAT_LIST = "list_cat";
	public static final String VUE = "/WEB-INF/find_cat.jsp";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		AdminProxy adminproxy = new AdminProxy();
		String xmlcat = adminproxy.listCategories();
        request.setAttribute(ATT_CAT_LIST, xmlcat);
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		Long cat = Long.valueOf(request.getParameter( "categorie" ));
        AdminProxy adminproxy = new AdminProxy();
        
        String xmlann = adminproxy.listAnnoncesParCategorie(cat);
        request.setAttribute(ATT_ANN_LIST, xmlann);
        String xmlcat = adminproxy.listCategories();
        request.setAttribute(ATT_CAT_LIST, xmlcat);

        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }
}
