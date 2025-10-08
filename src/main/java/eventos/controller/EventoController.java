package eventos.controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.dao.EventoDao;
import eventos.dao.TipoDao;
import eventos.entidades.Evento;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/eventos")

public class EventoController {
	@Autowired
	private EventoDao edao;
	@Autowired
	private TipoDao tdao;
	
	
	
	
	@GetMapping("/alta")
	public String alta() {
		return "AltaEvento";
	}
	
	@PostMapping("/alta")
	public String procFormAlta(@ModelAttribute Evento evento, RedirectAttributes ratt) {
		
		evento.setEstado("ACEPTADO");
		
		if (evento.getDestacado() == null) {
			evento.setDestacado("N");
		}
		
		if (edao.insertOne(evento) == 1)
			
			ratt.addFlashAttribute("mensaje", "Alta realizada");
		else
			ratt.addFlashAttribute("mensaje", "Alta no realizada");	
		
		return "redirect:/home";
			
	}
	
	@GetMapping("/editar/{idEvento}")
	public String procModificar(@PathVariable int idEvento, Model model, RedirectAttributes ratt) {
		
		Evento evento = edao.findById(idEvento);
		if (evento == null) {
			
			model.addAttribute("mensaje", "Este evento no existe");
			return "forward:/";
			
		}
		model.addAttribute("tipos", tdao.buscarTodos());
		model.addAttribute("evento", evento);
		return "editar";
		
	}
	
	@PostMapping("/editar/{idEvento}")
	public String proEdicion(@PathVariable int idEvento, Evento evento, RedirectAttributes ratt) {
		
	evento.setIdEvento(idEvento);
	if (edao.updateOne(evento) == 1) {
		ratt.addFlashAttribute("mensaje", "Evento actualizado correctamente");	
	}else {
		ratt.addFlashAttribute("mensaje", "Error al actualizar");	
	}
	return "redirect:/home";
		
	}
	
	@GetMapping("/cancelar/{idEvento}")
	public String cancelar(@PathVariable int idEvento, RedirectAttributes ratt) {
		
		if (edao.cancelarEvento(idEvento) == 1) {
			ratt.addFlashAttribute("mensaje", "evento cancelado");	
		} else {
			ratt.addFlashAttribute("mensaje", "evento NO cancelado");
		}
		return "redirect:/home";
	}
	
	@GetMapping("/detalle/{id}")
	public String detalle(@PathVariable int id, Model model, RedirectAttributes ratt) {
	    
	    Evento evento = edao.findById(id);
	    if (evento == null) {
	        ratt.addFlashAttribute("mensaje", "El evento solicitado no existe.");
	        return "redirect:/eventos"; 
	    }
	    model.addAttribute("evento", evento);
	    return "DetalleEvento"; 
	}
	


	
	
		
		
		
		
	

}
