package chapter_four;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.events.EventFiringDecorator;

import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestEventListenerNG {
	
	static final Logger log = getLogger(lookup().lookupClass());

	WebDriver driver;



	@BeforeMethod
	public void setup() {
		MyEventListener listener = new MyEventListener();
		WebDriver originalDriver = WebDriverManager.chromedriver().create();
		driver = new EventFiringDecorator(listener).decorate(originalDriver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void testEvenListener() {
		driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
		assertThat(driver.getTitle()).isEqualTo("Hands-On Selenium WebDriver with Java");
		driver.findElement(By.linkText("Web form")).click();
	}

}
