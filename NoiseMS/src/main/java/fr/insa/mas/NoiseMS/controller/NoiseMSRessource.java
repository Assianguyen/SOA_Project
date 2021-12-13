package fr.insa.mas.NoiseMS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/noise")
public class NoiseMSRessource {

	@GetMapping("/value")
	public int getNoise() {
		return (int) Math.ceil(Math.random()*120);
	}
}
