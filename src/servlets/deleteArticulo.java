package servlets;

import java.io.IOException;
import java.sql.SQLException;
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
 * Servlet implementation class deleteArticulo
 */
@WebServlet("/deleteArticulo")
public class deleteArticulo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteArticulo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		int productoid=(Integer.parseInt(request.getParameter("productoId") ));
		
		HttpSession session = request.getSession();
		
		String address = "";
		
		articuloDAO aDAO = new articuloDAO();
		Articulo articulo=null;
		try {
			//articulo=aDAO.getArticulo(productoid);
			aDAO.borrarArticulo(productoid);
			address="articuloBorrado.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			address="articuloBorradoFail.jsp";
			e.printStackTrace();
		}
		
		
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
