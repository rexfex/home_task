package org.model;

public class Goods {
	private String asin;
	private String price;
	private String name;
	private String url;
	private String imgUrl;
	public String getAsin() {
		return asin;
	}
	public void setAsin(String asin) {
		this.asin = asin;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
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
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Goods() {
				// TODO Auto-generated constructor stub
	}
	public Goods(String asin, String price, String name, String url, String imgUrl) {
		
		this.asin = asin;
		this.price = price;
		this.name = name;
		this.url = url;
		this.imgUrl = imgUrl;
	}
	
}
