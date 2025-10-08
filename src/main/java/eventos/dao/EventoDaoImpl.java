package eventos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import eventos.entidades.Evento;
import eventos.repository.EventoRepository;

@Repository
public class EventoDaoImpl implements EventoDao{
	@Autowired
	private EventoRepository erepo;

	@Override
	public List<Evento> buscarPorAceptadoYDestacado() {
		// TODO Auto-generated method stub
		return erepo.findByAceptadoAndDestacado();
		
	}

	@Override
	public List<Evento> buscarPorTipo(int idTipo) {
		// TODO Auto-generated method stub
		return erepo.findByTipoIdTipo(idTipo);
	}


	@Override
	public List<Evento> buscarPorestado(String estado) {
		// TODO Auto-generated method stub
		return erepo.findByEstado(estado);
	}

	@Override
	public int insertOne(Evento evento) {
		try {
	        erepo.save(evento);
	        return 1;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0;
	    }
	}

	@Override
	public Evento findById(int idEvento) {
		// TODO Auto-generated method stub
		return erepo.findById(idEvento).orElse(null);
	}

	@Override
	public int updateOne(Evento evento) {
		try {
			if (erepo.existsById(evento.getIdEvento())) {
		        erepo.save(evento);
		        return 1;
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    return 0;
	}

	@Override
	public int cancelarEvento(int idEvento) {
		 Evento evento = erepo.findById(idEvento).orElse(null);
		    if (evento != null) {
		        evento.setEstado("CANCELADO");  // O si usas Enum: evento.setEstado(EstadoEvento.CANCELADO);
		        updateOne(evento);
		        return 1;  
		    }
		    return 0;  
	}

	

	@Override
	public List<Evento> buscarTodos() {
		
		return erepo.findAll();
	}

	
	
	
	
}
