<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="businessObjects.Articulo"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resultados</title>
<jsp:include page="elementos/head.jsp"/>
</head>
<% ArrayList<Articulo> articulos = (ArrayList<Articulo>)session.getAttribute("listaArticulos"); %>
<body>
<jsp:include page="elementos/menu.jsp"/>
<div id="content">
	<h2>Los resultados de la búsqueda se muestran a continuación:</h2>
	<table border = "2">
		<tr>
			<th>Identificador</th>
			<th>Nombre Artículo</th>
			<th>Marca Artículo</th>
			<th>Cantidad Disponible</th>
			<th>Precio</th>
		</tr>
		<%
		for(int i = 0; i<articulos.size(); i++){ 
			Articulo articulo = articulos.get(i);
			%>
		<tr>
			<td><%=articulo.getId() %></td>
			<td><%=articulo.getNombre() %></td>
			<td><%=articulo.getCantidad() %></td>
			<td><%=articulo.getPrecio() %></td>
		</tr>
		<%} %>
	</table>
	</div>
</body>
</html>