package org.itstep.model;

import java.sql.Time;


public class GoodAction {
	private Integer id;
	private Time actionTime;
	private String login;
	private String asin;
	private boolean added_to_wl;
	private boolean added_to_cart;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Time getActionTime() {
		return actionTime;
	}
	public void setActionTime(Time actionTime) {
		this.actionTime = actionTime;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getAsin() {
		return asin;
	}
	public void setAsin(String asin) {
		this.asin = asin;
	}
	public boolean isAdded_to_wl() {
		return added_to_wl;
	}
	public void setAdded_to_wl(boolean added_to_wl) {
		this.added_to_wl = added_to_wl;
	}
	public boolean isAdded_to_cart() {
		return added_to_cart;
	}
	public void setAdded_to_cart(boolean added_to_cart) {
		this.added_to_cart = added_to_cart;
	}

	
	public GoodAction(String login, String asin, boolean added_to_wl,boolean added_to_cart) {
		this.login = login;
		this.asin = asin;
		this.added_to_wl = added_to_wl;
		this.added_to_cart = added_to_cart;
	}
	public GoodAction() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
