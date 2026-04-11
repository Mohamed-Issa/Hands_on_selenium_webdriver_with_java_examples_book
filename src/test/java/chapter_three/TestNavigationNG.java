package chapter_three;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNavigationNG {

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
	public void testNavigation() {

		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
		driver.get(initUrl);

		driver.findElement(By.xpath("//a[text()='Navigation']")).click();
		driver.findElement(By.xpath("//a[text()='Next']")).click();
		driver.findElement(By.xpath("//a[text()='3']")).click();

		driver.findElement(By.xpath("//a[text()='2']")).click();
		driver.findElement(By.xpath("//a[text()='Previous']")).click();

		String bodyText = driver.findElement(By.tagName("body")).getText();

		assertThat(bodyText).contains("Lorem ipsum");

	}

	

}
