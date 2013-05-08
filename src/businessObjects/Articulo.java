package businessObjects;

public class Articulo
{
	private int id;
	private String nombre;
	private int cantidad;
	private float precio;
	private String informacion;
	private int idMarca;
	private int idSubcategoria;
	
	public Articulo(){
		id=0;
		nombre=null;
		cantidad=0;
		precio=0;
		informacion=null;
		idMarca=0;
		idSubcategoria=0;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getInformacion() {
		return informacion;
	}
	public void setInformacion(String informacion) {
		this.informacion = informacion;
	}
	public int getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}
	public int getIdSubcategoria() {
		return idSubcategoria;
	}
	public void setIdSubcategoria(int idSubcategoria) {
		this.idSubcategoria = idSubcategoria;
	}
}
