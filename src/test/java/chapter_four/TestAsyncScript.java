package chapter_four;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScriptKey;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestAsyncScript {
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
	public void testAsyncScripts() {
		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
		driver.get(initUrl);
		driver.manage().window().maximize();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		Duration pause = Duration.ofSeconds(2);

		String script = "const callback = arguments[arguments.length -1];" + "window.setTimeout(callback, "
				+ pause.toMillis() + ");";

		long initMillis = System.currentTimeMillis();

		js.executeAsyncScript(script);

		Duration elapsed = Duration.ofMillis(System.currentTimeMillis() - initMillis);
		
		System.out.println("the Script took "+ elapsed.toMillis() + " ms to be executed");
		
		assertThat(elapsed).isGreaterThan(pause);

	}

}
