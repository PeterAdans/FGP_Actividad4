package eventos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import eventos.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	Usuario findByUsername(String username);

}
