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

	private final String gazURI = "http://localhost:8084/gaz";
	private final String luminosityURI = "http://localhost:8087/luminosity";
	private final String noiseURI = "http://localhost:8088/noise";
	private final String presenceURI = "http://localhost:8089/presence";
	private final String temperatureURI = "http://localhost:8091/temperature";
	private final String tofURI = "http://localhost:8092/tof";
	
	private final String blindURI = "http://localhost:8081/blind/";
	private final String doorURI = "http://localhost:8082/door/";
	private final String lightURI = "http://localhost:8086/light/";
	private final String stationURI = "http://localhost:8090/station/";
	private final String windowURI = "http://localhost:8093/window/";
	
	private final String emergencyURI = "http://localhost:8083/emergency";
	
	int orderBlind;
	boolean orderWindow;
	boolean orderLight;
	boolean orderO;
	boolean orderL;
	boolean orderStation;
	boolean orderSW = false;
	
	int luminosity;
	int temperature;
	int presence;
	int distance;
	int noise;
	int CO2;
	boolean COVID = false;
	
	int currentBlindOpening;
	String currentWindowStatus;
	String currentDoorStatus;
	String currentStationStatus;
	String currentLightStatus;
	boolean currentEmergencyStatus;
	String newBlindStatus;
	String newWindowStatus;
	String newDoorStatus;
	String newStationStatus;
	String newLightStatus;
	boolean newEmergencyStatus;
	
	LocalDateTime now = LocalDateTime.now();
	int hour = now.getHour();
	int min = now.getMinute();
	int day = now.getDayOfMonth();
	int month = now.getMonthValue();
	int year = now.getYear();
	
	@GetMapping("/run")
	public String run() {
		RestTemplate restTemplate = new RestTemplate();
		String msg = "";
		
		msg += "Horloge : "+hour+":"+min+"<br> <br>";
		
		luminosity = restTemplate.getForObject(luminosityURI+"/value", Integer.class);
		msg += "--- La luminosité est de "+luminosity+"<br>";
		
		temperature = restTemplate.getForObject(temperatureURI+"/value", Integer.class);
		msg += "--- La température est de "+temperature+"<br>";
		
		presence = restTemplate.getForObject(presenceURI+"/value", Integer.class);
		CO2 = restTemplate.getForObject(gazURI+"/CO2", Integer.class);
		if(presence > 0) {
			msg += "--- Il y a quelqu'un dans la salle <br>";
			if((presence > 20) || (CO2 > 800)) {
				msg += "ATTENTION: non respect de la jauge COVID <br> <br>";
				COVID = true;
				
			}else if((presence < 20) && (CO2 < 800)){
				msg += "Jauge COVID respectée <br> <br>";
				COVID = false;
			}
		} else {
			msg += "--- Il n'y a personne dans la salle <br> <br>";
			COVID = false;
		}
		
		distance = restTemplate.getForObject(tofURI+"/value", Integer.class);
		
		if(distance > 500) {
			msg += "--- URGENT: objet manquant... Envoi mail... <br> <br>";
		}
		
		noise = restTemplate.getForObject(noiseURI+"/value", Integer.class);
		
		if(noise > 85) {
			msg += "--- ATTENTION: niveau de bruit trop élevé <br> <br>";
		}
		
		EmergencySW sw1 = restTemplate.getForObject(emergencyURI+"/sw", EmergencySW.class);
		currentEmergencyStatus = restTemplate.getForObject(emergencyURI+"/status", Boolean.class);
		if (currentEmergencyStatus) {
			msg += "--- URGENCE : appelez les secours <br> <br>";
			orderSW = false;
		} 
		
		sw1.setOrder(orderSW);
		restTemplate.put(emergencyURI+"/update", sw1);		
		
		for(int i = 1; i < 3; i++) {
			Blind blind = restTemplate.getForObject(blindURI+i, Blind.class);
			Door door = restTemplate.getForObject(doorURI+i, Door.class);
			Station station = restTemplate.getForObject(stationURI+i, Station.class);
			Light light = restTemplate.getForObject(lightURI+i, Light.class);
			Window window = restTemplate.getForObject(windowURI+i, Window.class);
			
			if(COVID) {
				orderO = true;
				door.setOrderOpening(orderO);
				restTemplate.put(doorURI+i, door);
				
				orderWindow = true;
				window.setOrder(orderWindow);
				restTemplate.put(windowURI+i, window);
			}else {
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
				
				if((presence == 0) && ((hour > 21) || (hour < 8))) {
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
					msg += "	| Forte température -> Il faut ouvrir les fenêtres <br>";
					orderWindow = true;
				} else if (temperature < 18) {
					msg += "	| Faible température -> Il faut fermer les fenêtres <br>";
					orderWindow = false;
				}
				window.setOrder(orderWindow);
				
				restTemplate.put(windowURI+i, window);
				
				newWindowStatus = restTemplate.getForObject(windowURI+"status/"+i, String.class);
				msg += "*** Le status de la fenêtre est : "+newWindowStatus+"<br> <br>";
				}
			}
		
		return msg;
	}
}
