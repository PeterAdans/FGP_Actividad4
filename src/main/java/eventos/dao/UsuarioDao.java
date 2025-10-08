package eventos.dao;

import java.util.List;

import eventos.entidades.Usuario;

public interface UsuarioDao {
	
	Usuario buscarPorUsername(String username);
	boolean altaUsuario(Usuario usuario);
	List<Usuario> buscarTodos();
	

}
