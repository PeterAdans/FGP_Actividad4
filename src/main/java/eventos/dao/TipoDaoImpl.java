package eventos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.entidades.Tipo;
import eventos.repository.TipoRepository;
@Repository
public class TipoDaoImpl implements TipoDao {
	
	@Autowired
	private TipoRepository trepo;

	@Override
	public List<Tipo> buscarTodos() {
		// TODO Auto-generated method stub
		return trepo.findAll();
	}

	@Override
	public int insertOne(Tipo tipo) {
		 if (!trepo.existsById(tipo.getIdTipo())) {
	            trepo.save(tipo);
	            return 1; // Éxito
	        }
	        return 0; // El tipo ya existe
	}

	@Override
	public int updateOne(Tipo tipo) {
		if (trepo.existsById(tipo.getIdTipo())) {
            trepo.save(tipo);
            return 1; // Éxito
        }
        return 0; // El tipo no existe;
	}

	
	@Override
	public Tipo findById(int idTipo) {
		// TODO Auto-generated method stub
		return trepo.findById(idTipo).orElse(null);
	}

	@Override
	public void eliminarTipo(int idTipo) {
		if (trepo.existsById(idTipo)) {
            trepo.deleteById(idTipo);
	}
	}

	

}
