package chapter_three;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverManagerBuilderNGTest {
	
	static final Logger log = getLogger(lookup().lookupClass());

	 WebDriver driver;
	
	//@BeforeClass
	//public void setupClass() {
	//	WebDriverManager.chromedriver().setup();
	//}
	
	@BeforeMethod
	public void setup() {
		driver = WebDriverManager.chromedriver().create();
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void test() {
		String url = "https://bonigarcia.dev/selenium-webdriver-java/";
		driver.get(url);
		String title  = driver.getTitle();
		log.debug("the title of {} is {}.", url, title );
		
		assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java");
	}

}
