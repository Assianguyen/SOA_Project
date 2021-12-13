package fr.insa.mas.DoorMS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.mas.DoorMS.model.*;

@RestController
@RequestMapping("/door")
public class DoorMSRessource {
	
	private static Door[] listDoors = {new Door(1, false, false, false, false), new Door(2, false, true, false, true)};
	
	@GetMapping("/{id}")
	public Door getDoor(@PathVariable("id") int id) {
		return listDoors[id-1];
	}
	
	@GetMapping("/status/{id}")
	public String getStatus(@PathVariable("id") int id) {
		String msg = "";
		
		if(listDoors[id-1].getCurrentLockState()) {
			msg += "	|Unlocked <br>";
		} else {
			msg += "	|Locked <br>";
		}
		
		if(listDoors[id-1].getCurrentOpeningState()) {
			msg += "	|Open <br>";
		} else {
			msg += "	|Close <br>";
		}
		
		return msg;
	}
	
	@PostMapping("/orderL/{id}")
	public void giveOrderLock(@PathVariable("id") int id, @RequestParam boolean value) {
		listDoors[id-1].setOrderLock(value);
	}
	
	@PostMapping("/orderO/{id}")
	public void giveOrderOpening(@PathVariable("id") int id, @RequestParam boolean value) {
		listDoors[id-1].setOrderOpening(value);
	}
	
	@PutMapping("/{id}")
	public void updateDoor(@PathVariable("id") int id, @RequestBody Door door) {
		this.listDoors[id-1].setOrderLock(door.getOrderLock());
		this.listDoors[id-1].setOrderOpening(door.getOrderOpening());
	}
}
