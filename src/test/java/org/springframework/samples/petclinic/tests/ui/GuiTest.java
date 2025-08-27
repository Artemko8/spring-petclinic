package org.springframework.samples.petclinic.tests.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.samples.petclinic.tests.ui.pages.AddPetPage;
import org.springframework.samples.petclinic.tests.ui.pages.FindOwnerPage;
import org.springframework.samples.petclinic.tests.ui.pages.OwnerDetailsPage;
import org.springframework.samples.petclinic.tests.ui.pages.OwnersPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;

import java.time.Duration;

public class GuiTest {

	static WebDriver driver;

	@BeforeClass
	public static void setup() {
		driver = new ChromeDriver();
	}

	@AfterClass
	public static void teardown() {
		if (driver != null) {
			driver.quit(); // закрываем браузер
		}
	}

	@Test
	public void addPetToOwner() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\!Artem\\PetProj\\chromedriver-win64\\chromedriver.exe");
		String baseUrl = System.getProperty("base.url");

		FindOwnerPage findOwnerPage = new FindOwnerPage(driver, baseUrl);
		findOwnerPage.open();
		findOwnerPage.setLastName("Davis");
		OwnersPage ownersPage = findOwnerPage.clickFindOwner();
		OwnerDetailsPage ownerDetailsPage = ownersPage.clickOwnerByName("Betty Davis");
		AddPetPage addPetPage = ownerDetailsPage.clickAddNewPet();
		addPetPage.setPetName("Buddy");
		addPetPage.setBirthDate("2020-02-02");
		addPetPage.selectPetType("dog");
		addPetPage.clickAddPet();
		findOwnerPage.open();
		findOwnerPage.setLastName("Davis");
		ownersPage = findOwnerPage.clickFindOwner();
		ownerDetailsPage = ownersPage.clickOwnerByName("Betty Davis");
		Assert.assertTrue(ownerDetailsPage.isPetPresent("Buddy"));

		Thread.sleep(5000);
		System.out.println(driver.getTitle());
		driver.quit();
	}

}
