package org.springframework.samples.petclinic.tests.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OwnersPage {

	private WebDriver driver;

	private WebDriverWait wait;

	private String baseUrl;

	// Локаторы
	private By ownersTable = By.id("owners");

	private By ownerRows = By.cssSelector("#owners tbody tr");

	private By ownerNameLink = By.cssSelector("td a"); // ссылка с именем владельца

	// Конструктор с базовым URL
	public OwnersPage(WebDriver driver, String baseUrl) {
		this.driver = driver;
		this.baseUrl = baseUrl;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// // Открыть страницу владельцев
	// public void open() {
	// driver.get(baseUrl + "/owners/find");
	// }

	// Получить список всех владельцев из таблицы
	public List<WebElement> getAllOwnerRows() {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ownerRows));
	}

	// Проверить, что владелец с указанным именем есть в таблице
	public boolean isOwnerPresent(String fullName) {
		List<WebElement> rows = getAllOwnerRows();
		for (WebElement row : rows) {
			String name = row.findElement(ownerNameLink).getText();
			if (name.equals(fullName)) {
				return true;
			}
		}
		return false;
	}

	// Кликнуть на владельца по имени
	public OwnerDetailsPage clickOwnerByName(String fullName) {
		List<WebElement> rows = getAllOwnerRows();
		for (WebElement row : rows) {
			WebElement link = row.findElement(ownerNameLink);
			if (link.getText().equals(fullName)) {
				link.click();
				break;
			}
		}
		return new OwnerDetailsPage(driver, baseUrl);
	}

	public OwnerDetailsPage clickOwnerByIndex(int index) {
		List<WebElement> rows = getAllOwnerRows();
		WebElement link = rows.get(index).findElement(ownerNameLink);
		link.click();
		return new OwnerDetailsPage(driver, baseUrl);
	}

}
