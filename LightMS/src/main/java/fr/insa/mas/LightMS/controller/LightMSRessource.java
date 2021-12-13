package fr.insa.mas.LightMS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.mas.LightMS.model.*;

@RestController
@RequestMapping("/light")
public class LightMSRessource {
	
	private static Light[] listLights = {new Light(1, false, false), new Light(2, true, true)};
	
	@GetMapping("/{id}")
	public Light getLight(@PathVariable("id") int id) {
		return listLights[id-1];
	}
	
	@GetMapping("/status/{id}")
	public String getStatus(@PathVariable("id") int id) {
		String msg = "";
		
		if(listLights[id-1].getCurrentState()) {
			msg += "	|On <br>";
		} else {
			msg += "	|Off <br>";
		}
		
		return msg;
	}
	
	@PostMapping("/order/{id}")
	public void giveOrder(@PathVariable("id") int id, @RequestParam boolean value) {
		listLights[id-1].setOrder(value);
	}
	
	@PutMapping("/{id}")
	public void updateDoor(@PathVariable("id") int id, @RequestBody Light door) {
		this.listLights[id-1].setOrder(door.getOrder());
	}
}
