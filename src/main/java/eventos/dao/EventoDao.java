package eventos.dao;

import java.util.List;

import eventos.entidades.Evento;

public interface EventoDao {
	
	List<Evento> buscarPorAceptadoYDestacado();
	List<Evento> buscarPorTipo(int idTipo);
	List<Evento> buscarPorestado(String estado);
	int insertOne(Evento evento);
	Evento findById(int idEvento);
	int updateOne(Evento evento);
	int cancelarEvento(int idEvento);
	List<Evento> buscarTodos();
	
	

}
