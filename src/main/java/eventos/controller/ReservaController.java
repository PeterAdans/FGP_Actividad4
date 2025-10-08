package eventos.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.dao.EventoDao;
import eventos.dao.ReservaDao;
import eventos.dao.TipoDao;
import eventos.entidades.Evento;
import eventos.entidades.Reserva;
import eventos.entidades.Usuario;
import jakarta.servlet.http.HttpSession;

@Controller

public class ReservaController {
	
	@Autowired
	private ReservaDao rdao;
	
	@Autowired
	private EventoDao edao;
	
	@PostMapping("/cliente/evento/alta/{id}")
	public String reserva(@PathVariable int id, @RequestParam int cantidad, RedirectAttributes ratt, Model model, HttpSession sesion) {
		Usuario usuario = (Usuario) sesion.getAttribute("usuario");
		Evento evento = edao.findById(id);
		BigDecimal precioReserva = evento.calcularPrecio(cantidad);
		Reserva reserva = new Reserva(0, cantidad, "no hay observaciones", precioReserva, evento, usuario); 
		rdao.insertOne(reserva);
		System.out.println(reserva);
		ratt.addFlashAttribute("mensaje", "reserva realizada con exito");
		return "redirect:/public/verUno/"+id;
		
			
		
	
	}
		

}
