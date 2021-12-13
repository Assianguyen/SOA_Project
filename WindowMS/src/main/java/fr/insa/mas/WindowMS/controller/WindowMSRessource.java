package fr.insa.mas.WindowMS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.mas.WindowMS.model.*;

@RestController
@RequestMapping("/window")
public class WindowMSRessource {
	
private static Window[] listWindows = {new Window(1, false, false), new Window(2, true, true)};
	
	@GetMapping("/{id}")
	public Window getWindow(@PathVariable("id") int id) {
		return listWindows[id-1];
	}
	
	@GetMapping("/status/{id}")
	public String getStatus(@PathVariable("id") int id) {
		String msg = "";
		
		if(listWindows[id-1].getCurrentState()) {
			msg += "	|Open <br>";
		} else {
			msg += "	|Close <br>";
		}
		
		return msg;
	}
	
	@PostMapping("/order/{id}")
	public void giveOrder(@PathVariable("id") int id, @RequestParam boolean value) {
		listWindows[id-1].setOrder(value);
	}
	
	@PutMapping("/{id}")
	public void updateDoor(@PathVariable("id") int id, @RequestBody Window door) {
		this.listWindows[id-1].setOrder(door.getOrder());
	}
}
