package fr.insa.mas.PresenceMS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/presence")
public class PresenceMSRessource {

	@GetMapping("/value")
	public int getPresence() {
		int rdmPresence = (int) Math.ceil(Math.random()*100);
		
		return rdmPresence;
	}
}
