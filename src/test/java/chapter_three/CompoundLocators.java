package chapter_three;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CompoundLocators {

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
	public void testByIdOrName() {
		driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

		WebElement fileElement = driver.findElement(new ByIdOrName("my-file"));
		assertThat(fileElement.getAttribute("id")).isBlank();
		assertThat(fileElement.getAttribute("name")).isNotBlank();

	}

	@Test
	public void testByChained() {
		driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

		List<WebElement> rowsInForm = driver.findElements(new ByChained(By.tagName("form"), By.className("row")));
		assertThat(rowsInForm.size()).isEqualTo(1);

	}

	@Test
	public void testByAll() {
		driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

		List<WebElement> rowsInForm = driver.findElements(new ByAll(By.tagName("form"), By.className("row")));
		assertThat(rowsInForm.size()).isEqualTo(5);

	}

}
