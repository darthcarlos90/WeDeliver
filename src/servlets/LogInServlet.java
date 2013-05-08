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
import dataAccess.usersDAO;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogInServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// traer el parametro email para su uso
		String email = request.getParameter("email");
		// usar la sesión actual
		HttpSession session = request.getSession();
		// traer el password del usuario
		String password = request.getParameter("password");
		// crear conexión
		usersDAO usersDao = new usersDAO();
		String address = "";
		// ver si el usuario que inició sesión es un administrador
		if (usersDao.adminExists(email) == true) {
			// ver si el usuario coincide con el password
			if (usersDao.logInAdmin(email, password) == true) {
				// mandar a la página de inicio del administrador
				address = "inicioAdministrador.jsp";
				// mandar a la tabla de sesión el tipo de sesión que se activó
				session.setAttribute("tipoSesion", "Admin");
				// mandar el email del usuario que inició sesión
				session.setAttribute("user", email);
				// poner la sesión en activa
				session.setAttribute("sesion", "Activa");
				// mandar a la tabla de la sesión el email del usuario
				session.setAttribute("userName", usersDao.getUserName(email));

			} else {
				// si no, mandar a la tabla de error de inicio de sesión
				address = "errorInicioSesion.jsp";
			}
			// ver si el usuario existe en la base de datos
		} else if (usersDao.userExists(email) == true) {
			// inicio de sesión del usuario en caso de que exista
			if (usersDao.logIn(email, password) == true) {
				// mandar a la página principal del usuario
				address = "principalUsuario.jsp";
				// mandar los parametros a la tabla de sesión para su posterior
				// uso
				session.setAttribute("tipoSesion", "Consumer");
				session.setAttribute("user", email);
				session.setAttribute("sesion", "Activa");
				session.setAttribute("userName", usersDao.getUserName(email));
				session.setAttribute("activeUser", usersDao.getUser(email));
			} else {
				// si nada funciona, mandar a la página de error de inicio de
				// sesión
				address = "errorInicioSesion.jsp";
			}
		}
		/*
		 * Recibir las categorías para mostrarlas en el catálogo. Se manda
		 * llamar esto en cuanto se inicia sesión para eliminar un error que
		 * venía apareciendo.
		 */
		articuloDAO aDAO = new articuloDAO();
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();

		try {
			categorias = aDAO.obtenerCategorias();
			session.setAttribute("categorias", categorias);
		} catch (Exception ex) {
			System.out.println(ex);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}