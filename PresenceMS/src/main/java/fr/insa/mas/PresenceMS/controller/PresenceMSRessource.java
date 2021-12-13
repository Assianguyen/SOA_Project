package fr.insa.mas.PresenceMS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/presence")
public class PresenceMSRessource {

	@GetMapping("/value")
	public boolean getPresence() {
		int rdmPresence = (int) Math.ceil(Math.random()*2);
		
		if(rdmPresence == 1) {
			return true;
		}
		
		return false;
	}
}
