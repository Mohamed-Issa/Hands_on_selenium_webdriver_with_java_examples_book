package chapter_four;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.*;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestTimeouts {

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
	public void testPageLoadTimeout() {
		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/";

		driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(1));

		assertThatThrownBy(() -> driver.get(initUrl)).isInstanceOf(TimeoutException.class);

	}

	@Test
	public void testScriptTimeout() {
		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
		driver.get(initUrl);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(3));

		assertThatThrownBy(() -> {
			long wait = Duration.ofSeconds(5).toMillis();
			String script = "const callback = arguments[arguments.length -1];" + "window.setTimeout(callback, " + wait
					+ ");";
			js.executeAsyncScript(script);
		}).isInstanceOf(ScriptTimeoutException.class);

	}

}
