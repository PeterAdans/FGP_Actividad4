package eventos.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.entidades.Perfil;
import eventos.repository.PerfilRepository;
@Repository
public class PerfilDaoImpl implements PerfilDao{
	
	@Autowired
	private PerfilRepository prepo;

	@Override
	public Perfil buscarUno(int idPerfil) {
		// TODO Auto-generated method stub
		return prepo.findById(idPerfil).orElse(null);
	}

}
