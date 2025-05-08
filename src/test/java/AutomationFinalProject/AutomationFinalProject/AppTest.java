package AutomationFinalProject.AutomationFinalProject;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class AppTest extends TestData {

	@BeforeTest
	public void mySetup() {
		Setup();
	}

	@Test(priority = 1, enabled = false)
	public void CheckWebSiteLanguage(String ExpectedLanguage) {
		String ActualLanguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");
		Assert.assertEquals(ActualLanguage, ExpectedLanguage);
	}

	@Test(priority = 2, enabled = false)
	public void CheckCurrency() {
		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid='Header__CurrencySelector']"))
				.getText();
		Assert.assertEquals(ActualCurrency, expectedCurrency);
	}

	@Test(priority = 3, enabled = false)
	public void CheckContactNumber() {
		String ActualContactNumber = driver.findElement(By.linkText("+966554400000")).getText();
		Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);
	}

	@Test(priority = 4, enabled = false)
	public void CheckQitafLogo() {
		WebElement TheFooter = driver.findElement(By.tagName("footer"));
		boolean ActualImageIsDisplay = TheFooter.findElement(By.cssSelector(".sc-ekulBa.iOOTo"))
				.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-lcpuFF.jipXfR")).isDisplayed();
		Assert.assertEquals(ActualImageIsDisplay, true);
	}

	@Test(priority = 5, enabled = false)
	public void CheckHotelTabIsNotSelected() {
		WebElement HotalTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String ActualValue = HotalTab.getDomAttribute("aria-selected");
		Assert.assertEquals(ActualValue, expectedCheckHotelTabIsNotSelected);
	}

	@Test(priority = 6, enabled = false)
	public void FlightDepatureDate() {
		List<WebElement> dates = driver.findElements(By.cssSelector(".sc-dXfzlN.iPVuSG"));
		String ActualDepatureDate = dates.get(0).getText();
		System.out.println(tomorrow);
		System.out.println(ActualDepatureDate);
		System.out.println(tomorrowAsFormatedValue);
		Assert.assertEquals(ActualDepatureDate, tomorrowAsFormatedValue);
	}

	@Test(priority = 7, enabled = false)
	public void FlightReturnDate() {
		List<WebElement> dates = driver.findElements(By.cssSelector(".sc-dXfzlN.iPVuSG"));
		String ActualReturnDate = dates.get(1).getText();
		String dayAftertomorrowAsFormatedValue = String.format("%02d", dayAftertomorrow);
		System.out.println(ActualReturnDate);
		System.out.println(dayAftertomorrow);
		System.out.println(dayAftertomorrowAsFormatedValue);
		Assert.assertEquals(ActualReturnDate, dayAftertomorrowAsFormatedValue);
	}

	@Test(priority = 8, enabled = true)
	public void ChandeTheWebsiteLanguage() {
		driver.get(website[randomIndex]);
		if (driver.getCurrentUrl().contains("en")) {
			CheckWebSiteLanguage("en");
		} else {
			CheckWebSiteLanguage("ar");
		}
	}

	@Test(priority = 9, enabled = true)
	public void RandomlySelectCity() {
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTab.click();
		WebElement SearchInputField = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
		if (driver.getCurrentUrl().contains("en")) {
			SearchInputField.sendKeys(englishCities[randomEnglishCity]);
		} else {
			SearchInputField.sendKeys(arabicCities[randomArabicCity]);
		}
		WebElement SelectVistorNumber = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
		Select mySelect = new Select(SelectVistorNumber);
		driver.findElement(By.cssSelector(".sc-1vkdpp9-5.btwWVk")).click();
	}

	@Test(priority = 10, enabled = true)
	public void CheckTheResultsIsretretrived() throws InterruptedException {
		Thread.sleep(10000);
		String Results = driver.findElement(By.xpath("//span[@data-testid='srp_properties_found']")).getText();
		System.out.println(Results + "@@@@@@@@@@@@@@@@");
		if (driver.getCurrentUrl().contains("en")) {
			Assert.assertEquals(Results.contains("found"), true);
		} else {
			Assert.assertEquals(Results.contains("مكان إقامة"), true);
		}
	}

	@AfterTest
	public void AfterMyTest() {
	}
}
