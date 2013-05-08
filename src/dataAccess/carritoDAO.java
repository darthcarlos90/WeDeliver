package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import businessObjects.ArticuloCarrito;
import businessObjects.Carrito;

public class carritoDAO {

	connectionDB connectionDB;
	private PreparedStatement getCarritos, getCarritoAbierto,
			agregarCarrito, agregarACarrito, getArticulosDelCarrito, cerrarCarrito;
	private Connection connection;

	public carritoDAO() {
		connectionDB = new connectionDB();
		connection = connectionDB.getConnectionDB();
	}

	public ArrayList<Carrito> getCarritos(String email) {
		ArrayList<Carrito> carritos = new ArrayList<Carrito>();
		try {
			getCarritos = connection
					.prepareStatement("SELECT * FROM carrito WHERE email = ?");
			getCarritos.setString(1, email);
			synchronized (getCarritos) {
				ResultSet results = getCarritos.executeQuery();
				while (results.next()) {
					Carrito carrito = new Carrito();
					carrito.setEmail(results.getString("email"));
					carrito.setSesionCarrito(results.getInt("sesionCarrito"));
					carrito.setTerminado(results.getBoolean("terminado"));
					carritos.add(carrito);
				}
			}
			getCarritos.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return carritos;

	}

	public Carrito getCarritoAbierto(String email) {
		ArrayList<Carrito> carritos = new ArrayList<Carrito>();
		try {
			getCarritoAbierto = connection
					.prepareStatement("SELECT * FROM carrito WHERE email = ?");
			getCarritoAbierto.setString(1, email);
			synchronized (getCarritoAbierto) {
				ResultSet results = getCarritoAbierto.executeQuery();
				while (results.next()) {
					Carrito carrito = new Carrito();
					carrito.setEmail(results.getString("email"));
					carrito.setSesionCarrito(results.getInt("sesionCarrito"));
					carrito.setTerminado(results.getBoolean("terminado"));
					carritos.add(carrito);
				}
			}
			getCarritoAbierto.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		if (carritos.size() <= 0)
		{
			return agregarCarrito(email);
		}
		else
		{
			boolean carritoAbierto = false;
			Carrito tmp = new Carrito();
			for(int i = 0; i < carritos.size(); i++)
			{
				tmp = carritos.get(i);
				if(!tmp.isTerminado())
				{
					carritoAbierto = true;
				}
			}
			if(carritoAbierto)
			{
				return tmp;
			}
			else
			{
				return agregarCarrito(email);
			}
		}
	}
	
	public Carrito agregarCarrito(String email) {
		try {
			agregarCarrito = connection
					.prepareStatement("INSERT INTO carrito (email) VALUES (?)");
			agregarCarrito.setString(1, email);
			agregarCarrito.executeUpdate();
			agregarCarrito.close();
		} catch (Exception ex){
			System.out.println(ex);
		}
		
		ArrayList<Carrito> carritos = new ArrayList<Carrito>();
		try {
			getCarritoAbierto = connection
					.prepareStatement("SELECT * FROM carrito WHERE email = ?");
			getCarritoAbierto.setString(1, email);
			synchronized (getCarritoAbierto) {
				ResultSet results = getCarritoAbierto.executeQuery();
				while (results.next()) {
					Carrito carrito = new Carrito();
					carrito.setEmail(results.getString("email"));
					carrito.setSesionCarrito(results.getInt("sesionCarrito"));
					carrito.setTerminado(results.getBoolean("terminado"));
					carritos.add(carrito);
				}
			}
			getCarritoAbierto.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return carritos.get(carritos.size() - 1);
	}
	
	public void agregarACarrito(Carrito carrito, int idArticulo, int cantidad)
	{
		try {
			agregarACarrito = connection
					.prepareStatement("INSERT INTO articulo_carrito VALUES (?,?,?)");
			agregarACarrito.setInt(1, carrito.getSesionCarrito());
			agregarACarrito.setInt(2, idArticulo);
			agregarACarrito.setInt(3, cantidad);
			agregarACarrito.executeUpdate();
			agregarACarrito.close();
		} catch (Exception ex){
			System.out.println(ex);
		}
	}
	
	public void actualizarEnCarrito(Carrito carrito, int idArticulo, int cantidad)
	{
		try {
			agregarACarrito = connection
					.prepareStatement("UPDATE articulo_carrito SET cantidad = ? WHERE sesionCarrito = ? AND articuloId = ?");
			agregarACarrito.setInt(2, carrito.getSesionCarrito());
			agregarACarrito.setInt(3, idArticulo);
			agregarACarrito.setInt(1, cantidad);
			agregarACarrito.executeUpdate();
			agregarACarrito.close();
		} catch (Exception ex){
			System.out.println(ex);
		}
	}
	
	public void eliminarDeCarrito(Carrito carrito, int idArticulo)
	{
		try {
			agregarACarrito = connection
					.prepareStatement("DELETE FROM articulo_carrito WHERE sesionCarrito = ? AND articuloId = ?");
			agregarACarrito.setInt(1, carrito.getSesionCarrito());
			agregarACarrito.setInt(2, idArticulo);
			agregarACarrito.executeUpdate();
			agregarACarrito.close();
		} catch (Exception ex){
			System.out.println(ex);
		}
	}
	
	public ArrayList<ArticuloCarrito> getArticulosDelCarrito(Carrito carrito)
	{
		ArrayList<ArticuloCarrito> articulosEnCarrito = new ArrayList<ArticuloCarrito>();
		try {
			getArticulosDelCarrito = connection
					.prepareStatement("SELECT * FROM articulo_carrito WHERE sesionCarrito = ?");
			getArticulosDelCarrito.setInt(1, carrito.getSesionCarrito());
			synchronized (getArticulosDelCarrito) {
				ResultSet results = getArticulosDelCarrito.executeQuery();
				while (results.next()) {
					ArticuloCarrito artCar = new ArticuloCarrito();
					artCar.setSesionCarrito(results.getInt("sesionCarrito"));
					artCar.setArticuloID(results.getInt("articuloId"));
					artCar.setCantidad(results.getInt("cantidad"));
					articulosEnCarrito.add(artCar);
				}
			}
			getArticulosDelCarrito.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return articulosEnCarrito;
	}
	
	public void cerrarCarrito(String email)
	{
		try {
			cerrarCarrito = connection
					.prepareStatement("UPDATE carrito SET terminado = ? WHERE sesionCarrito = ?");
			cerrarCarrito.setBoolean(1, true);
			cerrarCarrito.setInt(2, getCarritoAbierto(email).getSesionCarrito());
			cerrarCarrito.executeUpdate();
			cerrarCarrito.close();
		} catch (Exception ex){
			System.out.println(ex);
		}
	}
}
