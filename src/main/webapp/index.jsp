<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" media="screen" href="css/style.css"/>
<title>Eventos</title>
</head>
<body>

	<nav>
		<ul>
			<li><a>Tipos</a></li>
			<li><a>Eventos</a></li>
			<li><a>Usuarios</a></li>
			<li class="active"><a>Eventos/tipo</a></li>
			<li><a>Login</a></li>
			<li><a>Registro</a></li>
			<li><a>Salir</a></li>
		</ul>
	</nav>
	<h1>Listado de Eventos Activos</h1>
	<div class="boton"><a href=form.jsp>Nuevo evento</a></div>
	
	<table>
		<tr>
			<th class="col1">Id</th>
			<th class="col2">Nombre</th>
			<th class="col3">Precio</th>
			
			<!-- Columna hueca -->
			<th class="col4 empty-col" id="clear-col"></th>
			
			<th class="col5-6-7" colspan="3">Opciones</th>
		</tr>
		<c:forEach var="eleEvento" items="${sessionScope.interEventos.listEvent }">
			<c:if test = "${eleEvento.estado == 'Activo'}">
				<tr>
					<td class="col1 filled-col">${eleEvento.idEvento}</td>
					<td class="col2 filled-col">${eleEvento.nombre}</td>
					<td class="col3 filled-col">${eleEvento.precio}</td>
					<td class="col4 empty-col"></td>
					<td class="col5 filled-col"><a href="eventos?opcion=editar&id=${eleEvento.idEvento}">Editar</a></td>
					<td class="col6 filled-col"><a href="eventos?opcion=eliminar&id=${eleEvento.idEvento}">Eliminar</a></td>
					<td class="col7 filled-col"><a href="eventos?opcion=cancelar&id=${eleEvento.idEvento}">Cancelar</a></td>
				</tr>
			</c:if>
		</c:forEach>
	</table>

</body>
</html>