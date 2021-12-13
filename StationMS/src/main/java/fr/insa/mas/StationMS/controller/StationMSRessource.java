package fr.insa.mas.StationMS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.mas.StationMS.model.*;

@RestController
@RequestMapping("/station")
public class StationMSRessource {
	
	private static Station[] listStations = {new Station(1, false, false), new Station(2, true, true)};
	
	@GetMapping("/{id}")
	public Station getStation(@PathVariable("id") int id) {
		return listStations[id-1];
	}
	
	@GetMapping("/status/{id}")
	public String getStatus(@PathVariable("id") int id) {
		String msg = "";
		
		if(listStations[id-1].getCurrentState()) {
			msg += "	|On <br>";
		} else {
			msg += "	|Off <br>";
		}
		
		return msg;
	}
	
	@PostMapping("/order/{id}")
	public void giveOrder(@PathVariable("id") int id, @RequestParam boolean value) {
		listStations[id-1].setOrder(value);
	}
	
	@PutMapping("/{id}")
	public void updateDoor(@PathVariable("id") int id, @RequestBody Station door) {
		this.listStations[id-1].setOrder(door.getOrder());
	}
}
