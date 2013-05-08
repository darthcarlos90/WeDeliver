
<%
	// Agarra el email y el tipo de sesión para saber que a que página mandar al dar click en el email.
	String email = (String) session.getAttribute("user");
	String tipoSesion = (String) session.getAttribute("tipoSesion");
%>
<form id="logout" method="POST" action="LogOutServlet">
	<label for="email"><a
		href="<%if (tipoSesion.equals("Consumer")) {%>
		principalUsuario.jsp
		<%} else {%> 
		inicioAdministrador.jsp
		<%}%>">
			<%=email%></a></label><input type="submit" value="Logout">
</form>