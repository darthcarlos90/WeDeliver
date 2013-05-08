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

import businessObjects.Categoria;

import dataAccess.articuloDAO;

/**
 * Servlet implementation class catalogo
 */
@WebServlet("/catalogo")
public class catalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public catalogo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		articuloDAO aDAO = new articuloDAO();
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		HttpSession session = request.getSession();
		
		try
		{
			categorias = aDAO.obtenerCategorias();
			session.setAttribute("categorias", categorias);
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("catalogo.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
