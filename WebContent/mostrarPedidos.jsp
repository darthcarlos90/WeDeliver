<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="businessObjects.Pedido"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	pedidos = (ArrayList<Pedido>)session.getAttribute("pedidos");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pedidos Pendientes</title>
</head>
<jsp:include page="elementos/head.jsp" />
<body>
	<jsp:include page="elementos/menu.jsp" />
	<div id="content">
		<h1>Estos son los pedidos que tienes pendientes:</h1>
		<table class="tablaObsoleta center">
			<tr>
				<th>Id Pedido</th>
				<th>Email de quien hizo el pedido</th>
				<th>Precio total pedido</th>
				<th>Fecha en que se realizó el pedido</th>
			</tr>
			<%for (int i = 0; i<pedidos.size(); i++){ 
				Pedido pedido = pedidos.get(i);
			%>

			<tr>
				<form method="post" action="mostrarPedidoIndividualServlet">
					<input type="hidden" name="pedidoId" value="<%= pedido.getId() %>" />
				<td><input type="submit" value="<%=pedido.getId() %>" /></td>
				</form>
				<td><%=pedido.getEmail() %></td>
				<td><%=pedido.getTotal() %></td>
				<td><%=pedido.getFecha() %></td>
				<form method="post" action="PedidoServlet">
					<input type="hidden" name="pedidoId" value="<%= pedido.getId() %>" />
					<td><input type="submit" value="Pedido terminado" /></td>
				</form>
			</tr>

			<%} %>
		</table>
	</div>
	<jsp:include page="elementos/footer.jsp" />
</body>
</html>