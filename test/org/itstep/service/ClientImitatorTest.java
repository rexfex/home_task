package org.itstep.service;

import static org.junit.jupiter.api.Assertions.*;

import org.itstep.dao.AccountDao;
import org.itstep.dao.ActionDao;
import org.itstep.dao.GoodDAO;
import org.itstep.model.Account;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

class ClientImitatorTest {

	AccountDao accDao = new AccountDao();
	ActionDao good_actionDao = new ActionDao();
	GoodDAO goodDAO = new GoodDAO();
	ClientImitator imitator = new ClientImitator();

	@Test
	void testRegisterAmazonAccount() {
		Account acc = new Account("jdfjfdsdsddsd@mail.ru", "hfhfgdgdg", "qqq", "Tresssdtiy");
		WebDriver driver = imitator.registerAmazonAccount(acc);
		Timer.waitSeconds(10);
		assertTrue(driver.getPageSource().contains("Hello, "));

	}

	@Test
	void addItemToWl() {
		Account acc = new Account("jdfjfdsdsddsd1@mail.ru", "hfhfgdgdg", "qqq", "Tresssdtiy");
		WebDriver driver = imitator.registerAmazonAccount(acc);
		Timer.waitSeconds(10);
		accDao.save(acc);

		imitator.addItemToWl(driver, acc.getLogin(), "B01IGO4A4S");
		Timer.waitSeconds(3);
		assertTrue(good_actionDao.get("jdfjfdsdsddsd1@mail.ru").isAdded_to_wl());

		imitator.addItemToCart(driver, acc.getLogin(), "B01IGO4A4S");
		Timer.waitSeconds(3);
		assertTrue(good_actionDao.get("jdfjfdsdsddsd1@mail.ru").isAdded_to_cart());

		good_actionDao.delete("jdfjfdsdsddsd1@mail.ru");
		accDao.delete("jdfjfdsdsddsd1@mail.ru");
		goodDAO.delete("B01IGO4A4S");
		driver.close();
	}

}