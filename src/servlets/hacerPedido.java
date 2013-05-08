package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import businessObjects.ArticuloCarrito;
import businessObjects.Carrito;
import businessObjects.Pedido;
import dataAccess.PedidoDAO;
import dataAccess.carritoDAO;

/**
 * Servlet implementation class hacerPedido
 */
@WebServlet("/hacerPedido")
public class hacerPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hacerPedido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				float total = Float.parseFloat(request.getParameter("total"));
				ArrayList<ArticuloCarrito> artCars = (ArrayList<ArticuloCarrito>)session.getAttribute("carrito");
				Date fecha = new Date();
				Pedido pedido = new Pedido();
				pedido.setEmail(email);
				pedido.setTotal(total);
				pedido.setFecha(fecha);
				PedidoDAO pDAO = new PedidoDAO();
				if(pDAO.addPedido(pedido))
				{
					pDAO.agregaArticulosAPedido(artCars, pDAO.getUltimoPedido(email));
					carritoDAO cDAO = new carritoDAO();
					cDAO.cerrarCarrito(email);
				}
				
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex);
			dispatcher = request.getRequestDispatcher("errorInicioSesion.jsp");
			dispatcher.forward(request, response);
		}
	}

}
