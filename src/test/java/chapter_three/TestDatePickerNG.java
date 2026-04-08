package chapter_three;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.locators.RelativeLocator.RelativeBy;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestDatePickerNG {

	static final Logger log = getLogger(lookup().lookupClass());

	WebDriver driver;

	@BeforeClass
	public void setupClass() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void testDatePicker() {
		driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

		LocalDate today = LocalDate.now();
		int currentYear = today.getYear();
		int currentDay = today.getDayOfMonth();

		System.out.println(today + "\n" + currentYear + "\n" + currentDay);

		WebElement datePicker = driver.findElement(By.cssSelector("input[name='my-date']"));
		datePicker.click();

		WebElement monthElement = driver
				.findElement(By.xpath(String.format("//th[contains(text(),'%d')]", currentYear)));
		monthElement.click();

		WebElement yearElement = driver
				.findElement(By.xpath(String.format("//th[@class='datepicker-switch' and text()='%d']", currentYear)));

		// Click on the left arrow using relative locators
		WebElement arrowLeft = driver.findElement(RelativeLocator.with(By.tagName("th")).toLeftOf(yearElement));
		arrowLeft.click();

		WebElement monthPastYear = driver
				.findElement(RelativeLocator.with(By.cssSelector("span[class$=focused]")).below(arrowLeft));

		monthPastYear.click();

		WebElement dayElement = driver
				.findElement(By.xpath(String.format("//td[@class='day' and contains(text(),'%d')]", currentDay)));
		dayElement.click();

		String oneYearBack = datePicker.getAttribute("value");
		log.debug("Final date in the date picker: {}", oneYearBack);
		System.out.println(oneYearBack);

		LocalDate previousYear = today.minusYears(1);

		DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		String expectedDate = previousYear.format(dateformat);

		log.debug("Expected date: {}", expectedDate);

		assertThat(oneYearBack).isEqualTo(expectedDate);

	}
	
	
  
}
