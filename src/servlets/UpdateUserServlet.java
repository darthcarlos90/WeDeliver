package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataAccess.usersDAO;

import businessObjects.Users;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Users user = new Users();
		user = (Users) session.getAttribute("activeUser");
		usersDAO usersDao = new usersDAO();
		String newNombre = request.getParameter("nombre");
		String newApellido = request.getParameter("apellido");
		String newPassword = request.getParameter("password");
		String newCelular = request.getParameter("celular");
		String newMatricula = request.getParameter("matricula");
		String newCarrera = request.getParameter("carrera");
		usersDao.updateUsuario(user.getEmail(), newNombre, newApellido,
				newPassword, newCelular, newMatricula, newCarrera);
		user.setNombre(newNombre);
		user.setApellido(newApellido);
		user.setPassword(newPassword);
		user.setTelefono(newCelular);
		user.setMatricula(newMatricula);
		user.setCarrera(newCarrera);
		session.setAttribute("activeUser", user);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("principalUsuario.jsp");
		dispatcher.forward(request, response);
	}

}
