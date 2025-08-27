package org.springframework.samples.petclinic.tests.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddPetPage {
	private WebDriver driver;
	private WebDriverWait wait;

	// Локаторы
	private By ownerName = By.cssSelector(".form-group span"); // "George Franklin"
	private By petNameInput = By.id("name");
	private By birthDateInput = By.id("birthDate");
	private By typeDropdown = By.id("type");
	private By addPetButton = By.cssSelector("button[type='submit']");

	public AddPetPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	// Получить имя владельца (только для проверки)
	public String getOwnerName() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(ownerName)).getText();
	}

	// Ввод имени питомца
	public void setPetName(String name) {
		WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(petNameInput));
		input.clear();
		input.sendKeys(name);
	}

	// Установка даты рождения
	public void setBirthDate(String date) {
		WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(birthDateInput));
		input.clear();
		input.sendKeys(date); // формат yyyy-MM-dd
	}

	// Выбор типа питомца
	public void selectPetType(String type) {
		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(typeDropdown));
		dropdown.click();
		dropdown.findElement(By.cssSelector("option[value='" + type + "']")).click();
	}

	// Нажать "Add Pet"
	public void clickAddPet() {
		wait.until(ExpectedConditions.elementToBeClickable(addPetButton)).click();
	}
}
