<!DOCTYPE html>
<%@page import="businessObjects.Articulo"%>
<%@page import="dataAccess.articuloDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="businessObjects.ArticuloCarrito"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<jsp:include page="elementos/head.jsp" />
<script type="text/javascript" src="validacion.js"></script>
<script type="text/javascript">
			$(document).ready(function(){
				
			});
		</script>
<%
	ArrayList<ArticuloCarrito> artCars = (ArrayList<ArticuloCarrito>)session.getAttribute("carrito");
%>
<style type="text/css">
	td
	{
		text-align: center;
	}
</style>
</head>
<body>
	<jsp:include page="elementos/menu.jsp" />
	<div id="content">
		<%
			articuloDAO aDAO = new articuloDAO();
				Iterator iterator = artCars.iterator();
				ArticuloCarrito artCar;
				if (artCars.isEmpty())
				{
		%>
		<H1>No hay productos en el carrito.</H1>
		<%
			}
				else
				{
		%>
		<table>
			<tr>
				<th>Articulo</th>
				<th>Precio unitario</th>
				<th>Cantidad</th>
				<th>Subtotal</th>
			</tr>
		<%
					float total = 0;
					while(iterator.hasNext())
					{
						artCar = (ArticuloCarrito)iterator.next();
						Articulo art = aDAO.getArticulo(artCar.getArticuloID());
						float subtotal = art.getPrecio() * artCar.getCantidad();
						total += subtotal;
		%>
		<form method="POST">
			<tr>
				<td><%=art.getNombre()%></td>
				<td><%=art.getPrecio()%></td>
				<td><input type="number" min="1" max="<%=art.getCantidad()%>"
					value="<%=artCar.getCantidad()%>" id="cantidad<%=art.getId()%>" />
				</td>
				<td><%= subtotal %></td>
				<td><input type="button" value="Actualizar"
					onclick="actualizar<%=art.getId()%>()" /></td>
				<td><input type="button" value="Eliminar"
					onclick="eliminar<%=art.getId()%>()" /></td>
				<script type="text/javascript">
						function actualizar<%=art.getId()%>()
						{
							var cantidad = $("#cantidad<%=art.getId()%>").val();
							var accion = "actualiza";
							var productoID = "<%=art.getId()%>";
							$.ajax({
								type: "GET",
								url: "carrito",
								data: {"accion": accion, "productoId": productoID, "cantidad": cantidad},
								success: function()
								{
									location.href = "carrito?accion=verCarrito";
								}
							});
						};
						
						function eliminar<%=art.getId()%>()
						{
							var accion = "elimina";
							var productoID = "<%=art.getId()%>";
							$.ajax({
								type: "GET",
								url: "carrito",
								data: {"accion": accion, "productoId": productoID},
								success: function()
								{
									location.href = "carrito?accion=verCarrito";
								}
							});
						};
					</script>
			</tr>
		</form>
		<%
				}
		%>
				<tr><td><br/></td></tr>
				<tr>
					<td></td>
					<td></td>
					<td style="text-align: right; padding-right: 5px;">Total:</td>
					<td><%= total %></td>
					<td><input type="button" value="Hacer pedido" onclick="hacerPedido()"/></td>
					<script type="text/javascript">
						function hacerPedido()
						{
							var total = "<%= total %>";
							$.ajax({
								type: "POST",
								url: "hacerPedido",
								data: {"total": total},
								success: function(){
									alert("Pedido relizado correctamente! Espera un correo electronico con mas detalles.");
									location.href = "principalUsuario.jsp";
								}
							});
						}
					</script>
					<td></td>
				</tr>
		<%
			}
		%>
		</table>
	</div>
	<!-- content -->
	<jsp:include page="elementos/footer.jsp" />
</body>
</html>