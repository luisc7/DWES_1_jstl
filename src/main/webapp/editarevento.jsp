<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" media="screen" href="css/style.css"/>
<title>Insert title here</title>
</head>
<body>
	
	<h1>Editar evento</h1>
	<p class="boton"><a href="eventos?opcion=activos">Volver a lista de eventos activos</a></p>
	<table class="modif">
		<tr>
			<th>Identificador</th>
			<th>Tipo de evento</th>
			<th>Nombre</th>
			<th>Descripción</th>
			<th>Inicio</th>
			<th>Duración (h)</th>
			<th>Dirección</th>
			<th>Estado</th>
			<th>¿Destacado?</th>
			<th>Máximo asistentes</th>
			<th>Mínimo asistentes</th>
			<th>Precio (Euros)</th>
		</tr>
		<c:forEach var="eleEvento" items="${sessionScope.interEventos.listEvent }">
			<c:if test = "${eleEvento.idEvento == evento.idEvento}">
				<tr>
					<td>${eleEvento.idEvento }</td>
					<td>${eleEvento.idTipo.nombre }</td>
					<td>${eleEvento.nombre }</td>
					<td>${eleEvento.descripcion }</td>
					<td>${eleEvento.fechaInicio }</td>
					<td>${eleEvento.duracion }</td>
					<td>${eleEvento.direccion }</td>
					<td>${eleEvento.estado }</td>
					<td>${eleEvento.destacado }</td>
					<td>${eleEvento.aforoMaximo }</td>
					<td>${eleEvento.minimoAsistencia }</td>
					<td>${eleEvento.precio }</td>	
				</tr>		
			</c:if>
		</c:forEach>
	</table>
</body>
</html>