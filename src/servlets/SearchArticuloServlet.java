package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import businessObjects.Articulo;

import dataAccess.articuloDAO;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchArticuloServlet")
public class SearchArticuloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchArticuloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO
		String tipoDeBusqueda = request.getParameter("tipoDeBusqueda");
		
		
		
		HttpSession session = request.getSession();
		String address = "";
		ArrayList<Articulo> articulos = new ArrayList<Articulo>();
		articuloDAO aDAO = new articuloDAO();
		try{
		String borrar=(String)request.getParameter("borrar");
		if (borrar.equals("borrar")){
			address="WEB-INF/borrarArticulo.jsp";
		}
			
		
		}catch(Exception e){
			try{
				String cambiar=(String)request.getParameter("cambiar");
				if (cambiar.equals("cambiar")){
					address="WEB-INF/modificarArticulo.jsp";
				}
		}catch(Exception e2){
			address="resultadoBusqueda.jsp";
		}
		}
			
		
		
		if (tipoDeBusqueda.equals("porNombre"))
		{
			String nombre = request.getParameter("nombre");
			articulos = aDAO.getArticulosPorNombre(nombre);
			session.setAttribute("listaArticulos", articulos);
			
		}
		else if (tipoDeBusqueda.equals("porCategoria"))
		{
			int subcategoria = Integer.parseInt(request.getParameter("subcategoria"));
			articulos = aDAO.getArticulosPorSubcategoria(subcategoria);
			session.setAttribute("listaArticulos", articulos);
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
