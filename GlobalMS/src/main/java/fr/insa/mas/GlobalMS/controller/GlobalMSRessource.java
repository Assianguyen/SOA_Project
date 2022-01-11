package fr.insa.mas.GlobalMS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.time.*;

import fr.insa.mas.GlobalMS.model.*;

@RestController
@RequestMapping("/gei")
public class GlobalMSRessource {

	
	private GlobalMS gei = new GlobalMS();
	
	@GetMapping("/auto")
	public GlobalMS auto() {
		LocalDateTime now = LocalDateTime.now();
		gei.setHour(now.getHour());
		gei.setMin(now.getMinute());
		gei.setDay(now.getDayOfMonth());
		gei.setMonth(now.getMonthValue());
		gei.setYear(now.getYear());
		
		RestTemplate restTemplate = new RestTemplate();
		gei.setBlind1(restTemplate.getForObject(gei.getBlinduri()+1, Blind.class));
		gei.setBlind2(restTemplate.getForObject(gei.getBlinduri()+2, Blind.class));
		gei.setDoor1(restTemplate.getForObject(gei.getDooruri()+1, Door.class));
		gei.setDoor2(restTemplate.getForObject(gei.getDooruri()+2, Door.class));
		gei.setLight1(restTemplate.getForObject(gei.getLighturi()+1, Light.class));
		gei.setLight2(restTemplate.getForObject(gei.getLighturi()+2, Light.class));
		gei.setStation1(restTemplate.getForObject(gei.getStationuri()+1, Station.class));
		gei.setStation2(restTemplate.getForObject(gei.getStationuri()+2, Station.class));
		gei.setWindow1(restTemplate.getForObject(gei.getWindowuri()+1, Window.class));
		gei.setWindow2(restTemplate.getForObject(gei.getWindowuri()+2, Window.class));
		
		gei.setLuminosity(restTemplate.getForObject(gei.getLuminosityuri()+"/value", Integer.class));
		gei.setTemperature(restTemplate.getForObject(gei.getTemperatureuri()+"/value", Integer.class));
		gei.setPresence(restTemplate.getForObject(gei.getPresenceuri()+"/value", Integer.class));
		gei.setCO2(restTemplate.getForObject(gei.getGazuri()+"/CO2", Integer.class));
		gei.setDistance(restTemplate.getForObject(gei.getTofuri()+"/value", Integer.class));
		gei.setNoise(restTemplate.getForObject(gei.getNoiseuri()+"/value", Integer.class));
		
		if(gei.getPresence() > 0) {
			if((gei.getPresence() > gei.getSeuilPresence()) || (gei.getCO2() > gei.getSeuilCO2())) {
				gei.setCOVID(true);
			}else if((gei.getPresence() < gei.getSeuilPresence()) && (gei.getCO2() < gei.getSeuilCO2())){
				gei.setCOVID(false);
			}
		} else {
			gei.setCOVID(false);
		}
		
		if(gei.getDistance() > gei.getSeuilDistance()) {
			gei.setMissingItem(true);
		} else {
			gei.setMissingItem(false);
		}
		
		if(gei.getNoise() > gei.getSeuilNoise()) {
			gei.setTooLoud(true);
		} else {
			gei.setTooLoud(false);
		}
		
		if(gei.isCOVID()) {
			gei.setOrderDoor(true);
			gei.getDoor1().setOrderOpening(gei.isOrderDoor());
			restTemplate.put(gei.getDooruri()+1, gei.getDoor1());
			gei.getDoor2().setOrderOpening(gei.isOrderDoor());
			restTemplate.put(gei.getDooruri()+2, gei.getDoor2());
			
			gei.setOrderWindow(true);
			gei.getWindow1().setOrder(gei.isOrderWindow());
			restTemplate.put(gei.getWindowuri()+1, gei.getWindow1());
			gei.getWindow2().setOrder(gei.isOrderWindow());
			restTemplate.put(gei.getWindowuri()+2, gei.getWindow2());
		}else {			
			if(gei.getLuminosity() > gei.getSeuilLuminosity()) {
				gei.setOrderBlind(100-gei.getLuminosity());
				gei.setOrderLight(false);
			} else {
				gei.setOrderBlind(100-gei.getLuminosity());
				gei.setOrderLight(true);
			}
			gei.getBlind1().setOrder(gei.getOrderBlind());
			gei.getBlind2().setOrder(gei.getOrderBlind());
			restTemplate.put(gei.getBlinduri()+1, gei.getBlind1());
			restTemplate.put(gei.getBlinduri()+2, gei.getBlind2());
			
			if((gei.getHour() > 21) || (gei.getHour() < 8)) {
				if(gei.getPresence() == 0) {
					gei.setOrderDoor(false);
					gei.setOrderStation(false);
					gei.setOrderLight(false);
				}
			}else {
				gei.setOrderDoor(true);
			}

			gei.getDoor1().setOrderOpening(gei.isOrderDoor());
			gei.getDoor2().setOrderOpening(gei.isOrderDoor());
			restTemplate.put(gei.getDooruri()+1, gei.getDoor1());
			restTemplate.put(gei.getDooruri()+2, gei.getDoor2());
			
			gei.getStation1().setOrder(gei.isOrderStation());
			gei.getStation2().setOrder(gei.isOrderStation());
			restTemplate.put(gei.getStationuri()+1, gei.getStation1());
			restTemplate.put(gei.getStationuri()+2, gei.getStation2());
			
			gei.getLight1().setOrder(gei.isOrderLight());
			gei.getLight2().setOrder(gei.isOrderLight());
			restTemplate.put(gei.getLighturi()+1, gei.getLight1());
			restTemplate.put(gei.getLighturi()+2, gei.getLight2());
			
			if(gei.getTemperature() > gei.getSeuilTemperatureHot()) {
				gei.setOrderWindow(true);
			} else if (gei.getTemperature() < gei.getSeuilTemperatureCold()) {
				gei.setOrderWindow(false);
			}
			gei.getWindow1().setOrder(gei.isOrderWindow());
			gei.getWindow2().setOrder(gei.isOrderWindow());
			restTemplate.put(gei.getWindowuri()+1, gei.getWindow1());
			restTemplate.put(gei.getWindowuri()+2, gei.getWindow2());
		}
		return gei;
	}
	
