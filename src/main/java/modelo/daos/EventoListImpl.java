package modelo.daos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.beans.Evento;

public class EventoListImpl implements IntEventoDao {
	
	private List<Evento> listEvent;
	private int referenciaNuevoEvento;
	
	public EventoListImpl() throws ParseException {
		listEvent = new ArrayList<Evento>();
		cargarEventos();
	}
	
	private void cargarEventos() throws ParseException {
		/**
		 * <pre>Este método carga una lista por defecto en listEvent.</pre>
		 */
		
		IntTipoDao itipo = new TipoListImpl();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//Date fecha = format.parse("2021-01-02");
		
		listEvent.add(new Evento(1, "Evelin y Jaime", "Enlace vespertino", new Date(), 8, "C/Olmo", "Activo", "", 115, 10, 65.7f, itipo.findById(1)) );
		listEvent.add(new Evento(2, "Jeremiah", "Bautizo almuerzo", format.parse("2021-01-02"), 3, "C/Abeto", "Activo", "s", 50, 12, 32.5f, itipo.findById(2)) );
		listEvent.add(new Evento(3, "Carlos Alberto", "Comunión almuerzo", new Date(), 3, "C/Pino", "Activo", "", 40, 10, 25.35f, itipo.findById(3)) );
		listEvent.add(new Evento(4, "Vittorio Casanova", "Despedida madrugada", new Date(), 5, "C/Haya", "Activo", "s", 30, 8, 46.75f, itipo.findById(4)) );
		listEvent.add(new Evento(5, "Florimond Añejado", "Cumpleaños senior", new Date(), 3, "C/Alamo", "Cancelado", "", 75, 20, 25.5f, itipo.findById(5)) );
		listEvent.add(new Evento(6, "La Tuna viejuna", "Concierto fin de semana noche", new Date(), 3, "C/Abeto", "Activo", "s", 50, 12, 32.5f, itipo.findById(6)) );
		listEvent.add(new Evento(7, "Fortunata y Jacinta", "Teatro de media tarde", new Date(), 3, "C/Ciprés", "Cancelado", "s", 250, 50, 42f, itipo.findById(7)) );
		referenciaNuevoEvento = 8;		
	}

	
	@Override
	public Evento findById(int idEvento) {
		/**
		 * <pre>Encuentra el evento con un determinado id 
		 * en la lista en memoria.</pre>
		 * @param idEvento es el id del Evento a buscar en listEvent.
		 * 
		 * @return El Evento insertado en listEvent. Si falla, devuelve null.
		 */
		Evento aux = new Evento();
		aux.setIdEvento(idEvento);
		int pos = listEvent.indexOf(aux);
		if (pos == -1)
			return null;
		else
			return listEvent.get(pos);
	}

	@Override
	public List<Evento> findAll() {
		/**
		 * <pre>Devuelve la lista de todos los "Evento".</pre>
		 * 
		 * @return listEvent
		 */
		return getListEvent();
	}

	@Override
	public List<Evento> findActive() {
		/**
		 * <pre>Devuelve solo los eventos activos.</pre>
		 * 
		 * @return List<Evento> conteniendo solo aquellos Evento 
		 * cuyo atributo estado es "Activo".
		 */
		List<Evento> listActivos = new ArrayList<Evento>();
		for (Evento event: listEvent) {
			if (event.getEstado()=="Activo")
				listActivos.add(event);
		}
		return listActivos;
	}

	@Override
	public int altaEvento(Evento evento) {
		/**
		 * <pre> Añade un evento a la lista.</pre>
		 * 
		 * @param evento Es el evento a añadir.
		 * 
		 * @return Devuelve 1 si se puede añadir, devuelve 0 si 
		 * el evento ya estaba en la lista o si no se puede añadir.
		 */
		if (listEvent.contains(evento))
			return 0;
		else {
			referenciaNuevoEvento++;			
			return listEvent.add(evento)? 1 : 0;
		}
	}

	@Override
	public int editarEvento(Evento evento) {
		/**
		 * <pre>Modifica un evento de la lista. Considera 
		 * un evento el mismo si tiene en mismo id.</pre>
		 * 
		 * @param evento Es el evento a editar.
		 * 
		 * @return Devuelve 1 si se ha podido modificar, devuelve 0 si 
		 * el evento no estaba en la lista o si no se puede añadir.
		 */
		int pos = listEvent.indexOf(evento);
		if (pos == -1)
			return 0;
		else
			return (listEvent.set(pos, evento) != null)? 1 : 0;
	}

	@Override
	public int eliminarEvento(Evento evento) {
		/**
		 * <pre> Elimina un evento de la lista. Ti</pre>
		 * 
		 * @param evento Es el evento a eliminar.
		 * 
		 * @return Devuelve 1 si se puede añadir, devuelve 0 si 
		 * el evento no estaba en la lista o si no se puede eliminar.
		 */
		return listEvent.remove(evento)? 1 : 0;
	}

	@Override
	public int cancelarEvento(Evento evento) {
		/**
		 * <pre> Cancela un evento de la lista.</pre>
		 * 
		 * @param evento Es el evento a cancelar.
		 * 
		 * @return Devuelve 1 si se puede establecer su atributo 'estado' 
		 * con el String "Cancelado", devuelve 0 si a pesar de intentar 
		 * cambiar a cancelado, no es posible.
		 * 
		 * 
		 */
		evento.setEstado("Cancelado");
		return (evento.getEstado() == "Cancelado")? 1 : 0;
	}
	
	@Override
	public int getReferenciaNuevoEvento() {
		return referenciaNuevoEvento;
	}

	@Override
	public void setReferenciaNuevoEvento(int referenciaNuevoEvento) {
		this.referenciaNuevoEvento = referenciaNuevoEvento;
	}

	public List<Evento> getListEvent() {
		return listEvent;
	}

	public void setListEvent(List<Evento> listEvent) {
		this.listEvent = listEvent;
	}
	

}
