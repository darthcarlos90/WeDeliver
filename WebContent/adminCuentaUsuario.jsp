<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="businessObjects.Users"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="elementos/head.jsp" />
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrador de la cuenta de usuario</title>
</head>
<script type="text/javascript" src="validacion.js"></script>
<body>
	<jsp:include page="elementos/menu.jsp" />
	<div id="content">
		<!--  contend div -->

		<form class="registro" name="updateUser" onsubmit="return validar()"
			action="UpdateUserServlet" method="POST">
			<%
				Users usuarioActivo = (Users) session.getAttribute("activeUser");
			%>
			<table>
				<tr>
					<td>Nombre:</td>
					<td><input type="text" name="nombre" id="nombre" required
						value="<%=usuarioActivo.getNombre()%>" /></td>
				</tr>
				<tr>
					<td>Apellido:</td>
					<td><input type="text" name="apellido" id="apellido" required
						value="<%=usuarioActivo.getApellido()%>" /></td>
				</tr>
				<tr>
					<td id="contra">Password:</td>
					<td><input type="password" name="password" required
						value="<%=usuarioActivo.getPassword()%>" id="password" /></td>
				</tr>
				<tr>
					<td id="contra2">Confirmar Password:</td>
					<td><input type="password" name="cPassword" required
						value="<%=usuarioActivo.getPassword()%>" id="cPassword" /></td>
				</tr>
				<tr>
					<td>Celular:</td>
					<td><input type="tel" name="celular" required
						value="<%=usuarioActivo.getTelefono()%>" /></td>
				</tr>
				<tr>
					<td>Matricula:</td>
					<td><input type="text" name="matricula" id="matricula"
						value="<%=usuarioActivo.getMatricula()%>" /></td>
				</tr>
				<tr>
					<td>Carrera:</td>
					<td><input type="text" name="carrera" id="carrera"
						value="<%=usuarioActivo.getCarrera()%>" maxLength="3" /></td>
				</tr>

				<tr>
					<td><input type="submit" value="Confirmar" name="submit"></td>
					<td><input type="reset" value="Cancelar"
						OnClick="history.go( -1 );return true;"></td>
				</tr>
			</table>
		</form>
	</div>

	<!--  end of content -->
	<jsp:include page="elementos/footer.jsp" />
</body>
</html>