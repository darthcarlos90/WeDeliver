package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import businessObjects.Users;

import dataAccess.usersDAO;

/**
 * Servlet implementation class addUserServlet
 */
@WebServlet("/addUserServlet")
public class addUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addUserServlet() {
		super();
		// TODO Auto-generated constructor stub
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
		HttpSession sesion = request.getSession();
		usersDAO userDao = new usersDAO();
		
		String address;
		synchronized (this) {
			// Ver si ya había un usuario registrado
			Users usuario = (Users) sesion.getAttribute("usuarioRegistrado");
			if (usuario == null) {
				// SI el usuario no existe, pasar el usuario a la sesión.
				usuario = new Users();
				
			}
			// pasarle los parámetros al usuario
			usuario.setApellido(request.getParameter("apellido"));
			usuario.setEmail(request.getParameter("email"));
			usuario.setMatricula(request.getParameter("matricula"));
			usuario.setNombre(request.getParameter("nombre"));
			usuario.setPassword(request.getParameter("password"));
			usuario.setTelefono(request.getParameter("celular"));
			usuario.setCarrera(request.getParameter("carrera"));
			// ver si el usuario ya estaba dado de alta
			if (userDao.userExists(usuario.getEmail()) == true) {
				// si el usuario ya estaba dado de alta, mandar a pagina de error
				address = "errorRegistro.jsp";
			} else {
				// si no, agregarlo a labase de datos
				userDao.addUser(usuario);
				// mandar a la tabla de sesion el usuario registrado para su posterior uso
				sesion.setAttribute("usuarioRegistrado", usuario);
				// mandar a la tabla de sesion el mail del usuario, para su posterior uso
				sesion.setAttribute("user", usuario.getEmail());
				// mandar el parametro activo, indicando que la sesion ya esta activa
				sesion.setAttribute("sesion", "Activa");
				// mandar el nombre del usuario para su posterior uso
				sesion.setAttribute("userName", usuario.getNombre());
				// mandar a la página de validado
				address = "Validado.jsp";
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}
}
