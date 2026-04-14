package chapter_three;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestExplicitWait {

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

//	@AfterMethod
//	public void tearDown() {
//		driver.quit();
//	}

	@Test
	public void testExplicitwait() {
		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/loading-images.html";
		driver.get(initUrl);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement landscape = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("landscape")));

		assertThat(landscape.getAttribute("src")).containsIgnoringCase("landscape");

	}

	@Test
	public void testFluentwait() {
		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/loading-images.html";
		driver.get(initUrl);

		Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);

		WebElement landscape = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("landscape")));

		assertThat(landscape.getAttribute("src")).containsIgnoringCase("landscape");

	}

}
