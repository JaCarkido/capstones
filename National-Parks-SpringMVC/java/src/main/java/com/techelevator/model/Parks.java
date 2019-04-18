package com.techelevator.model;

public class Parks {

	private String code;
	private String name;
	private String state;
	private int acreage;
	private int elevation;
	private double miles;
	private int campsites;
	private String climate;
	private int founded;
	private int visitors;
	private String quote;
	private String source;
	private String description;
	private int fee;
	private int animals;
	private int count;
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	//${isFree(parkFee)}
	public String isFree(String fee) {
		
		int parkFee = Integer.parseInt(fee);
		if(parkFee==0) {
			return "Free!";
		}
		String feeString;
		feeString = "$" + Integer.toString(parkFee);
		return feeString;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getAcreage() {
		return acreage;
	}
	public void setAcreage(int acreage) {
		this.acreage = acreage;
	}
	public int getElevation() {
		return elevation;
	}
	public void setElevation(int elevation) {
		this.elevation = elevation;
	}
	public double getMiles() {
		return miles;
	}
	public void setMiles(double miles) {
		this.miles = miles;
	}
	public int getCampsites() {
		return campsites;
	}
	public void setCampsites(int campsites) {
		this.campsites = campsites;
	}
	public String getClimate() {
		return climate;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	public int getFounded() {
		return founded;
	}
	public void setFounded(int founded) {
		this.founded = founded;
	}
	public int getVisitors() {
		return visitors;
	}
	public void setVisitors(int visitors) {
		this.visitors = visitors;
	}
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public int getAnimals() {
		return animals;
	}
	public void setAnimals(int animals) {
		this.animals = animals;
	}
	
}
