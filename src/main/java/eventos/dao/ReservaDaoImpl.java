package eventos.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.entidades.Reserva;
import eventos.repository.ReservaRepository;
@Repository
public class ReservaDaoImpl implements ReservaDao {
	
	@Autowired
	private ReservaRepository rrepo;

	@Override
	public List<Reserva> buscarTodos() {
		// TODO Auto-generated method stub
		return rrepo.findAll();
	}

	@Override
	public int insertOne(Reserva reserva) {
		if (!rrepo.existsById(reserva.getIdReserva())) { //si no existe
            rrepo.save(reserva); // Insertamos el perfil
            return 1; 
        }
		return 0;
	}

	@Override
	public int updateOne(Reserva reserva) {
		if (rrepo.existsById(reserva.getIdReserva())) {
            rrepo.save(reserva); // Actualiza el perfil
            return 1; // Éxito
        }
        return 0; 
	}

	

	

	@Override
	public Reserva findById(int idReserva) {
		// TODO Auto-generated method stub
		return rrepo.findById(idReserva).orElse(null);
	}

	@Override
	public void eliminarReserva(int idReserva) {
		if (rrepo.existsById(idReserva)) {
            rrepo.deleteById(idReserva); // Elimina el perfil si existe
        }
	}
		

}
