<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="elementos/head.jsp" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="elementos/menu.jsp" />
	<%
		try {
			String sesion = (String) session.getAttribute("sesion");
			if (sesion.equals("Activa")) {
	%>
	<div id="content">
		<h1>Administrador</h1>
		<br>
		<div class="fancyBox">
			<br />
			<table class="tablaObsoleta">
				<tr>
					<td><a href="AltasCatalogo.jsp">Altas</a></td>
					<td><a href="bajasCatalogo.jsp">Bajas</a></td>
				</tr>
				<tr>
					<td><a href="CambiosCatalogo.jsp">Cambios</a></td>
					<td><a href="PedidoServlet">Pedidos Pendientes</a></td>

				</tr>
			</table>
		</div>
	</div>
	<%
		} else {
	%>
	<jsp:include page="errorAdministrador.jsp"></jsp:include>
	<%
		}
		} catch (Exception e) {
	%>
	<jsp:include page="errorAdministrador.jsp"></jsp:include>
	<%
		}
	%>
	<jsp:include page="elementos/footer.jsp" />
</body>
</html>