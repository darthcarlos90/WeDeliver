<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="elementos/head.jsp" />
		<script type="text/javascript" src="validacion.js"></script>
		<%@ page import="java.util.*, businessObjects.Categoria" %>
		<%
		
			ArrayList<Categoria> categorias = (ArrayList<Categoria>)session.getAttribute("categorias"); 
			
		%>
		<style>
			.right
			{
				text-align: right;
				padding-right: 1em;
			}
		</style>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#categoria").change(function(){
					var idDeCategoria = $(this).val();
					$("#subcategoria").html("");
					if(idDeCategoria == -1)
					{
						$("#subcategoria").append("<option value=\"-1\">Selecciona la categoria primero</option>");
					}
					$.ajax({
						url: "getSubcategorias",
						contentType: "application/json; charset=utf-8",
						dataType: "json",
						data: "idDeCategoria=" + idDeCategoria,
						success: function(categorias){
							if (jQuery.isEmptyObject(categorias))
							{
								$("#subcategoria").append("<option value=\"0\">No hay subcategorias</option>");
								$("#buscarPorCategoria").show("slow");
							}
							else
							{
								$.each(categorias, function(i, categorias)
								{
									$("#subcategoria").append("<option value=\"" + categorias.id + "\">" + categorias.nombre + "</option>");
									$("#buscarPorCategoria").show("slow");
								});
							}
						}
					});
				});
			});
		</script>
	</head>
	<body>
		<jsp:include page="elementos/menu.jsp" />
		<div id="content">
			<!-- Aqui empieza el content -->
						<h1>Sección de Cambios de Catalogo</h1>
						<br>
				<div id="barraBusqueda" class="center">
					<form method="GET" action="SearchArticuloServlet" class="center">
						<label for="nombre">Buscar por nombre:</label>
						<input type="text" name="nombre" autocomplete="off" required="required"/>
						<input type="hidden" name="tipoDeBusqueda" value="porNombre" />
						<input type="hidden" name="cambiar" value="cambiar"/>
						<input type="submit" value="Buscar">
					</form>
					<br />
					<table>
						<form method="GET" action="SearchArticuloServlet">
							<tr>
								<td><label for="categoria">Buscar por categoria:</label></td>
								<td>
									<select name="categoria" id="categoria">
										<option value="" selected="selected">Selecciona la categoria</option>
									<%
										for (int i = 0; i < categorias.size(); i++)
										{
											Categoria categoria = categorias.get(i);
									%>
										<option value="<%= categoria.getId() %>"><%= categoria.getNombre() %></option>
									<%
										}
									%>
									</select>
								</td>
							</tr>
							<tr>
								<td><label for="subcategoria">Selecciona subcategoria:</label></td>
								<td>
									<select name="subcategoria" id="subcategoria">
										<option value="">Selecciona la categoria primero</option>
									</select>
								</td>
							</tr>
							<tr>
								<td></td>
								<td><input type="submit" value="Buscar" id="buscarPorCategoria" style="display:none;"/></td>
							</tr>
							<input type="hidden" name="tipoDeBusqueda" value="porCategoria" />
							<input type="hidden" name="cambiar" value="cambiar"/>
						</form>
					</table>
				</div><!-- barraBusqueda -->
			<!-- Aqui acaba el content -->
		</div><!-- content -->
		<jsp:include page="elementos/footer.jsp" />
	</body>
</html>