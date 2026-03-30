package chapter_three;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SessionIdNGTest {
	
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
	public void testBasicMethod() {
		String url = "https://bonigarcia.dev/selenium-webdriver-java/";
		driver.get(url);
		String title  = driver.getTitle();
		log.debug("the title of {} is {}.", url, title );
		
		assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java");
		
		assertThat(driver.getCurrentUrl()).isEqualTo(url);
		
		assertThat(driver.getPageSource()).containsIgnoringCase("</html>");
	}

	@Test
	public void testSessionId() {
		String url = "https://bonigarcia.dev/selenium-webdriver-java/";
		driver.get(url);
		
		SessionId sessionId = ((RemoteWebDriver) driver).getSessionId();
		assertThat(sessionId).isNotNull();
		log.debug("the session is {}", sessionId.toString());
	}

}
