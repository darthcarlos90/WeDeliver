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

import businessObjects.ArticuloCarrito;
import businessObjects.Carrito;

import dataAccess.carritoDAO;

/**
 * Servlet implementation class carrito
 */
@WebServlet("/carrito")
public class carrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public carrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher;
		String sesionActiva = (String)session.getAttribute("sesion");
		try
		{
			if(sesionActiva.equals("NoActiva"))
			{
				dispatcher = request.getRequestDispatcher("errorInicioSesion.jsp");
				dispatcher.forward(request, response);
			}
			else if(sesionActiva.equals("Activa"))
			{
				String email = (String)session.getAttribute("user");
				String accion = request.getParameter("accion");
				carritoDAO cDAO = new carritoDAO();
				Carrito carrito = cDAO.getCarritoAbierto(email);
				if (accion.equals("agregar"))
				{
					int articuloId = Integer.parseInt(request.getParameter("productoId"));
					int cantidad = Integer.parseInt(request.getParameter("cantidad"));
					cDAO.agregarACarrito(carrito,articuloId,cantidad);
					dispatcher = request.getRequestDispatcher("carrito?accion=verCarrito");
					dispatcher.forward(request, response);
				} else if (accion.equals("verCarrito")){
					ArrayList<ArticuloCarrito> artCar = cDAO.getArticulosDelCarrito(carrito);
					session.setAttribute("carrito", artCar);
					dispatcher = request.getRequestDispatcher("carrito.jsp");
					dispatcher.forward(request, response);
				} else if (accion.equals("actualiza")){
					int articuloId = Integer.parseInt(request.getParameter("productoId"));
					int cantidad = Integer.parseInt(request.getParameter("cantidad"));
					cDAO.actualizarEnCarrito(carrito,articuloId,cantidad);
				}else if (accion.equals("elimina"))
				{
					int articuloId = Integer.parseInt(request.getParameter("productoId"));
					cDAO.eliminarDeCarrito(carrito,articuloId);
				}
			}
		}
		catch (Exception ex)
		{
			dispatcher = request.getRequestDispatcher("errorInicioSesion.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
