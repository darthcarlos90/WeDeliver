<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@page import="java.util.*"%>
<%@page import="dataAccess.*"%>
<%@page import="businessObjects.*"%>
<%@page import="java.sql.*"%>

<jsp:useBean id="miArtDAO" scope="request"
	class="dataAccess.articuloDAO" />
<jsp:useBean id="articulo" scope="page" class="businessObjects.Articulo"/>



<html>
<head>
<jsp:include page="elementos/head.jsp" />
<title>Altas Catalogo</title>

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
				}
				else
				{
					$.each(categorias, function(i, categorias)
					{
						$("#subcategoria").append("<option value=\"" + categorias.id + "\">" + categorias.nombre + "</option>");
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
	<div id="content"><!--  comienza content -->
	<jsp:setProperty name="articulo" property="nombre" param="nombre"/>
	<jsp:setProperty name="articulo" property="precio" param="precio"/>
	<jsp:setProperty name="articulo" property="cantidad" param="cantidad"/>
	<jsp:setProperty name="articulo" property="idMarca" param="marca"/>
	<jsp:setProperty name="articulo" property="idSubcategoria" param="subcategoria"/>
	<jsp:setProperty name="articulo" property="informacion" param="informacion"/>

	
	<%	if (articulo.getNombre()==null || articulo.getPrecio()<0 || articulo.getCantidad()<0 || articulo.getIdMarca()==0 || articulo.getInformacion()==null || articulo.getIdSubcategoria()==0 ) 
	{
	%>
	
				<h1>Sección de Altas de Catalogo</h1>
				
	<br />
	<form action="AltasCatalogo.jsp" id="datosArticulo" class="registro left" name="datosArticulo" method="post">
	
					<p>
					Nombre <input id="nombre" name="nombre" type="text" required="true"/>
					</p>
		<p>
			Marca: <select id="marca" name="marca">

				<option value="" select="selected">Elegir Marca</option>
				<!-- for de las marcas -->
				<%
				List listaSubcategorias=miArtDAO.obtenerTodasSubCategorias();
				
				
				List marcasList = miArtDAO.obtenerMarcas();
			Iterator marcasListIterator = marcasList.iterator();
			Marca marca;

			while (marcasListIterator.hasNext()) {
				marca = (Marca) marcasListIterator.next();%>

				<option value="<%=marca.getId()%>">
					<%=marca.getNombre()%>
				</option>

				<%}%>

			</select>
		</p>
		<p>
			Categoria: <select id="categoria" name="categoria"
				onChange="mostrarSubcategorias()">
				<option value="" select="selected">Elegir Categoria</option>
				<%List categoriasList = miArtDAO.obtenerCategorias();
			Iterator categoriasListIterator = categoriasList.iterator();
			Categoria categoria = null;

			while (categoriasListIterator.hasNext()) {
				categoria = (Categoria) categoriasListIterator.next();
				%>

				<option value="<%=categoria.getId()%>">
					<%=categoria.getNombre()%>
				</option>

				<%
				}
				%>
			</select>
		</p>

		<p>
			SubCategoria 
			<select id="subcategoria" name="subcategoria">
				<option value="-1">Elegir Subcategoria</option>
			</select>
		</p>
					<p>
						Cantidad <input id="cantidad" name="cantidad" type="number" min="0" required="true"/>
					</p>
					<p>
					Precio <input id="precio" name="precio" type="number" min="0" required="true"/>
					</p>
					<p>
					Informacion <input id="informacion" name="informacion" type="text" required="true"/>
					</p>
					<p>			
					<input type="submit" value="Submit" />
					</p>

	</form>


	<jsp:include page="elementos/footer.jsp" />
	<%
	}
	else{
		miArtDAO.agregarArticulo(articulo);
	
	%>
	<jsp:forward page="articuloAgregado.jsp"/>
	<%
	}
	%>
	</div>
	<jsp:include page="elementos/footer.jsp"/>
</body>
</html>