<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar Articulo 2</title>
</head>
<jsp:include page="../elementos/head.jsp" />
<body>
	<jsp:include page="../elementos/menu.jsp" />

	<jsp:useBean id="modart" scope="request"
		type="businessObjects.Articulo" />
	<div id="content">
		<h1>Modificar Articulo:</h1>

		<form method="POST" action="updateArticuloServlet">
			<table>
				<tr>
					<td>Id:</td>
					<td><input name="productoId" type="number" readonly="true"
						value="<%=modart.getId()%>" /></td>
				</tr>
				<tr>
					<td>Nombre</td>
					<td><input name="nombre" type="text" required="true"
						value="<%=modart.getNombre()%>" /></td>
				</tr>
				<tr>
					<td>Cantidad</td>
					<td><input name="cantidad" type="number" min="0"
						required="true" value="<%=modart.getCantidad()%>" /></td>
				</tr>
				<tr>
					<td>Precio</td>
					<td><input name="precio" type="number" min="0" required="true"
						value="<%=modart.getPrecio()%>" /></td>
				</tr>
				<tr>
					<td>Informacion</td>
					<td><input name="informacion" type="text" required="true"
						value="<%=modart.getInformacion()%>" /></td>
				</tr>
				<tr>
					<td>idMarca</td>
					<td><input name="idmarca" type="number" required="true"
						value="<%=modart.getIdMarca()%>" /></td>
				</tr>
				<tr>
					<td>idSubcategoria</td>
					<td><input name="idsubcategoria" required="true" type="number"
						value="<%=modart.getIdSubcategoria()%>" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Modificar" /></td>
				</tr>

			</table>
		</form>
	</div>
	<jsp:include page="../elementos/footer.jsp" />


</body>
</html>