package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import businessObjects.Users;

public class usersDAO {
	connectionDB connectionDB;
	private PreparedStatement consultaUsuario, insertaUsuario, updateUsuario;
	private Connection connection;

	public usersDAO() {
		// Conexión a la base de datos.
		connectionDB = new connectionDB();
		connection = connectionDB.getConnectionDB();
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Método que regresa la lista de usuarios registrados en la base de datos.
	 * 
	 * @return Lista de usuarios registrados en la base de datos.
	 */
	public ArrayList<Users> getListaUsers() {
		ArrayList<Users> usersList = new ArrayList<Users>();
		try {
			consultaUsuario = connection
					.prepareStatement("SELECT * FROM usuario");
			// obtain list of users
			synchronized (consultaUsuario) {
				ResultSet results = consultaUsuario.executeQuery();
				// get rows data{
				while (results.next()) {
					Users usuario = new Users();
					usuario.setEmail(results.getString("email"));
					usuario.setMatricula(results.getString("matricula"));
					usuario.setNombre(results.getString("nombre"));
					usuario.setPassword(results.getString("password"));
					usuario.setTelefono(results.getString("celular"));
					usuario.setApellido(results.getString("apellidos"));
					usuario.setCarrera(results.getString("carrera"));
					usersList.add(usuario);
				}
			}
			consultaUsuario.close();
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
		return usersList;
	}

	/**
	 * Método que revisa si existe el usuario administrador en la base de datos.
	 * 
	 * @param Email
	 *            del administrador.
	 * @return True si el administrador existe, Falso si no existe.
	 */
	public boolean adminExists(String email) {
		try {
			consultaUsuario = connection
					.prepareStatement("SELECT * FROM administrador WHERE email = ?");
			consultaUsuario.setString(1, email);
			synchronized (consultaUsuario) {
				ResultSet results = consultaUsuario.executeQuery();
				if (results.next()) {
					return true;
				} else
					return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Método que regresa el usuario en la base de datos que coincide con el
	 * email que se da de entrada en los parámetros.
	 * 
	 * @param Email
	 *            del usuario a buscar.
	 * @return El usuario que coincide con el email que se da de entrada.
	 */
	public Users getUser(String email) {
		Users usuario = new Users();
		if (userExists(email) == false) {
			usuario = null;
		} else {
			try {
				consultaUsuario = connection
						.prepareStatement("SELECT * FROM usuario where email =?");
				consultaUsuario.setString(1, email);
				// obtain list of users
				synchronized (consultaUsuario) {
					ResultSet results = consultaUsuario.executeQuery();
					// get rows data{
					results.next();
					usuario.setEmail(results.getString("email"));
					usuario.setMatricula(results.getString("matricula"));
					usuario.setNombre(results.getString("nombre"));
					usuario.setPassword(results.getString("password"));
					usuario.setTelefono(results.getString("celular"));
					usuario.setApellido(results.getString("apellidos"));
					usuario.setCarrera(results.getString("carrera"));

				}
				consultaUsuario.close();
			} catch (SQLException sqle) {
				System.out.println(sqle);
			}
		}
		return usuario;
	}

	/**
	 * Método que le hace cambios al usuario registrado en la base de datos.
	 * 
	 * @param Nuevo
	 *            email del usuario.
	 * @param Nuevo
	 *            nombre del usuario.
	 * @param Nuevos
	 *            apellidos del usuario.
	 * @param Nuevo
	 *            password del usuario.
	 * @param Nuevo
	 *            celular del usuario.
	 * @param Nueva
	 *            matrícula del usuario.
	 * @param Nueva
	 *            carrera del usuario.
	 */
	public void updateUsuario(String email, String nombre, String apellidos,
			String password, String celular, String matricula, String carrera) {
		try {
			updateUsuario = connection
					.prepareStatement("UPDATE usuario SET nombre=?, apellidos=?, PASSWORD=?,celular=?,matricula=?,carrera=? where email = ?");
			synchronized (updateUsuario) {

				updateUsuario.setString(1, nombre);
				updateUsuario.setString(2, apellidos);
				updateUsuario.setString(3, password);
				updateUsuario.setString(4, celular);
				updateUsuario.setString(5, matricula);
				updateUsuario.setString(6, carrera);
				updateUsuario.setString(7, email);
				updateUsuario.executeUpdate();
			}
			updateUsuario.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Método que revisa si el usuario existe en la base de datos.
	 * 
	 * @param Email
	 *            del usuario a buscar.
	 * @return True si el usuario existe, False si el usuario no existe.
	 */
	public boolean userExists(String email) {
		try {
			consultaUsuario = connection
					.prepareStatement("SELECT * FROM usuario WHERE email = ?");
			consultaUsuario.setString(1, email);
			synchronized (consultaUsuario) {
				ResultSet results = consultaUsuario.executeQuery();
				if (results.next()) {
					return true;
				} else
					return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Método que regresa el nombre del usuario que tiene que coincide con el
	 * email que se da de entrada.
	 * 
	 * @param Email
	 *            del usuario que se busca.
	 * @return El nombre del usuario que coincide con el email.
	 */
	public String getUserName(String email) {
		if (userExists(email) == true) {
			try {
				consultaUsuario = connection
						.prepareStatement("SELECT nombre FROM usuario WHERE email = ?");
				consultaUsuario.setString(1, email);
				synchronized (consultaUsuario) {
					ResultSet results = consultaUsuario.executeQuery();
					results.next();
					return results.getString("nombre");
				}
			} catch (Exception e) {
				return null;
			}
		} else

			return null;

	}

	/**
	 * Método que revisa si el email coincide con la contraseña registrada en la
	 * base de datos.
	 * 
	 * @param Email
	 *            del usuario.
	 * @param Password
	 *            del usuario.
	 * @return True si el usuario y contraseña coincide, false si no existen.
	 */
	public boolean logIn(String email, String password) {
		try {
			consultaUsuario = connection
					.prepareStatement("SELECT * FROM usuario WHERE email = ?");
			consultaUsuario.setString(1, email);
			synchronized (consultaUsuario) {
				ResultSet results = consultaUsuario.executeQuery();
				results.next();
				String otherpassword = results.getString("password");
				if (otherpassword.equals(password)) {
					return true;
				} else
					return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Método que revisa si el usuario administrador coincide con la contraseña
	 * que esta en la base de datos.
	 * 
	 * @param Email
	 *            del administrador.
	 * @param Contraseña
	 *            del administrador.
	 * @return True si se puede iniciar sesión, False si no es posible.
	 */
	public boolean logInAdmin(String email, String password) {
		try {
			consultaUsuario = connection
					.prepareStatement("SELECT * FROM administrador WHERE email = ?");
			consultaUsuario.setString(1, email);
			synchronized (consultaUsuario) {
				ResultSet results = consultaUsuario.executeQuery();
				results.next();
				String otherpassword = results.getString("password");
				if (otherpassword.equals(password)) {
					return true;
				} else
					return false;

			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Método que cambia la contraseña del usuario.
	 * 
	 * @param Correo
	 *            del usuario.
	 * @param Nuevo
	 *            password para el usuario.
	 */
	public void changePasword(String email, String newPassword) {
		try {

			consultaUsuario = connection
					.prepareStatement("UPDATE usuario SET PASSWORD = ? WHERE email = ?");
			consultaUsuario.setString(1, newPassword);
			consultaUsuario.setString(2, email);
			synchronized (consultaUsuario) {
				consultaUsuario.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que agrega un usuario a la base de datos.
	 * 
	 * @param El
	 *            usuario a agregar a la base de datos.
	 */
	public void addUser(Users usuario) {
		try {
			insertaUsuario = connection
					.prepareStatement("INSERT INTO usuario VALUES(?,?,?,?,?,?,?)");
			synchronized (insertaUsuario) {
				insertaUsuario.setString(2, usuario.getNombre());
				insertaUsuario.setString(5, usuario.getTelefono());
				insertaUsuario.setString(1, usuario.getEmail());
				insertaUsuario.setString(4, usuario.getPassword());
				insertaUsuario.setString(6, usuario.getMatricula());
				insertaUsuario.setString(3, usuario.getApellido());
				insertaUsuario.setString(7, usuario.getCarrera());
				insertaUsuario.executeUpdate();
			}
			insertaUsuario.close();
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}

	}

	/**
	 * Método que cierra la conexión a la base de datos.
	 */
	public void finalize() {
		connectionDB.closeConnectionDB();
	}

}
