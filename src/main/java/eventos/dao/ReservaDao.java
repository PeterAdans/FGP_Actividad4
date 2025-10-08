package eventos.dao;

import java.util.List;

import eventos.entidades.Reserva;

public interface ReservaDao {
	List<Reserva>buscarTodos();
	int insertOne(Reserva Reserva);
	int updateOne(Reserva Reserva);
	Reserva findById(int idReserva);
	void eliminarReserva(int idReserva);

}
