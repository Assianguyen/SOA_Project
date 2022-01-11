package fr.insa.mas.GlobalMS.model;

import fr.insa.mas.GlobalMS.model.Blind;
import java.time.LocalDateTime;

public class Global {
	private final String globalURI = "http://localhost:8080";
	
	private Blind blind1;
	
	public Blind getBlind1() {
		return blind1;
	}

	public void setBlind1(Blind blind1) {
		this.blind1 = blind1;
	}
	
	private int orderBlind;
	private boolean orderWindow;
	private boolean orderLight;
	private boolean orderO;
	private boolean orderL;
	private boolean orderStation;
	private boolean orderSW = false;
	
	private int luminosity;
	private int temperature;
	private int presence;
	private int distance;
	private int noise;
	private int CO2;
	private boolean COVID = false;
	
	private int hour;
	private int min;
	private int day;
	private int month;
	private int year;
	
	public Global() {
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

	public boolean isOrderO() {
		return orderO;
	}

	public void setOrderO(boolean orderO) {
		this.orderO = orderO;
	}

	public boolean isOrderL() {
		return orderL;
	}

	public void setOrderL(boolean orderL) {
		this.orderL = orderL;
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

	public boolean isCOVID() {
		return COVID;
	}

	public void setCOVID(boolean cOVID) {
		COVID = cOVID;
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

	public String getGlobalURI() {
		return globalURI;
	}
	
	
}
