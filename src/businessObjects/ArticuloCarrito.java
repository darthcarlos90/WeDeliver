package businessObjects;

public class ArticuloCarrito {
	private int sesionCarrito;
	private int articuloID;
	private int cantidad;
	public int getSesionCarrito() {
		return sesionCarrito;
	}
	public void setSesionCarrito(int sesionCarrito) {
		this.sesionCarrito = sesionCarrito;
	}
	public int getArticuloID() {
		return articuloID;
	}
	public void setArticuloID(int articuloID) {
		this.articuloID = articuloID;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
