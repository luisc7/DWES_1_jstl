package modelo.daos;

import java.util.List;

import modelo.beans.Tipo;

public interface IntTipoDao {
	
	Tipo findById(int idTipo);
	List<Tipo> findAll();

}
