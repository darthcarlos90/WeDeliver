<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="businessObjects.*"%>
<jsp:useBean id="pedidoDAO" scope="request" class="dataAccess.PedidoDAO" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<title></title>
</head>
<jsp:include page="elementos/head.jsp" />
<body>
	<jsp:include page="elementos/menu.jsp" />
	<div id="content">
		<table class="tablaObsoleta center">
			<thead>
				<tr>
					<th>Id Articulo</th>
					<th>Id Pedido</th>
					<th>Cantidad</th>
					<th>Nombre</th>
					<th>Informacion</th>
					<th>Total</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Resultado> resultsList=(List<Resultado>)session.getAttribute("results");
				Iterator<Resultado> resultsListIterator=resultsList.iterator();
				Resultado res;
				while (resultsListIterator.hasNext()) {
					res=(Resultado)resultsListIterator.next();
			%>
				<tr>
					<td><%=res.getIdArticulo()%></td>
					<td><%=res.getIdPedido() %></td>
					<td><%=res.getCantidad() %></td>
					<td><%=res.getNombre() %></td>
					<td><%=res.getInformacion() %></td>
					<td><%=res.getPrecio()*res.getCantidad() %></td>
				</tr>

				<%
				}
			%>
			
		</table>
	</div>
	<jsp:include page="elementos/footer.jsp" />
</body>
</html>