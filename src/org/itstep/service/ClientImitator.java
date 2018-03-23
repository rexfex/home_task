package org.itstep.service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.itstep.dao.ActionDao;
import org.itstep.dao.GoodDAO;
import org.itstep.model.Account;
import org.itstep.model.GoodAction;
import org.itstep.model.Goods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ClientImitator {

	private static final String SEPARATOR = System.getProperty("file.separator");

	private static final String USER_DIR = System.getProperty("user.dir");

	private static final String DRIVER_PATH = USER_DIR + SEPARATOR + "lib" + SEPARATOR + "chromedriver";

	private static final String BASE_URL = "https://www.amazon.com";
	GoodDAO goodDao = new GoodDAO();
	Goods good = new Goods();
	ActionDao actionDao = new ActionDao();
	GoodAction action = new GoodAction();

	public WebDriver getWebDriver() {

		System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);

		Timer.waitSeconds(3);
		return driver;
	}

	public WebDriver registerAmazonAccount(Account account) {

		WebDriver driver = getWebDriver();
		Timer.waitSeconds(3);
		driver.get("https://www.amazon.com");
	
		Timer.waitSeconds(5);

		WebElement wlBlock = driver.findElement(By.id("nav-al-wishlist"));
		WebElement wlLinkElement = wlBlock.findElement(By.tagName("a"));

		String wlLink = wlLinkElement.getAttribute("href");

		driver.get(wlLink);
		Timer.waitSeconds(5);

		WebElement registerBlock = driver.findElement(By.className("a-button-inner"));
		WebElement registerLinkElement = registerBlock.findElement(By.tagName("a"));
		String registerLink = registerLinkElement.getAttribute("href");
		driver.get(registerLink);
		Timer.waitSeconds(5);
		WebElement registredBotoon = driver.findElement(By.id("createAccountSubmit"));
		registredBotoon.click();
		Timer.waitSeconds(5);

		WebElement nameElement = driver.findElement(By.id("ap_customer_name"));
		WebElement emailElement = driver.findElement(By.id("ap_email"));
		WebElement passwordElement = driver.findElement(By.id("ap_password"));
		WebElement checkPasswordElement = driver.findElement(By.id("ap_password_check"));
		WebElement submitElement = driver.findElement(By.id("continue"));

		nameElement.sendKeys(account.getFistName() + " " + account.getSecondName());
		emailElement.sendKeys(account.getLogin());
		passwordElement.sendKeys(account.getPassword());
		checkPasswordElement.sendKeys(account.getPassword());
		submitElement.submit();
		Timer.waitSeconds(5);

		WebElement createList = driver.findElement(By.id("createList-announce"));
		createList.click();
		Timer.waitSeconds(5);

		WebElement WLList = driver.findElement(By.id("WLNEW_list_name"));
		WLList.sendKeys(" + Wish List");
		WebElement createWishList = driver.findElement(By.xpath(".//*[text()='Create List']/.."));
		createWishList.click();
		Timer.waitSeconds(5);

		String pageLink = driver.getCurrentUrl();
		driver.get(pageLink);

		Timer.waitSeconds(10);

		return driver;
	}

	public void addItemToWl(WebDriver driver, String login, String itemAsin) {

		String startUrl = driver.getCurrentUrl();

		WebElement searchInput = driver.findElement(By.id("twotabsearchtextbox"));
		searchInput.sendKeys(itemAsin);
		Timer.waitSeconds(15);

		WebElement searchBox = driver.findElement(By.id("nav-search"));
		List<WebElement> inputElements = searchBox.findElements(By.tagName("input"));
		for (WebElement inputElement : inputElements) {
			if (inputElement.getAttribute("type").equals("submit")) {
				inputElement.submit();
				break;
			}
		}

		Timer.waitSeconds(5);
		String currentUrl = driver.getCurrentUrl();
		driver.get(currentUrl);

		Timer.waitSeconds(5);

		WebElement productsBlock = driver.findElement(By.id("s-results-list-atf"));
		List<WebElement> productList = productsBlock.findElements(By.tagName("li"));

		for (WebElement productElement : productList) {

			if (productElement.getAttribute("data-asin") == null) {
				continue;
			}
			if (productElement.getAttribute("data-asin").equals(itemAsin)) {
				List<WebElement> productLinkElements = productElement.findElements(By.tagName("a"));
				for (WebElement aElement : productLinkElements) {
					if (aElement.getAttribute("class") != null) {
						if (aElement.getAttribute("class").contains("s-access-detail-page")) {
							String productLink = aElement.getAttribute("href");
							if (!productLink.contains(BASE_URL)) {
								productLink = BASE_URL + productLink;
							}
							driver.get(productLink);
							Timer.waitSeconds(5);
							break;
						}
					}
				}
			}
		}

		WebElement wlBtnWL = driver.findElement(By.id("add-to-wishlist-button-submit"));
		WebElement nameProdukt = driver.findElement(By.id("productTitle"));
		WebElement price = driver.findElement(By.id("priceblock_ourprice"));

		good = new Goods(itemAsin, nameProdukt.getText(), ChToInt(price.getText()), driver.getCurrentUrl());
		if (goodDao.get(itemAsin).getName() == null) {
			goodDao.save(good);
		}
		wlBtnWL.click();

		action = new GoodAction(login, itemAsin, true, false);
		actionDao.save(action);

		Timer.waitSeconds(5);
		driver.get(startUrl);
	}

	public void addItemToCart(WebDriver driver, String login, String itemAsin) {

		driver.get(goodDao.get(itemAsin).getUrl());
		
		WebElement wlBtnAC = driver.findElement(By.id("add-to-cart-button"));
		wlBtnAC.click();
		action = new GoodAction(login, itemAsin, false, true);
		actionDao.update(itemAsin, action);
	}

	public static int ChToInt(String ch) {
		char[] charArray = new char[10];
		charArray = ch.toCharArray();
		for (int i = 1; i < charArray.length; i++) {
			charArray[i - 1] = charArray[i];
		}
		String b = String.valueOf(charArray);
		double c = Double.parseDouble(b);

		int priceToInt = (int) (c * 100);
		return priceToInt;

	}

}
