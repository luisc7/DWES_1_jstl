package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.beans.Evento;
import modelo.daos.IntEventoDao;
import modelo.daos.IntTipoDao;
import modelo.daos.TipoListImpl;

/**
 * Servlet implementation class GestionEventos
 * @author Luis Cifuentes
 * @since 22 Octubre 2021
 */
@WebServlet(description = "Tareas de Evento", urlPatterns = { "/eventos" })
public class GestionEventos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionEventos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		//System.out.println("Test init");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * <pre>Contiene un switch case con el que dirige al método correspondiente
		 * a la acción que se va a realizar, que pueden ser:</pre>
		 * <pre>- Ver lista de activos (dirige a index.jsp)</pre>
		 * <pre>- Procesar el alta de un nuevo evento (viniendo del formulario form.jsp correspondiente)</pre>
		 * <pre>- Efectuar la operación de "Editar" de cada elemento de la tabla de eventos activos. </pre>
		 * <pre>- Efectuar la operación de "Eliminar" de cada elemento de la tabla de eventos activos. </pre>
		 * <pre>- Efectuar la operación de "Cancelar" de cada elemento de la tabla de eventos activos. </pre>
		 */
		String opcion = request.getParameter("opcion");
		System.out.println(opcion);
		switch (opcion) {
			case "activos":
				procActivos(request, response);
				break;
			case "alta":
				try {
					procAlta(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case "editar":
				procEditar(request, response);
				break;
			case "eliminar":
				procEliminar(request, response);
				break;
			case "cancelar":
				procCancelar(request, response);
				break;
		}
	}
	
	protected void procActivos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Dirige a index.jsp, ideado para mostrar los eventos activos
		 */
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	protected void procAlta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		/**
		 * Este método es llamado tras volver del envío del formulario.
		 * Recoge todos los parámetros introducidos en el formulario 
		 * y añade el evento del formulario al atributo que contiene 
		 * la lista del objeto "interEventos" recogido, que se envía 
		 * nuevamente como atributo de sesión.
		 * 
		 */


		/* Obtiene la sesión, y de ella el atributo "interEventos", que es 
		 * el objeto que contiene un atributo con la lista de eventos. */
		System.out.println("alta");
		HttpSession sesion = request.getSession();
		IntEventoDao ievent = (IntEventoDao)sesion.getAttribute("interEventos");	
				
		/* Crear evento a añadir (eventoAlta), para poder ir añadiendo 
		 * todos los atributos
		 */
		IntTipoDao itipo = new TipoListImpl();
		Evento eventoAlta = new Evento();
		eventoAlta.setEstado("Activo");
		
		/* Tratamiento fecha con SimpleDateFormat, configurar el formato */
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		/* Recogida de los parámetros del formulario en 'eventoAlta' */
		eventoAlta.setNombre(request.getParameter("nombre"));
		eventoAlta.setDescripcion(request.getParameter("descripcion"));
		//eventoAlta.setFechaInicio(request.getParameter("fecha"));
		eventoAlta.setFechaInicio(format.parse(request.getParameter("fecha")));
		eventoAlta.setDuracion(Integer.parseInt(request.getParameter("duracion")));
		eventoAlta.setDireccion(request.getParameter("direccion"));
		eventoAlta.setEstado("Activo");
		eventoAlta.setDestacado(request.getParameter("destacado"));
		eventoAlta.setAforoMaximo(Integer.parseInt(request.getParameter("max")));
		eventoAlta.setMinimoAsistencia(Integer.parseInt(request.getParameter("min")));
		eventoAlta.setPrecio(Float.parseFloat(request.getParameter("precio")));
		eventoAlta.setIdTipo(itipo.findById((Integer.parseInt(request.getParameter("tipo")))));
		eventoAlta.setIdEvento(ievent.getReferenciaNuevoEvento());	
		
		
		/* Actualizar la lista añadiendo eventoAlta a la lista del objeto */
		ievent.altaEvento(eventoAlta);
		
		/* Guardar en la sesion el objeto y volver a index.jsp */
		sesion.setAttribute("interEventos", ievent);				
		request.getRequestDispatcher("index.jsp").forward(request, response);	
	}
	
	protected void procEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Editar muestra todos los atributos del evento elegido en la tabla. 
		 * Para ello, selecciona el id del elemento a editar de la 
		 * lista, y envía el elemento en la petición.
		 * 
		 * Pasa el control al .jsp de editarevento.
		 */
		
		// Obtención del objeto desde la lista de eventos de la sesion
		HttpSession sesion = request.getSession();
		IntEventoDao ievent = (IntEventoDao)sesion.getAttribute("interEventos");
		
		// Obtiene Evento a partir de la id pasada en la petición
		int id = Integer.parseInt(request.getParameter("id"));
		Evento evento = ievent.findById(id);
				
		//Se envía el evento a editar (mostrar) con la petición 
		request.setAttribute("evento", evento);
		request.getRequestDispatcher("editarevento.jsp").forward(request, response);
	}
	
	protected void procEliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Elimina un evento de la lista de sesion con el id del evento,
		 * viniendo desde el enlace correspondiente de la tabla
		 */
		
		// Obtener la lista de la sesión
		HttpSession sesion = request.getSession();
		IntEventoDao ievent = (IntEventoDao)sesion.getAttribute("interEventos");
		
		// Obtener el elemento a eliminar de la lista desde la id
		// pasada en la petición, y borrarlo
		int id = Integer.parseInt(request.getParameter("id"));		
		ievent.eliminarEvento(ievent.findById(id));
		
		// Actualizar el objeto que contiene la lista en la sesión y
		// volver a index.jsp
		sesion.setAttribute("interEventos", ievent);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	protected void procCancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Cancela un evento de la lista de sesion con el id del evento.
		 * (Establece su atributo 'estado' a "Cancelado").
		 * Se diseña para acceder desde el enlace generado en la fila
		 * correspondiente al id del evento de la tabla de eventos.
		 */
		
		// Obtener la lista de la sesión
		HttpSession sesion = request.getSession();
		IntEventoDao ievent = (IntEventoDao)sesion.getAttribute("interEventos");
		
		// Obtener el elemento a cancelar de la lista desde la id
		// pasada en la petición, y cancelarlo
		int id = Integer.parseInt(request.getParameter("id"));		
		ievent.cancelarEvento(ievent.findById(id));
		
		// Actualizar el objeto que contiene la lista en la sesión y
		// volver a index.jsp
		sesion.setAttribute("interEventos", ievent);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
