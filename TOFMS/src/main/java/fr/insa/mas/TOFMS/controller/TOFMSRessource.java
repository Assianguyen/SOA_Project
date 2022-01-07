package fr.insa.mas.TOFMS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tof")
public class TOFMSRessource {

	@GetMapping("/value")
	public int getTOF() {
		return (int) Math.ceil(Math.random()*1000);
	}
}
