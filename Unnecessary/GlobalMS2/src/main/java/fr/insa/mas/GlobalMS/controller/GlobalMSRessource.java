package fr.insa.mas.GlobalMS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.time.*;

import fr.insa.mas.GlobalMS.model.*;

@RestController
@RequestMapping("/gei")
public class GlobalMSRessource {
	
	private Global global1 = new Global();
	
	LocalDateTime now = LocalDateTime.now();
	int hour = now.getHour();
	int min = now.getMinute();
	int day = now.getDayOfMonth();
	int month = now.getMonthValue();
	int year = now.getYear();
	
	@PostMapping("/run")
	public void run() {
		int luminosity = 100;
		int temperature = 20;
		
		Blind[] listB = {new Blind(1, 0, 0, false), new Blind(2, 0, 0, false)};
		global1.setLuminosity(luminosity);
		global1.setTemperature(temperature);
		Blind b1 = new Blind(1, 0, 0, false);
		global1.setBlind1(b1);
		
		String msg = "{";
		//restTemplate.post(emergencyURI+"/update", sw1);
		msg += "Horloge : "+hour+":"+min+"<br> <br>";
		msg += " \"luminosity\": \""+luminosity+"\",";	
		msg += " \"temperature\": \""+temperature+"\"}";
	}
	
	@GetMapping("/data")
	public Global getGlobal() {
		return global1;
	}
}
