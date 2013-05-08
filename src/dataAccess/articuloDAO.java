package dataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import businessObjects.*;

import businessObjects.Articulo;

public class articuloDAO {
	connectionDB connectionDB;
	private PreparedStatement getArticulosPorSubcategoria, getArticulosPorNombre;
	private Connection connection;
	
	private boolean subcategoriat;
	
	public articuloDAO()
	{
		connectionDB = new connectionDB();
		connection = connectionDB.getConnectionDB();
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public ArrayList<Marca> obtenerMarcas() throws SQLException{
		ArrayList<Marca> marcas = new ArrayList<Marca>();
		ResultSet results=connectionDB.statement.executeQuery("SELECT id, nombre from marca");
		
		while(results.next()){
			Marca marca=new Marca();
			marca.setId(results.getInt("id"));
			marca.setNombre(results.getString("nombre"));
			
			marcas.add(marca);
		}
		
		
		return marcas;
	}
	
	public Articulo getArticulo(int id) throws SQLException{
		Articulo art = new Articulo();
		ResultSet results=connectionDB.statement.executeQuery("SELECT * from articulo WHERE id="+id);
		while(results.next()){
			art.setId(results.getInt("id"));
			art.setNombre(results.getString("nombre"));
			art.setCantidad(results.getInt("cantidad"));
			art.setPrecio(results.getFloat("precio"));
			art.setInformacion(results.getString("informacion"));
			art.setIdMarca(results.getInt("idMarca"));
			art.setIdSubcategoria(results.getInt("idSubcategoria"));
		}
		return art;
	}
	
	public void borrarArticulo(int id) throws SQLException{
		connectionDB.statement.executeUpdate("DELETE FROM articulo WHERE ID="+id+";");
	}
	
	public void updetearArticulo(int id,Articulo art) throws SQLException{
		connectionDB.statement.executeUpdate("UPDATE articulo set nombre='"+art.getNombre()+"', cantidad="+art.getCantidad()+",precio="+art.getPrecio()+",informacion='"+art.getInformacion()+"',idMarca="+art.getIdMarca()+",idSubcategoria="+art.getIdSubcategoria()+" WHERE ID="+id+";");
	}
	
	public ArrayList<Categoria> obtenerCategorias() throws SQLException{
		ArrayList<Categoria> categorias = new ArrayList<Categoria>();
		ResultSet results = connectionDB.statement.executeQuery("SELECT id,nombre from categoria");
		while (results.next()){
			Categoria categoria = new Categoria();
			categoria.setId(results.getInt("id"));
			categoria.setNombre(results.getString("nombre"));
			
			categorias.add(categoria);
		}
		
		return categorias;
	}
	
	public void agregarArticulo(Articulo articulo) throws SQLException
	{		
		connectionDB.statement.executeUpdate("INSERT INTO articulo(nombre,cantidad,precio,informacion,idMarca,idSubcategoria) values ('"+articulo.getNombre()+"', "+articulo.getCantidad()+","+articulo.getPrecio()+", '"+articulo.getInformacion()+"' ," +articulo.getIdMarca()+", "+articulo.getIdSubcategoria()+");");
	}
	
	public void activarSucategorias(){
		this.subcategoriat = true;
	}	
	
	public void desactivarSucategorias(){
		this.subcategoriat = false;
	}
	
	public ArrayList<Subcategoria> obtenerSubCategorias(int categoria) throws SQLException{
		ArrayList<Subcategoria> subcategorias = new ArrayList<Subcategoria>();
		ResultSet results=connectionDB.statement.executeQuery("SELECT id,nombre FROM subcategoria WHERE idcategoria="+categoria);
		while (results.next()){
			Subcategoria subcategoria=new Subcategoria();
			subcategoria.setId(results.getInt("id"));
			subcategoria.setNombre(results.getString("nombre"));
			
			subcategorias.add(subcategoria);
		}
		
		return subcategorias;
	}
	
	public ArrayList<Subcategoria> obtenerTodasSubCategorias() throws SQLException{
		ArrayList<Subcategoria> subcategorias = new ArrayList<Subcategoria>();
		ResultSet results=connectionDB.statement.executeQuery("SELECT id,nombre FROM subcategoria");
		while (results.next()){
			Subcategoria subcategoria=new Subcategoria();
			subcategoria.setId(results.getInt("id"));
			subcategoria.setNombre(results.getString("nombre"));
			
			subcategorias.add(subcategoria);
		}
		
		return subcategorias;
	}
	
	public ArrayList<Articulo> getArticulosPorSubcategoria(int subcategoria)
	{
		ArrayList<Articulo> articulos = new ArrayList<Articulo>();
		try
		{
			getArticulosPorSubcategoria = connection.prepareStatement("SELECT * FROM articulo WHERE idSubcategoria = ?");
			getArticulosPorSubcategoria.setInt(1, subcategoria);
			synchronized (getArticulosPorSubcategoria)
			{
				ResultSet results = getArticulosPorSubcategoria.executeQuery();
				while (results.next())
				{
					Articulo articulo = new Articulo();
					articulo.setId(results.getInt("id"));
					articulo.setNombre(results.getString("nombre"));
					articulo.setCantidad(results.getInt("cantidad"));
					articulo.setPrecio(results.getFloat("precio"));
					articulo.setInformacion(results.getString("informacion"));
					articulo.setIdMarca(results.getInt("idMarca"));
					articulo.setIdSubcategoria(results.getInt("idSubcategoria"));
					articulos.add(articulo);
				}
			}
			getArticulosPorSubcategoria.close();
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		return articulos;
	}
	
	public ArrayList<Articulo> getArticulosPorNombre(String nombre)
	{
		ArrayList<Articulo> articulos = new ArrayList<Articulo>();
		try
		{
			getArticulosPorNombre = connection.prepareStatement("SELECT * FROM articulo WHERE nombre LIKE ?");
			getArticulosPorNombre.setString(1, "%" + nombre + "%");
			synchronized (getArticulosPorNombre)
			{
				ResultSet results = getArticulosPorNombre.executeQuery();
				while (results.next())
				{
					Articulo articulo = new Articulo();
					articulo.setId(results.getInt("id"));
					articulo.setNombre(results.getString("nombre"));
					articulo.setCantidad(results.getInt("cantidad"));
					articulo.setPrecio(results.getFloat("precio"));
					articulo.setInformacion(results.getString("informacion"));
					articulo.setIdMarca(results.getInt("idMarca"));
					articulo.setIdSubcategoria(results.getInt("idSubcategoria"));
					articulos.add(articulo);
				}
			}
			getArticulosPorNombre.close();
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		return articulos;
	}
}
