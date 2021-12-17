package fr.insa.mas.EmergencySWMS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.mas.EmergencySWMS.model.*;
import fr.insa.mas.EmergencySWMS.model.EmergencySW;

@RestController
@RequestMapping("/emergency")


public class EmergencySWMSRessource {
	
	private EmergencySW sw1 = new EmergencySW(false,false);
	
	@GetMapping("/sw")
	public EmergencySW getSwitch() {
		return sw1;
	}
	
	@GetMapping("/status")
	public boolean getStatus() {
		return sw1.getCurrentState();
	}
	
	@PostMapping("/order")
	public void giveOrder(@RequestParam boolean value) {
		sw1.setOrder(value);
	}
	
	@PutMapping("/update")
	public void updateSwitch(@RequestBody EmergencySW sw) {
		this.sw1.setOrder(sw.getOrder());
	}

}
