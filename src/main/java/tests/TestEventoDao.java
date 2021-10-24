package tests;

import java.text.ParseException;
import java.util.Date;

import modelo.beans.Evento;
import modelo.beans.Tipo;
import modelo.daos.EventoListImpl;
import modelo.daos.IntEventoDao;
import modelo.daos.IntTipoDao;
import modelo.daos.TipoListImpl;

public class TestEventoDao {

	public static void main(String[] args) throws ParseException {
		
		IntEventoDao ievento = new EventoListImpl();
		IntTipoDao itipo = new TipoListImpl();
		
		System.out.println("Todos los eventos:");
		for (Evento evento: ievento.findAll())
			System.out.println(evento);
		System.out.println();
		
		// Añadir un evento a la lista
		Evento ev8 = new Evento(8, "Agatha", "Despedida chicas tarde y noche", new Date(), 7, "C/Conífera", "Activo", "", 42, 10, 78.3f, itipo.findById(4)) ;
		ievento.altaEvento(ev8);

		System.out.println("Todos los eventos tras añadir el evento 8");
		for (Evento evento: ievento.findAll())
			System.out.println(evento);
		System.out.println();
		
		// Cancelar el evento 2
		ievento.cancelarEvento(ievento.findById(2));
		
		System.out.println("Eventos tras cancelar el evento 2:");
		for (Evento evento: ievento.findAll())
			System.out.println(evento);
		System.out.println();
		

		System.out.println("Tipos de evento:");
		for (Tipo tipo: itipo.findAll())
			System.out.println(tipo);
		
	}

}
