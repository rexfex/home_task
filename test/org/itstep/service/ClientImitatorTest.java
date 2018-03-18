package org.itstep.service;

import static org.junit.Assert.*;

import org.itstep.model.Account;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class ClientImitatorTest {

	@Test
	public void testLoginAccount() {

		Account acc = new Account("igniae@gmail.com","alexsuperpuper2207","rexfex", "sesdeded" );
		ClientImitator imitator = new ClientImitator();

		WebDriver driver = imitator.registerAmazonAccount(acc);
		
		Timer.waitSeconds(5);
		
//		assertTrue(driver.getPageSource().contains("Helo, "));
		
		//driver = imitator.addItemToWL(driver, "B077N43PST");
		
		//assertTrue(driver.getPageSource().contains(""));
		
		driver.quit();
	
	}

}
