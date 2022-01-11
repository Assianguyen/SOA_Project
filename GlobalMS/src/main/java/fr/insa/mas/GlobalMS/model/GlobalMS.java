package fr.insa.mas.GlobalMS.model;

public class GlobalMS {
	static private final String globalURI = "http://localhost:8080";
	
	static private final String gazURI = "http://localhost:8083/gaz";
	static private final String luminosityURI = "http://localhost:8085/luminosity";
	static private final String noiseURI = "http://localhost:8086/noise";
	static private final String presenceURI = "http://localhost:8087/presence";
	static private final String temperatureURI = "http://localhost:8089/temperature";
	static private final String tofURI = "http://localhost:8090/tof";
	
	static private final String blindURI = "http://localhost:8081/blind/";
	static private final String doorURI = "http://localhost:8082/door/";
	static private final String lightURI = "http://localhost:8084/light/";
	static private final String stationURI = "http://localhost:8088/station/";
	static private final String windowURI = "http://localhost:8091/window/";
	
	private Blind blind1;
	private Blind blind2;
	private Door door1;
	private Door door2;
	private Light light1;
	private Light light2;
	private Station station1;
	private Station station2;
	private Window window1;
	private Window window2;
	
	private int luminosity;
	private int temperature;
	private int presence;
	private int distance;
	private int noise;
	private int CO2;
	
	private boolean emergency = false;
	private boolean tooLoud = false;
	private boolean missingItem = false;
	private boolean COVID = false;
	
	private int seuilLuminosity = 50;
	private int seuilTemperatureHot = 25;
	private int seuilTemperatureCold = 18;
	private int seuilPresence = 20;
	private int seuilDistance = 500;
	private int seuilNoise = 85;
	private int seuilCO2 = 800;
	
	private int orderBlind;
	private boolean orderWindow;
	private boolean orderLight;
	private boolean orderDoor;
	private boolean orderStation;
	private boolean orderSW = false;

	private int hour;
	private int min;
	private int day;
	private int month;
	private int year;
	
	public GlobalMS() {
	}

	public Blind getBlind1() {
		return blind1;
	}

	public void setBlind1(Blind blind1) {
		this.blind1 = blind1;
	}

	public Blind getBlind2() {
		return blind2;
	}

	public void setBlind2(Blind blind2) {
		this.blind2 = blind2;
	}

	public Door getDoor1() {
		return door1;
	}

	public void setDoor1(Door door1) {
		this.door1 = door1;
	}

	public Door getDoor2() {
		return door2;
	}

	public void setDoor2(Door door2) {
		this.door2 = door2;
	}

	public Light getLight1() {
		return light1;
	}

	public void setLight1(Light light1) {
		this.light1 = light1;
	}

	public Light getLight2() {
		return light2;
	}

	public void setLight2(Light light2) {
		this.light2 = light2;
	}

	public Station getStation1() {
		return station1;
	}

	public void setStation1(Station station1) {
		this.station1 = station1;
	}

	public Station getStation2() {
		return station2;
	}

	public void setStation2(Station station2) {
		this.station2 = station2;
	}

	public Window getWindow1() {
		return window1;
	}

	public void setWindow1(Window window1) {
		this.window1 = window1;
	}

	public Window getWindow2() {
		return window2;
	}

	public void setWindow2(Window window2) {
		this.window2 = window2;
	}

	public int getLuminosity() {
		return luminosity;
	}

	public void setLuminosity(int luminosity) {
		this.luminosity = luminosity;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getPresence() {
		return presence;
	}

	public void setPresence(int presence) {
		this.presence = presence;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getNoise() {
		return noise;
	}

	public void setNoise(int noise) {
		this.noise = noise;
	}

	public int getCO2() {
		return CO2;
	}

	public void setCO2(int cO2) {
		CO2 = cO2;
	}

	public boolean isEmergency() {
		return emergency;
	}

	public void setEmergency(boolean emergency) {
		this.emergency = emergency;
	}

	public boolean isTooLoud() {
		return tooLoud;
	}

	public void setTooLoud(boolean tooLoud) {
		this.tooLoud = tooLoud;
	}

	public boolean isMissingItem() {
		return missingItem;
	}

	public void setMissingItem(boolean missingItem) {
		this.missingItem = missingItem;
	}

	public boolean isCOVID() {
		return COVID;
	}

	public void setCOVID(boolean cOVID) {
		COVID = cOVID;
	}

	public int getSeuilLuminosity() {
		return seuilLuminosity;
	}

	public void setSeuilLuminosity(int seuilLuminosity) {
		this.seuilLuminosity = seuilLuminosity;
	}

	public int getSeuilTemperatureHot() {
		return seuilTemperatureHot;
	}

	public void setSeuilTemperatureHot(int seuilTemperatureHot) {
		this.seuilTemperatureHot = seuilTemperatureHot;
	}

	public int getSeuilTemperatureCold() {
		return seuilTemperatureCold;
	}

	public void setSeuilTemperatureCold(int seuilTemperatureCold) {
		this.seuilTemperatureCold = seuilTemperatureCold;
	}

	public int getSeuilPresence() {
		return seuilPresence;
	}

	public void setSeuilPresence(int seuilPresence) {
		this.seuilPresence = seuilPresence;
	}

	public int getSeuilDistance() {
		return seuilDistance;
	}

	public void setSeuilDistance(int seuilDistance) {
		this.seuilDistance = seuilDistance;
	}

	public int getSeuilNoise() {
		return seuilNoise;
	}

	public void setSeuilNoise(int seuilNoise) {
		this.seuilNoise = seuilNoise;
	}

	public int getSeuilCO2() {
		return seuilCO2;
	}

	public void setSeuilCO2(int seuilCO2) {
		this.seuilCO2 = seuilCO2;
	}

	public int getOrderBlind() {
		return orderBlind;
	}

	public void setOrderBlind(int orderBlind) {
		this.orderBlind = orderBlind;
	}

	public boolean isOrderWindow() {
		return orderWindow;
	}

	public void setOrderWindow(boolean orderWindow) {
		this.orderWindow = orderWindow;
	}

	public boolean isOrderLight() {
		return orderLight;
	}

	public void setOrderLight(boolean orderLight) {
		this.orderLight = orderLight;
	}

	public boolean isOrderDoor() {
		return orderDoor;
	}

	public void setOrderDoor(boolean orderDoor) {
		this.orderDoor = orderDoor;
	}

	public boolean isOrderStation() {
		return orderStation;
	}

	public void setOrderStation(boolean orderStation) {
		this.orderStation = orderStation;
	}

	public boolean isOrderSW() {
		return orderSW;
	}

	public void setOrderSW(boolean orderSW) {
		this.orderSW = orderSW;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public static String getGlobaluri() {
		return globalURI;
	}

	public static String getGazuri() {
		return gazURI;
	}

	public static String getLuminosityuri() {
		return luminosityURI;
	}

	public static String getNoiseuri() {
		return noiseURI;
	}

	public static String getPresenceuri() {
		return presenceURI;
	}

	public static String getTemperatureuri() {
		return temperatureURI;
	}

	public static String getTofuri() {
		return tofURI;
	}

	public static String getBlinduri() {
		return blindURI;
	}

	public static String getDooruri() {
		return doorURI;
	}

	public static String getLighturi() {
		return lightURI;
	}

	public static String getStationuri() {
		return stationURI;
	}

	public static String getWindowuri() {
		return windowURI;
	}
}