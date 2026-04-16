package chapter_four;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class TestTakeScreenshots {

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
	public void testScreenshotsPNG() throws IOException {
		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
		driver.get(initUrl);
		driver.manage().window().maximize();

		TakesScreenshot ts = (TakesScreenshot) driver;

		File file = ts.getScreenshotAs(OutputType.FILE);
		System.out.println("Screenshot created on " + file);

		Path destination = Paths.get("screenshot.png");
		Files.move(file.toPath(), destination, REPLACE_EXISTING);
		System.out.println("Screenshots moved on " + destination);

		assertThat(destination).exists();

	}

	@Test
	public void testScreenshotsBase64() {
		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
		driver.get(initUrl);
		driver.manage().window().maximize();

		TakesScreenshot ts = (TakesScreenshot) driver;

		String screenshot = ts.getScreenshotAs(OutputType.BASE64);

		System.out.printf("data:image/png;base64,%s", screenshot);
		assertThat(screenshot).isNotEmpty();

	}
	
	@Test
	public void testWebElementScreenshot() throws IOException {
		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
		driver.get(initUrl);
		driver.manage().window().maximize();
		
		WebElement form = driver.findElement(By.tagName("form"));

		File webElementScreenshot = form.getScreenshotAs(OutputType.FILE);
		System.out.println("Screenshot created on " + webElementScreenshot);

		Path destination = Paths.get("webElement-screenshot.png");
		Files.move(webElementScreenshot.toPath(), destination, REPLACE_EXISTING);
		System.out.println("Screenshots moved on " + destination);

		assertThat(destination).exists();

	}

}
