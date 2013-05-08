<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="businessObjects.Users"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gracias por Registrarte</title>
<jsp:include page="elementos/head.jsp" />
</head>
<%
	// traer el nombre del usuario que se registro para mostrarlo en pantalla
	Users usuarioRegistrado = (Users) session
			.getAttribute("usuarioRegistrado");
%>
<body>
	<div id="content">
		<jsp:include page="elementos/menu.jsp" />
		<h1>Gracias por registrate ${usuarioRegistrado.nombre}</h1>
		<h3>
			Para regresar a la página principal da click <a href="index.jsp">aquí</a>,
			para ir a tu página de administración da click en esta <a
				href="principalUsuario.jsp">parte</a>.
		</h3>
		<jsp:include page="elementos/footer.jsp" />
	</div>
</body>
</html>