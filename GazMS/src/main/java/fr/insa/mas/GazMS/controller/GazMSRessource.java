package fr.insa.mas.GazMS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gaz")
public class GazMSRessource {
	
	@GetMapping("/CO")
	public int getCO() {
		return (int) Math.ceil(Math.random()*1000);
	}
	
	@GetMapping("/CO2")
	public int getCO2() {
		return (int) Math.ceil(Math.random()*1000);
	}
}