	@GetMapping("/run")
	public GlobalMS run() {
		RestTemplate restTemplate = new RestTemplate();
		
		if(gei.getPresence() > 0) {
			if((gei.getPresence() > gei.getSeuilPresence()) || (gei.getCO2() > gei.getSeuilCO2())) {
				gei.setCOVID(true);
			}else if((gei.getPresence() < gei.getSeuilPresence()) && (gei.getCO2() < gei.getSeuilCO2())){
				gei.setCOVID(false);
			}
		} else {
			gei.setCOVID(false);
		}
		
		if(gei.getDistance() > gei.getSeuilDistance()) {
			gei.setMissingItem(true);
		} else {
			gei.setMissingItem(false);
		}
		
		if(gei.getNoise() > gei.getSeuilNoise()) {
			gei.setTooLoud(true);
		} else {
			gei.setTooLoud(false);
		}
		
		return gei;
	}
	
	@PostMapping("/seuil/{sensor}")
	public void setSeuil(@PathVariable("sensor") String sensor, @RequestParam int value) {
		switch (sensor) {
			case "luminosity":
				gei.setSeuilLuminosity(value);
				break;
			case "temperatureH":
				gei.setSeuilTemperatureHot(value);
				break;
			case "temperatureC":
				gei.setSeuilTemperatureCold(value);
				break;
			case "CO2":
				gei.setSeuilCO2(value);
				break;
			case "presence":
				gei.setSeuilPresence(value);
				break;
			case "noise":
				gei.setSeuilNoise(value);
				break;
			case "distance":
				gei.setSeuilDistance(value);
				break;
		}
	}
	
	@PostMapping("/button/{actuator}")
	public void order(@PathVariable("actuator") String actuator, @RequestParam Boolean value) {
		switch (actuator) {
			case "d1":
				gei.getDoor1().setOrderOpening(value);
				break;
			case "d2":
				gei.getDoor2().setOrderOpening(value);
				break;
			case "w1":
				gei.getWindow1().setOrder(value);
				break;
			case "w2":
				gei.getWindow2().setOrder(value);
				break;
			case "s1":
				gei.getStation1().setOrder(value);
				break;
			case "s2":
				gei.getStation2().setOrder(value);
				break;
			case "l1":
				gei.getLight1().setOrder(value);
				break;
			case "l2":
				gei.getLight2().setOrder(value);
				break;
			case "eSW":
				gei.setEmergency(value);
				break;
		}
	}
	
	@PostMapping("/order/{actuator}")
	public void orderB(@PathVariable("actuator") String actuator, @RequestParam int value) {
		switch (actuator) {
			case "b1":
				gei.getBlind1().setOrder(value);
				break;
			case "b2":
				gei.getBlind2().setOrder(value);
				break;
		}
	}
}
