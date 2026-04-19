package chapter_four;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestIframe {

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
	public void testIFrames() {
		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/iframes.html";
		driver.get(initUrl);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("my-iframe"));
		
		By pName  = By.tagName("p");
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(pName, 0));
		List<WebElement> paragraph = driver.findElements(pName);
		assertThat(paragraph).hasSize(20);

	}

}
