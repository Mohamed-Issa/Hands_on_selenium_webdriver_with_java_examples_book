package chapter_three;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestMouseOverNG {
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
	public void testMouseOver() {

		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/mouse-over.html";
		driver.get(initUrl);

		Actions actions = new Actions(driver);

		List<String> imageList = Arrays.asList("compass", "calendar", "award", "landscape");

		for (String imageName : imageList) {
			String xpath = String.format("//img[@src='img/%s.png']", imageName);
			WebElement image = driver.findElement(By.xpath(xpath));
			actions.moveToElement(image).build().perform();

			WebElement caption = driver.findElement(RelativeLocator.with(By.tagName("div")).near(image));
			assertThat(caption.getText()).containsIgnoringCase(imageName);

		}

	}

}
