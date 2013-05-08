<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="elementos/head.jsp" />
		<%@ page import="java.util.*, businessObjects.Articulo" %>
		<% ArrayList<Articulo> articulos = (ArrayList<Articulo>)session.getAttribute("listaArticulos"); %>
		<% String x=(String)session.getAttribute("borrar"); %>
		<style type="text/css">
			table, th, tr, td
			{
				border: 1px solid black;
			}
			th
			{
				font-weight: bold;
			}
			td
			{
				text-align: center;
			}
		</style>
	</head>
	<body>
		<jsp:include page="elementos/menu.jsp" />
		<div id="content">
			<!-- Aqui empieza el content -->
			<table>
				<tr>
					<th>ID del Articulo</th>
					<th>Nombre</th>
					<th>Cantidad disponible</th>
					<th>Precio</th>
					<th>Informacion</th>
					<th>Cantidad</th>
				</tr>
				<%
					Iterator articulosIterator = articulos.iterator();
					Articulo a;
					while (articulosIterator.hasNext())
					{
						a = (Articulo) articulosIterator.next();
				
					
					%>
					
					
						<form method="GET" action="carrito">
					
					<tr>
						<td><%= a.getId() %></td>
						<td><%= a.getNombre() %></td>
						<td><%= a.getCantidad() %></td>
						<td><%= a.getPrecio() %></td>
						<td><%= a.getInformacion() %></td>
						<input type="hidden" name="accion" value="agregar" />
						<input type="hidden" name="productoId" value="<%= a.getId() %>" />
						<td><input type="number" name="cantidad" min="0" max="<%= a.getCantidad() %>"/></td>
					
					
					
						<td><input type="submit" value="Agregar al carrito"/></td>
						
						
					</tr>
				</form>
				<%
					}
				%>
				
			</table>
			<!-- Aqui acaba el content -->
		</div><!-- content -->
		<jsp:include page="elementos/footer.jsp" />
	</body>
</html>