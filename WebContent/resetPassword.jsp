<!DOCTYPE html>
<html>
<head>
<jsp:include page="elementos/head.jsp" />
<script type="text/javascript" src="validacion.js"></script>
</head>

<body>
	
	<div id="content">
	<jsp:include page="elementos/menu.jsp" />
		<!-- Aqui empieza el content -->
		<form class="registro" id="newPassword" method="post"
			action="ChangePasswordServlet" onsubmit="return validar()">
			<table>
				<tr>
					<td>Email:</td>
					<td><input type="email" name="email" id="email" required /></td>
				</tr>
				<tr>
					<td id="contra">Nueva Contraseña:</td>
					<td><input type="password" name="password" id="password" /></td>
				</tr>
				<tr>
					<td id="contra2">Confirmar Nueva Contraseña:</td>
					<td><input type="password" name="cPassword" id="cPassword" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Aceptar" id="submitNewPass" /></td>
				</tr>
			</table>
		</form>
		<!-- Aqui acaba el content -->

	</div>
	<!-- content -->
	<jsp:include page="elementos/footer.jsp" />
</body>
</html>