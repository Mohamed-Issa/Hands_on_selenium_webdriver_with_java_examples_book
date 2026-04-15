package chapter_four;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestColorPicker {

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
	public void testColorPicker() {
		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
		driver.get(initUrl);
		driver.manage().window().maximize();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement colorPicker = driver.findElement(By.name("my-colors"));
		String initColor = colorPicker.getAttribute("value");
		System.out.println("the init color is " + initColor);

		Color red = new Color(255, 0, 0, 1);
		String script = String.format("arguments[0].setAttribute('value','%s')", red.asHex());
		js.executeScript(script, colorPicker);

		String colorFinal = colorPicker.getAttribute("value");

		assertThat(colorFinal).isNotEqualTo(initColor);
		assertThat(Color.fromString(colorFinal)).isEqualTo(red);

	}

}
