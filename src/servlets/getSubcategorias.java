package servlets;

import java.io.IOException;
import java.util.ArrayList;

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
@WebServlet("/getSubcategorias")
public class getSubcategorias extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getSubcategorias() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		String categorias = "[";
		for(int i = 0; i < subcategorias.size(); i++)
		{
			Subcategoria categoria = new Subcategoria();
			categoria = subcategorias.get(i);
			if ((i + 1) < subcategorias.size())
			{
				categorias += "{\"id\": \"" + categoria.getId() + "\", \"nombre\": \"" + categoria.getNombre() + "\"},";
			}
			else
			{
				categorias += "{\"id\": \"" + categoria.getId() + "\", \"nombre\": \"" + categoria.getNombre() + "\"}";
			}
		}
		categorias += "]";
		response.getWriter().write(categorias);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
