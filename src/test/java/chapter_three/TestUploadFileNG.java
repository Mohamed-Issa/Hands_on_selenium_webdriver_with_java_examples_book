package chapter_three;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestUploadFileNG {

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
	public void testUploadFile() throws IOException {

		String initUrl = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";
		driver.get(initUrl);

		WebElement inputFile = driver.findElement(By.name("my-file"));

		Path templFile = Files.createTempFile("tempFiles", ".temp");

		String fileName = templFile.toAbsolutePath().toString();

		log.debug("Using the temporal file {} in file uploading", fileName);
		System.out.println("Using the temporal file " + fileName + " in file uploading");

		inputFile.sendKeys(fileName);

		driver.findElement(By.tagName("form")).submit();

		System.out.println(driver.getCurrentUrl());
		System.out.println(initUrl);
		assertThat(driver.getCurrentUrl()).isNotEqualTo(initUrl);

	}

}
