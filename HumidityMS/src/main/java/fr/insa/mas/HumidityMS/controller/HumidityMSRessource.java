package fr.insa.mas.HumidityMS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/humidity")
public class HumidityMSRessource {
	
	@GetMapping("/value")
	public int getHumidity() {
		return (int) Math.ceil(Math.random()*100);
	}
}
