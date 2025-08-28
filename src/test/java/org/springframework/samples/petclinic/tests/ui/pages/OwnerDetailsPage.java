package org.springframework.samples.petclinic.tests.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OwnerDetailsPage {

	private WebDriver driver;

	private WebDriverWait wait;

	private String baseUrl;

	private int ownerId;

	// Локаторы
	private By ownerTable = By.cssSelector("table.table-striped:first-of-type");

	private By ownerTableRows = By.cssSelector("table.table-striped:first-of-type tbody tr");

	private By petsSection = By.xpath("//h2[text()='Pets and Visits']/following-sibling::table");

	private By petRows = By.xpath("//h2[text()='Pets and Visits']/following-sibling::table/tbody/tr");

	private By editOwnerButton = By.cssSelector("a.btn.btn-primary[href$='/edit']");

	private By addPetButton = By.cssSelector("a.btn.btn-primary[href$='/pets/new']");

	// Конструктор
	public OwnerDetailsPage(WebDriver driver, String baseUrl, int ownerId) {
		this.driver = driver;
		this.baseUrl = baseUrl;
		this.ownerId = ownerId;
	}

	public OwnerDetailsPage(WebDriver driver, String baseUrl) {
		this.driver = driver;
		this.baseUrl = baseUrl;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// // Открыть страницу владельца
	// public void open() {
	// driver.get(baseUrl + "/owners/" + ownerId);
	// }

	// Получить данные владельца по заголовку (Name, Address, City, Telephone)
	public String getOwnerData(String fieldName) {
		List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ownerTableRows));
		for (WebElement row : rows) {
			WebElement th = row.findElement(By.tagName("th"));
			WebElement td = row.findElement(By.tagName("td"));
			if (th.getText().equalsIgnoreCase(fieldName)) {
				return td.getText();
			}
		}
		return null;
	}

	// Клик по кнопке редактирования владельца
	public void clickEditOwner() {
		wait.until(ExpectedConditions.elementToBeClickable(editOwnerButton)).click();
	}

	// Клик по кнопке добавления нового питомца
	public AddPetPage clickAddNewPet() {
		wait.until(ExpectedConditions.elementToBeClickable(addPetButton)).click();
		return new AddPetPage(driver);
	}

	// Проверка наличия питомца по имени
	public boolean isPetPresent(String petName) {
		List<WebElement> pets = wait.until(ExpectedConditions
			.visibilityOfAllElementsLocatedBy(By.xpath("//h2[text()='Pets and Visits']/following-sibling::table//dd")));
		for (WebElement pet : pets) {
			if (pet.getText().equalsIgnoreCase(petName)) {
				return true;
			}
		}
		return false;
	}

	// Дополнительно можно добавить методы для перехода к редактированию питомца и
	// добавления визита

}
