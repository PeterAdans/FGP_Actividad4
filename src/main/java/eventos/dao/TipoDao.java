package eventos.dao;

import java.util.List;

import eventos.entidades.Tipo;

public interface TipoDao {
	
		List<Tipo>buscarTodos();
		int insertOne(Tipo tipo);
		int updateOne(Tipo tipo);
		Tipo findById(int idTipo);
		void eliminarTipo(int idTipo);

}
