package eventos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eventos.entidades.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
	
	List<Reserva> findByUsuarioUsername(String username);
	List<Reserva> findByEventoIdEvento(int idEvento);

}
