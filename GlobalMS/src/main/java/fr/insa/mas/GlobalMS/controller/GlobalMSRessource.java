package fr.insa.mas.GlobalMS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.time.*;

import fr.insa.mas.GlobalMS.model.*;

@RestController
@RequestMapping("/auto")
public class GlobalMSRessource {

	private final String luminosityURI = "http://localhost:8087/luminosity";
	private final String presenceURI = "http://localhost:8089/presence";
	private final String temperatureURI = "http://localhost:8091/temperature";
	
	private final String blindURI = "http://localhost:8081/blind/";
	private final String doorURI = "http://localhost:8082/door/";
	private final String lightURI = "http://localhost:8086/light/";
	private final String stationURI = "http://localhost:8090/station/";
	private final String windowURI = "http://localhost:8093/window/";
	
	int orderBlind;
	boolean orderWindow;
	boolean orderLight;
	boolean orderO;
	boolean orderL;
	boolean orderStation;
	
	int luminosity;
	int temperature;
	boolean presence;
	
	int currentBlindOpening;
	String currentWindowStatus;
	String currentDoorStatus;
	String currentStationStatus;
	String currentLightStatus;
	String newBlindStatus;
	String newWindowStatus;
	String newDoorStatus;
	String newStationStatus;
	String newLightStatus;
	
	LocalDateTime now = LocalDateTime.now();
	int hour = now.getHour();
	int min = now.getMinute();
	
	@GetMapping("/run")
	public String run() {
		RestTemplate restTemplate = new RestTemplate();
		String msg = "";
		
		msg += "Horloge : "+hour+":"+min+"<br> <br>";
		
		luminosity = restTemplate.getForObject(luminosityURI+"/value", Integer.class);
		msg += "--- La luminosité est de "+luminosity+"<br>";
		
		temperature = restTemplate.getForObject(temperatureURI+"/value", Integer.class);
		msg += "--- La température est de "+temperature+"<br>";
		
		presence = restTemplate.getForObject(presenceURI+"/value", Boolean.class);
		if(presence) {
			msg += "--- Il n'y a personne dans la salle <br>";
		} else {
			msg += "--- Il y a quelqu'un dans la salle <br> <br>";
		}
		
		for(int i = 1; i < 3; i++) {
			Blind blind = restTemplate.getForObject(blindURI+i, Blind.class);
			Door door = restTemplate.getForObject(doorURI+i, Door.class);
			Station station = restTemplate.getForObject(stationURI+i, Station.class);
			Light light = restTemplate.getForObject(lightURI+i, Light.class);
			Window window = restTemplate.getForObject(windowURI+i, Window.class);
			
			msg += "Blind "+i+"<br>";
			
			currentBlindOpening = restTemplate.getForObject(blindURI+"opening/"+i, Integer.class);
			msg += "Ouverture actuelle : "+currentBlindOpening+" % <br>";
			
			msg += "## Commande actuelle : "+blind.getOrder()+"<br>";
			
			if(luminosity > 50) {
				msg += "	| Forte luminosité -> Il faut fermer les volets <br>";
				orderBlind = 100 - luminosity;
				orderLight = false;
			} else {
				msg += "	| Faible luminosité -> Il faut ouvrir les volets <br>";
				orderBlind = 100 - luminosity;
				orderLight = true;
			}
			blind.setOrder(orderBlind);
			
			restTemplate.put(blindURI+i, blind);
			
			newBlindStatus = restTemplate.getForObject(blindURI+"status/"+i, String.class);
			msg += "*** Le status du volet est : "+newBlindStatus+"<br> <br>";
			
			msg += "Door "+i+"<br>";
			
			currentDoorStatus = restTemplate.getForObject(doorURI+"status/"+i, String.class);
			msg += "Etat actuel : <br>"+currentDoorStatus;
			
			msg += "## Commande actuelle : <br>";
			msg += "### Lock : "+door.getOrderLock()+"<String newLightStatusbr>";
			msg += "### opening : "+door.getOrderOpening()+"<br>";
			
			if(!presence && ((hour > 21) || (hour < 8))) {
				orderO = false;
				orderL = false;
				orderStation = false;
				door.setOrderOpening(orderO);
			} else {
				orderL = true;
			}
			
			door.setOrderLock(orderL);
			restTemplate.put(doorURI+i, door);
			
			newDoorStatus = restTemplate.getForObject(doorURI+"status/"+i, String.class);
			msg += "Nouvel état : <br>"+newDoorStatus+"<br>";
			
			msg += "Station "+i+"<br>";
			
			currentStationStatus = restTemplate.getForObject(stationURI+"status/"+i, String.class);
			msg += "Etat actuel : <br>"+currentStationStatus;
			
			restTemplate.put(stationURI+i, station);
			
			station.setOrder(orderStation);
			newStationStatus = restTemplate.getForObject(stationURI+"status/"+i, String.class);
			msg += "Nouvel état : <br>"+newStationStatus+"<br>";
			
			
			msg += "Light "+i+"<br>";
			
			currentLightStatus = restTemplate.getForObject(lightURI+"status/"+i, String.class);
			msg += "Etat actuel : <br>"+currentLightStatus;
			
			light.setOrder(orderLight);
			restTemplate.put(lightURI+i, station);
			
			newLightStatus = restTemplate.getForObject(lightURI+"status/"+i, String.class);
			msg += "Nouvel état : <br>"+newLightStatus+"<br>";
			
			msg += "Window "+i+"<br>";
			
			currentWindowStatus = restTemplate.getForObject(windowURI+"status/"+i, String.class);
			msg += "Ouverture actuelle : "+currentWindowStatus+"<br>";
			
			msg += "## Commande actuelle : "+window.getOrder()+"<br>";
			
			if(temperature > 25) {
				msg += "	| Forte température -> Il faut fermer les fenêtres <br>";
				orderWindow = false;
			} else if (temperature < 18) {
				msg += "	| Faible température -> Il faut ouvrir les fenêtres <br>";
				orderWindow = true;
			}
			window.setOrder(orderWindow);
			
			restTemplate.put(windowURI+i, window);
			
			newWindowStatus = restTemplate.getForObject(windowURI+"status/"+i, String.class);
			msg += "*** Le status de la fenêtre est : "+newWindowStatus+"<br> <br>";
		}
		
		return msg;
	}
}
