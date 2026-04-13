package chapter_three;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestDragAndDrop {
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
		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html";
		driver.get(initUrl);

		Actions actions = new Actions(driver);

		WebElement draggable = driver.findElement(By.id("draggable"));
		int offset = 100;

		Point initLocation = draggable.getLocation();
		actions.dragAndDropBy(draggable, offset, 0).dragAndDropBy(draggable, 0, offset)
				.dragAndDropBy(draggable, -offset, 0).dragAndDropBy(draggable, 0, -offset).build().perform();
		assertThat(initLocation).isEqualTo(draggable.getLocation());

		WebElement target = driver.findElement(By.id("target"));
		actions.dragAndDrop(draggable, target).build().perform();

		assertThat(target.getLocation()).isEqualTo(draggable.getLocation());

	}

}
