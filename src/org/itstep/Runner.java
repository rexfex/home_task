package org.itstep;

import org.itstep.dao.AccountDao;
import org.itstep.dao.GoodDAO;
import org.itstep.model.Account;
import org.itstep.service.ClientImitator;
import org.itstep.service.Timer;
import org.openqa.selenium.WebDriver;

public class Runner {
	static ClientImitator imitator = new ClientImitator();
	static WebDriver driver;
	static Account acc = new Account();

	public static void main(String[] args) {
		
//		acc = new Account("3114297@mail.ru", "function", "Serge", "Drugiy");
//		driver = loginAndSaveDriver(acc);
//		
//		imitator.addItem(driver, "B01G9ADQYG", "wl");
//		imitator.addItem(driver, "B01FXN3E74", "wl");
//		imitator.addItem(driver, "B01FXN3E74", "ac");

		
		acc = new Account("rexfex777@ukr.net", "function", "Serge","Drugiy");
		driver = loginAndSaveDriver(acc);
		
//		imitator.addItem(driver, "B01G9ADQYG", "wl");
//		imitator.addItem(driver, "B01FXN3E74", "wl");
		imitator.addItem(driver, "B01FXN3E74", "ac");

		
		
//	 Account acc2 = new Account("rexfex777@ukr.net", "function", "Serge",
		// "Drugiy");
		// Account acc3 = new Account("firstasd@mail.ru", "qwertyuiop", "Ivan",
		// "Tretiy");
	}

	public static WebDriver loginAndSaveDriver(Account acc) {
		AccountDao accDao = new AccountDao();
		WebDriver driver = imitator.loginAmazonAccount(acc);
	Timer.waitSeconds(5);
		if ((driver.getPageSource().contains("Hello, "))) {
			if (accDao.get(acc.getLogin()).getPassword() == null) {
				accDao.save(acc);
			}
		} else {
			System.out.println("account not registred");
		}

		return driver;

	}

}
