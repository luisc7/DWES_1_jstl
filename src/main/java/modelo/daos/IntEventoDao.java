package modelo.daos;

import java.util.List;

import modelo.beans.Evento;

public interface IntEventoDao {
	
	Evento findById(int idEvento);
	List<Evento> findAll();
	List<Evento> findActive();
	int altaEvento(Evento evento);
	int editarEvento(Evento evento);
	int eliminarEvento(Evento evento);
	int cancelarEvento(Evento evento);
	
	int getReferenciaNuevoEvento();
	void setReferenciaNuevoEvento(int referenciaNuevoEvento);
}
