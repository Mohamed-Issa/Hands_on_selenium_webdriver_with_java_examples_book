package chapter_four;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestScrollBy {
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
	public void testScrollBy() {
		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/long-page.html";
		driver.get(initUrl);
		driver.manage().window().maximize();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		String script = "window.scrollBy(0,1000)";
		js.executeScript(script);

	}

	@Test
	public void testScrollIntoView() {
		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/long-page.html";
		driver.get(initUrl);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebElement lastElement = driver.findElement(By.cssSelector("p:last-child"));
		String script = "arguments[0].scrollIntoView()";
		js.executeScript(script, lastElement);
	}

	@Test
	public void testInfiniteScroll() {
		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/long-page.html";
		driver.get(initUrl);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		By pLocator = By.tagName("p");
		List<WebElement> paragraphs = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(pLocator, 0));
		
		//for(WebElement e : paragraphs) System.out.println(e.getText());
		//paragraphs.forEach(e -> System.out.println(e.getText()));
		
		int initParagraphsNumber = paragraphs.size();
		System.out.println(initParagraphsNumber);

		WebElement lastParagraph = driver.findElement(By.xpath(String.format("//p[%d]", initParagraphsNumber)));
		String script = "arguments[0].scrollIntoView()";
		js.executeScript(script, lastParagraph);

	}

}
