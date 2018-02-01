package org.itstep.model;

public class GoodAction {
	private String asin;
	private String login;
	private Integer id;
	private String action;
	private Integer actionTime;
	public String getAsin() {
		return asin;
	}
	public void setAsin(String asin) {
		this.asin = asin;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Integer getActionTime() {
		return actionTime;
	}
	public void setActionTime(Integer actionTime) {
		this.actionTime = actionTime;
	}
	public GoodAction() {
		// TODO Auto-generated constructor stub
	}
	public GoodAction(String asin, String login, Integer id, String action, Integer actionTime) {
		super();
		this.asin = asin;
		this.login = login;
		this.id = id;
		this.action = action;
		this.actionTime = actionTime;
	}

}
