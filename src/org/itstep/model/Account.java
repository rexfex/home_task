package org.itstep.model;

public class Account {
	private String login; // login this email.
	private String password;
	private String fistName;
	private String secondName;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFistName() {
		return fistName;
	}
	public void setFistName(String fistName) {
		this.fistName = fistName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public Account() {
		
		// TODO Auto-generated constructor stub
	}
	public Account(String login, String password, String fistName, String secondName) {
		
		this.login = login;
		this.password = password;
		this.fistName = fistName;
		this.secondName = secondName;
	}
	
}
