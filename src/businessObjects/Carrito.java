package businessObjects;

public class Carrito {
	private int sesionCarrito;
	private String email;
	private boolean terminado;
	
	public int getSesionCarrito() {
		return sesionCarrito;
	}
	public void setSesionCarrito(int sesionCarrito) {
		this.sesionCarrito = sesionCarrito;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isTerminado() {
		return terminado;
	}
	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}
}
