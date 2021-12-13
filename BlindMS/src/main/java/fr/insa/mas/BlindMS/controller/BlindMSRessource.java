package fr.insa.mas.BlindMS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.mas.BlindMS.model.*;

@RestController
@RequestMapping("/blind")
public class BlindMSRessource {
	
	private static Blind[] listBlinds = {new Blind(1, 0, 0, false), new Blind(2, 0, 0, false)};
	
	@GetMapping("/{id}")
	public Blind getBlind(@PathVariable("id") int id) {
		return listBlinds[id-1];
	}
	
	@GetMapping("/status/{id}")
	public String getStatus(@PathVariable("id") int id) {
		if(listBlinds[id-1].isMoving()) {
			return "Moving from "+listBlinds[id-1].getCurrentOpening()+" to "+listBlinds[id-1].getOrder();
		}
		
		if(listBlinds[id-1].getCurrentOpening() > 0) {
			return "Open";
		}
		
		return "Close";
	}
	
	@GetMapping("/opening/{id}")
	public int getOpening(@PathVariable("id") int id) {
		return listBlinds[id-1].getCurrentOpening();
	}
	
	@PostMapping("/order/{id}")
	public void giveOrder(@PathVariable("id") int id, @RequestParam int value) {
		listBlinds[id-1].setOrder(value);
	}
	
	@PutMapping("/{id}")
	public void updateBlind(@PathVariable("id") int id, @RequestBody Blind blind) {
		this.listBlinds[id-1].setOrder(blind.getOrder());
	}
}
