<form class="registro" name="registro" onsubmit="return validar()"
	action="addUserServlet" method="POST">
	<table>
		<tr>
			<td>Nombre:</td>
			<td><input type="text" name="nombre" id="nombre" required /></td>
		</tr>
		<tr>
			<td>Apellido:</td>
			<td><input type="text" name="apellido" id="apellido" required /></td>
		</tr>
		<tr>
			<td>Correo:</td>
			<td><input type="email" name="email"
				placeholder="tumail@dominio.com" required /></td>
		</tr>
		<tr>
			<td id="contra">Password:</td>
			<td><input type="password" name="password" required
				id="password" /></td>
		</tr>
		<tr>
			<td id="contra2">Confirmar Password:</td>
			<td><input type="password" name="cPassword" required
				id="cPassword" /></td>
		</tr>
		<tr>
			<td>Celular:</td>
			<td><input type="tel" name="celular" required /></td>
		</tr>
		<tr>
			<td>Matricula:</td>
			<td><input type="text" name="matricula"
				placeholder="Solo numeros" id="matricula" /></td>
		</tr>
		<tr>
			<td>Carrera:</td>
			<td><input type="text" name="carrera" id="carrera" maxLength= "3"/></td>
		</tr>
		
		<tr>
			<td><input type="submit" value="Confirmar" name="submit"></td>
			<td><input type="reset" value="Cancelar" name="cancelar"></td>
		</tr>
	</table>
</form>