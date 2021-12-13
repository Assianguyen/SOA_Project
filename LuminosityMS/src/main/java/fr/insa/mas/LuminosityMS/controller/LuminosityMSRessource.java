package fr.insa.mas.LuminosityMS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/luminosity")
public class LuminosityMSRessource {

	@GetMapping("/value")
	public int getLuminosity() {
		return (int) Math.ceil(Math.random()*100);
	}
}
