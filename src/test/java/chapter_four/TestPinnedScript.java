package chapter_four;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScriptKey;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.slf4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestPinnedScript {

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
	public void testPinnedScripts() {
		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
		driver.get(initUrl);
		driver.manage().window().maximize();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		ScriptKey linkKey = js.pin("return document.getElementsByTagName('a')[2];");
		ScriptKey firstArgKey = js.pin("return arguments[0];");

		Set<ScriptKey> pinnedScript = js.getPinnedScripts();

		assertThat(pinnedScript).hasSize(2);

		WebElement formLink = (WebElement) js.executeScript(linkKey);
		formLink.click();
		assertThat(driver.getCurrentUrl()).isNotEqualTo(initUrl);

		String message = "Hello World";

		String executeScript = (String) js.executeScript(firstArgKey, message);
		assertThat(executeScript).isEqualTo(message);

		js.unpin(linkKey);

		assertThat(js.getPinnedScripts()).hasSize(1);

	}

}
