package org.itstep.model;

public class Goods {
	private String asin;
	private String name;
	private int price;
	private String url;
	public String getAsin() {
		return asin;
	}
	public void setAsin(String asin) {
		this.asin = asin;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Goods() {
		
	}
	public Goods(String asin, String name, int price, String url) {
		
		this.asin = asin;
		this.price = price;
		this.name = name;
		this.url = url;
	}
	
	
	
	}
	
	

