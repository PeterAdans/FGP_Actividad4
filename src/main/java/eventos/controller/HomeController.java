package eventos.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.dao.EventoDao;
import eventos.dao.PerfilDao;
import eventos.dao.TipoDao;
import eventos.dao.UsuarioDao;
import eventos.entidades.Evento;
import eventos.entidades.Tipo;
import eventos.entidades.Usuario;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private UsuarioDao udao;
	
	@Autowired
	private EventoDao edao;
	
	@Autowired
	private PerfilDao pdao;
	
	@Autowired
	private TipoDao tdao;
	
	@GetMapping("/inicioSesion")
	public String inicioSesion(Authentication aut, HttpSession misesion) {
		
		String username = aut.getName();
		
		Usuario usuario=udao.buscarPorUsername(username);
		usuario.setPassword(null);
		
		misesion.setAttribute("usuario", usuario);
		
		
		
		
		return "forward:/home";
		
	}
	
	@GetMapping({"","/","/home"})
	public String home(Model model) {
		List<Evento> ele = edao.buscarTodos();
		List<Usuario> eleU = udao.buscarTodos();
		List<Tipo> eleT = tdao.buscarTodos();
;		model.addAttribute("eventos", ele);
		model.addAttribute("usuarios", eleU);
		model.addAttribute("tipos", eleT);
		return "home";
	    
	}
	
	@GetMapping("/login")
	public String login() {
		System.out.println("pasa por login2");
			
	    return "FormLogin"; // Redirige a login.html
	}
	
	@GetMapping("/login-error")
	public String loginError(Model model) {
		System.out.println("pasa por aqui");
		model.addAttribute("mensaje", "Invalid username or password.");
        return "forward:/login";
	}
	
	
	
	@GetMapping("/registro")
	public String mostrarRegisratee() {
		return "Registrate";
	}
	
	@PostMapping("/registro")
	public String procRegistro(Usuario usuario, RedirectAttributes ratt, Model model) {
		
		usuario.setEnabled(1);
		usuario.setFechaRegistro(new Date());
		usuario.setPerfiles(List.of(pdao.buscarUno(2)));
	 	usuario.setPassword("{noop}"+usuario.getPassword());
	 	
		
		if (udao.altaUsuario(usuario)) {
			return "redirect:/login";
			
		}else {
			ratt.addFlashAttribute("mensaje", "Este usuario ya existe");     
			return "redirect:/registro";
		}
	}
		
		@GetMapping("/public/verUno/{idEvento}")
		public String verUno(@PathVariable int idEvento, Model model) {
			
			model.addAttribute("evento", edao.findById(idEvento));
			
			
			return "DetalleEvento";
			
		}
		
		
			
	}
	



