package chapter_three;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.awt.Desktop.Action;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestContextAndDoubleClick {

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
	public void testContextAndDoubleClick() {

		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html";
		driver.get(initUrl);

		Actions actions = new Actions(driver);

		WebElement dropdown1 = driver.findElement(By.id("my-dropdown-1"));
		dropdown1.click();
		WebElement contextWindow1 = driver.findElement(By.cssSelector("ul[class='dropdown-menu show']"));
		assertThat(contextWindow1.isDisplayed()).isTrue();

		WebElement dropdown2 = driver.findElement(By.id("my-dropdown-2"));
		actions.contextClick(dropdown2).build().perform();
		WebElement contextWindow2 = driver.findElement(By.id("context-menu-2"));
		assertThat(contextWindow2.isDisplayed()).isTrue();

		WebElement dropdown3 = driver.findElement(By.id("my-dropdown-3"));
		actions.doubleClick(dropdown3).build().perform();
		WebElement contextWindow3 = driver.findElement(By.id("context-menu-3"));
		assertThat(contextWindow3.isDisplayed()).isTrue();

	}

}
