package modelo.daos;

import java.util.ArrayList;
import java.util.List;

import modelo.beans.Tipo;

public class TipoListImpl implements IntTipoDao {
	
	private List<Tipo> listaTipos;
	
	public TipoListImpl() {
		listaTipos = new ArrayList<Tipo>();
		cargaListaTipos();
	}
	
	private void cargaListaTipos() {
		/**
		 * <pre>Este método añade una lista de tipos predefinida en 
		 * el atributo listaTipos. </pre>
		 */		
		
		listaTipos.add(new Tipo(1, "Boda", "Convite enlace matrimonial o civil"));
		listaTipos.add(new Tipo(2, "Bautizo", "Convite bautizo"));
		listaTipos.add(new Tipo(3, "Comunión", "Convite comunión"));
		listaTipos.add(new Tipo(4, "Despedida", "Fiesta de despedida"));
		listaTipos.add(new Tipo(5, "Cumpleaños", "Cumpleaños infantil"));
		listaTipos.add(new Tipo(6, "Concierto", "Concierto privado"));
		listaTipos.add(new Tipo(7, "Teatro", "Representación teatral"));
	}

	public Tipo findById(int idTipo) {
		/**
		 * Devuelve el elemento de la lista de Tipo 
		 * cuyo id pasamos por parámetro.
		 * 
		 * @param idTipo Es la id del Tipo
		 * 
		 * @return Tipo de la posición indicada. Si no existe en la lista, devuelve -1.
		 */
		Tipo aux = new Tipo();
		aux.setIdTipo(idTipo);
		int pos = listaTipos.indexOf(aux);
		if (pos == -1)
			return null;
		else
			return listaTipos.get(pos);
	}

	@Override
	public List<Tipo> findAll() {
		/**
		 * Devuelve la lista de los tipos que contiene el objeto Tipo
		 */
		return listaTipos;
	}
	

}
