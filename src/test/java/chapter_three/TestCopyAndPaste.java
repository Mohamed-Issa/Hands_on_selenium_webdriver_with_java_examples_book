package chapter_three;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCopyAndPaste {
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
	public void testDragAndDrop() {
		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
		driver.get(initUrl);

		Actions actions = new Actions(driver);

		WebElement inputText = driver.findElement(By.name("my-text"));
		WebElement textarea = driver.findElement(By.name("my-textarea"));

		Keys modifier = SystemUtils.IS_OS_MAC ? Keys.COMMAND : Keys.CONTROL;

		actions.sendKeys(inputText, "hello world").keyDown(modifier).sendKeys(inputText, "a").sendKeys(inputText, "c")
				.sendKeys(textarea, "v").build().perform();

		assertThat(inputText.getAttribute("value")).isEqualTo(textarea.getAttribute("value"));

	}

}
