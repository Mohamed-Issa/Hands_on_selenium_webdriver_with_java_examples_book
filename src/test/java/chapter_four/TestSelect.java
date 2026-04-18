package chapter_four;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestSelect {

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
	public void testSelect() {
		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
		driver.get(initUrl);

		Select select = new Select(driver.findElement(By.name("my-select")));
		String optionLabel = "Three";
		select.selectByVisibleText(optionLabel);

		assertThat(select.getFirstSelectedOption().getText()).isEqualTo(optionLabel);

	}

	@Test
	public void testDataList() {
		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
		driver.get(initUrl);

		WebElement dataList = driver.findElement(By.name("my-datalist"));
		dataList.click();
		WebElement option = driver.findElement(By.xpath("//datalist/option[2]"));
		String optionValue = option.getAttribute("value");

		dataList.sendKeys(optionValue);

		assertThat(optionValue).isEqualTo("New York");

	}

}
