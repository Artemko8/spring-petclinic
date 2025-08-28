package org.springframework.samples.petclinic.tests.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FindOwnerPage {

	private WebDriver driver;

	private WebDriverWait wait;

	private String baseUrl;

	// Локаторы
	private By lastNameInput = By.id("lastName");

	private By findOwnerButton = By.cssSelector("button[type='submit']");

	private By resultsTable = By.cssSelector("table");

	// Конструктор с baseUrl
	public FindOwnerPage(WebDriver driver, String baseUrl) {
		this.driver = driver;
		this.baseUrl = baseUrl;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Действия на странице
	public void open() {
		driver.get(baseUrl + "/owners/find");
		wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
	}

	public void setLastName(String lastName) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
		driver.findElement(lastNameInput).clear();
		driver.findElement(lastNameInput).sendKeys(lastName);
	}

	public OwnersPage clickFindOwner() {
		wait.until(ExpectedConditions.elementToBeClickable(findOwnerButton)).click();
		return new OwnersPage(driver, baseUrl);
	}

	public boolean isOwnerInResults(String ownerName) {
		// WebElement table = driver.findElement(resultsTable);
		WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(resultsTable));
		return table.getText().contains(ownerName);
	}

}
