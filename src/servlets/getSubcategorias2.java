package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessObjects.Subcategoria;

import dataAccess.articuloDAO;

/**
 * Servlet implementation class getSubcategorias
 */
@WebServlet("/getSubcategorias2")
public class getSubcategorias2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getSubcategorias2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	private static final Logger log = Logger
			.getLogger(getSubcategorias.class.getName());
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		// TODO Auto-generated method stub
		
		log.info(request.getParameter("idDeCategoria").toString());
		
		int idCategoria = Integer.parseInt(request.getParameter("idDeCategoria"));
		
		articuloDAO aDAO = new articuloDAO();
		
		ArrayList<Subcategoria> subcategorias = new ArrayList<Subcategoria>();
		
		try
		{
			subcategorias = aDAO.obtenerSubCategorias(idCategoria);
		}
		catch (Exception ex)
		{	
			System.out.println(ex);
		}
		
		PrintWriter out=response.getWriter();
		if(subcategorias!=null)
		
		{

			out.println("<select id=\"subcategoriasingroup\" name=\"subcategorias\" >");		
		
		for(int i = 0; i < subcategorias.size(); i++){
			out.println("<option value="+subcategorias.get(i).getId()+">" + subcategorias.get(i).getNombre() + "</option>");
		}
		out.println("</select>");
		}
	
		else		
			log.info("No parameter received");
		
		}

}
