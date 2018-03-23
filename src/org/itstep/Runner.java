package org.itstep;


import org.itstep.dao.AccountDao;
import org.itstep.model.Account;
import org.itstep.service.ClientImitator;
import org.itstep.service.Timer;
import org.openqa.selenium.WebDriver;

public class Runner {
	static ClientImitator imitator = new ClientImitator();
	static WebDriver driver;

	public static void main(String[] args) {
		String[] login = {"jdfjfjf@mail.ru", "jdfjfjf1@mail.ru","jdfjfjf2@mail.ru"} ;
		
		for (int i = 0; i < login.length; i++) {
		Account acc = new Account(login[i], "qwertyuiop", "qqq", "Tresssdtiy");
		driver =newLoginAndSaveDriver(acc);
		imitator.addItemToWl(driver, acc.getLogin(), "B01IGO4A4S");
		imitator.addItemToWl(driver, acc.getLogin(), "B01IGMTBEO");
		imitator.addItemToCart (driver,acc.getLogin(),"B01IGMTBEO");
		driver.close();
		}
		
		
		
	}

	
	public static WebDriver newLoginAndSaveDriver(Account acc) {
		
		AccountDao accDao = new AccountDao();
		
		driver = imitator.registerAmazonAccount(acc);
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
