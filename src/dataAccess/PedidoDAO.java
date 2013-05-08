package dataAccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import businessObjects.ArticuloCarrito;
import businessObjects.Pedido;
import businessObjects.Resultado;

public class PedidoDAO {
	connectionDB connectionDB;
	private PreparedStatement consultaPedido, insertaPedido, updatePedido,
			insertaArticulosAPedido, getUltimoPedido;
	private Connection connection;

	public PedidoDAO() {
		connectionDB = new connectionDB();
		connection = connectionDB.getConnectionDB();
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<Pedido> getPedidos() {
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		try {
			consultaPedido = connection
					.prepareStatement("SELECT * FROM pedido");
			// obtain list of users
			synchronized (consultaPedido) {
				ResultSet results = consultaPedido.executeQuery();
				// get rows data{
				while (results.next()) {
					Pedido pedido = new Pedido();
					pedido.setEmail(results.getString("email"));
					pedido.setFecha(results.getDate("fecha"));
					pedido.setId(results.getInt("id"));
					pedido.setTotal(results.getFloat("total"));
					pedidos.add(pedido);
				}
			}
			consultaPedido.close();
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
		return pedidos;
	}

	public Pedido getUltimoPedido(String email) {
		Pedido pedido = new Pedido();
		try {
			getUltimoPedido = connection
					.prepareStatement("SELECT * FROM pedido WHERE email = ?");
			getUltimoPedido.setString(1, email);
			synchronized (getUltimoPedido) {
				ResultSet results = getUltimoPedido.executeQuery();
				// get rows data{
				while (results.next()) {
					pedido = new Pedido();
					pedido.setEmail(results.getString("email"));
					pedido.setFecha(results.getDate("fecha"));
					pedido.setId(results.getInt("id"));
					pedido.setTotal(results.getFloat("total"));
				}
			}
			getUltimoPedido.close();
		} catch (SQLException sqle) {
			System.out.println(sqle);
		}
		return pedido;
	}

	public boolean addPedido(Pedido pedido) {
		try {
			insertaPedido = connection
					.prepareStatement("INSERT INTO pedido (email, total) VALUES(?,?)");
			synchronized (insertaPedido) {
				insertaPedido.setString(1, pedido.getEmail());
				insertaPedido.setFloat(2, pedido.getTotal());
				insertaPedido.executeUpdate();
			}
			insertaPedido.close();
			return true;
		} catch (SQLException sqle) {
			System.out.println(sqle);
			return false;
		}

	}

	public void agregaArticulosAPedido(ArrayList<ArticuloCarrito> artCars,
			Pedido pedido) {
		try {
			insertaArticulosAPedido = connection
					.prepareStatement("INSERT INTO articulos_pedidos VALUES (?,?,?)");
			synchronized (insertaArticulosAPedido) {
				for (int i = 0; i < artCars.size(); i++) {

					ArticuloCarrito artCar = artCars.get(i);
					insertaArticulosAPedido.setInt(1, artCar.getArticuloID());
					insertaArticulosAPedido.setInt(2, pedido.getId());
					insertaArticulosAPedido.setInt(3, artCar.getCantidad());
					insertaArticulosAPedido.executeUpdate();
				}
			}
			insertaArticulosAPedido.close();
		} catch (Exception sqle) {
			System.out.println(sqle);
		}
	}

	public void modificarPedido(String email, float total) {
		try {
			updatePedido = connection
					.prepareStatement("UPDATE pedido SET total =? where email = ?");
			synchronized (updatePedido) {
				updatePedido.setFloat(1, total);
				updatePedido.setString(2, email);
				updatePedido.executeUpdate();
			}
			updatePedido.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void deletePedido(int pedidoId) throws SQLException {
		connectionDB.statement.executeUpdate("DELETE FROM pedido WHERE ID="
				+ pedidoId + ";");
	}

	public ArrayList<Resultado> infoPedido(int pedidoId) throws SQLException {
		
		ArrayList<Resultado> resultados=new ArrayList<Resultado>();
		ResultSet results;
		results=connectionDB.statement.executeQuery("select * from articulos_pedidos join articulo where articulos_pedidos.idArticulo=articulo.id and articulos_pedidos.idPedido="+pedidoId+";");
		
		while(results.next()){
			Resultado resultado=new Resultado();
			resultado.setIdPedido(results.getInt("idPedido"));
			resultado.setIdArticulo(results.getInt("idArticulo"));
			resultado.setCantidad(results.getInt("cantidad"));
			resultado.setNombre(results.getString("nombre"));
			resultado.setInformacion(results.getString("informacion"));
			resultado.setPrecio(results.getInt("precio"));
			
			resultados.add(resultado);
		}
		return resultados;
			
	}
}