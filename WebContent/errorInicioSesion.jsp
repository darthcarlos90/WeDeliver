<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error al inicio de sesión</title>
</head>
<jsp:include page="elementos/head.jsp" />
<body>
	<div id="content">
		<jsp:include page="elementos/menu.jsp" />
		<!-- Aqui empieza el content -->
		<h2>Ha ocurrido un error al inicio de sesión, favor de reingresar
			los datos.</h2>
		<form id="login" method="POST" action="LogInServlet">
			<table>
				<tr>
					<td><label for="email">Email:</label></td>
					<td><input type="text" name="email"></td>
				</tr>
				<tr>
					<td><label for="password">Password:</label></td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Entrar"></td>
					<td>Haz olvidado tu contraseña?</td>
				</tr>
			</table>
		</form>
		<!-- Aqui acaba el content -->
		<jsp:include page="elementos/footer.jsp" />
	</div>

</body>
</html>