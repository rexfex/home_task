package org.itstep.service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.itstep.dao.GoodDAO;
import org.itstep.model.Account;
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

		WebElement registerBlock = driver.findElement(By.id("nav-flyout-ya-newCust"));
		WebElement registerLinkElement = registerBlock.findElement(By.tagName("a"));

		String registerLink = registerLinkElement.getAttribute("href");

		driver.get(registerLink);
		Timer.waitSeconds(10);

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

		String pageLink = driver.getCurrentUrl();
		Timer.waitSeconds(6);
		driver.get(pageLink);

		Timer.waitSeconds(10);

		return driver;
	}

	public WebDriver loginAmazonAccount(Account account) {
		WebDriver driver = getWebDriver();

		Timer.waitSeconds(3);
		driver.get(BASE_URL);
		Timer.waitSeconds(5);

		WebElement loginBlock = driver.findElement(By.id("nav-flyout-ya-signin"));
		WebElement loginLinkElement = loginBlock.findElement(By.tagName("a"));

		String loginLink = loginLinkElement.getAttribute("href");

		loginLink = loginLink.contains(BASE_URL) ? loginLink : BASE_URL + loginLink;
		driver.get(loginLink);
		Timer.waitSeconds(5);

		WebElement emailElement = driver.findElement(By.id("ap_email"));
		WebElement submitElement = driver.findElement(By.id("continue"));

		emailElement.sendKeys(account.getLogin());
		submitElement.click();
		Timer.waitSeconds(5);

		WebElement passwordElement = driver.findElement(By.id("ap_password"));
		WebElement submitElementP = driver.findElement(By.id("signInSubmit"));
		passwordElement.sendKeys(account.getPassword());
		submitElementP.submit();
		Timer.waitSeconds(5);

		String currentPage = driver.getCurrentUrl();
		driver.get(currentPage);

		return driver;
	}

	public void addItem(WebDriver driver, String itemAsin, String action) {

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

		/// !!!!!!!!!
		GoodDAO GoodDao = new GoodDAO();
		Goods good = new Goods();

		WebElement wlBtnWL = driver.findElement(By.id("add-to-wishlist-button-submit"));
		WebElement wlBtnAC = driver.findElement(By.id("add-to-cart-button"));
		WebElement nameProdukt = driver.findElement(By.id("productTitle"));
		WebElement nameProdukt1 = driver.findElement(By.id("priceblock_ourprice"));
		// WebElement nameProdukt2 = driver.findElement(By.className("a-text-strike"));
		// WebElement nameProdukt2 = driver.findElement(By.id("ListPriceLegalMessage"));
		good = new Goods(itemAsin, nameProdukt.getText(), ChToInt(nameProdukt1.getText()), driver.getCurrentUrl(), 0);
		if (GoodDao.get(itemAsin).getName() == null) {
			GoodDao.save(good);
		}
		if (action == "wl") {
			wlBtnWL.click();

		} else
			wlBtnAC.click();
		driver.navigate().back();

		System.out.println(nameProdukt.getText());
//		System.out.println(nameProdukt2.getText());
		// System.out.println(ChToInt(nameProdukt1.getText()));

		Timer.waitSeconds(5);
		driver.get(startUrl);
	}

	public static int ChToInt(String ch) {
		char[] charArray = new char[10];
		// char[] charArray1 = new char[10];
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
