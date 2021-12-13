package fr.insa.mas.TemperatureMS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperature")
public class TemperatureMSResssource {

	@GetMapping("/value")
	public int getTemperature() {
		return (int) Math.ceil(Math.random()*45);
	}
}
