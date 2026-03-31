package chapter_three.location_strategies;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

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

public class TestByHtmlAttributes {
	
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
	    public void testByHtmlAttributes() {
	        driver.get(
	                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
	        
	        //by name
	        WebElement textInputByName = driver.findElement(By.name("my-text"));
	        assertThat(textInputByName.isEnabled()).isTrue();
	        
	        //by id
	        WebElement textInputById = driver.findElement(By.id("my-text-id"));
	        assertThat(textInputById.getAttribute("type")).isEqualTo("text");
	        assertThat(textInputById.getDomAttribute("type")).isEqualTo("text");
	        assertThat(textInputById.getDomProperty("type")).isEqualTo("text");
	        
	        assertThat(textInputById.getAttribute("myprop")).isEqualTo("myvalue");
	        assertThat(textInputById.getDomAttribute("myprop")).isEqualTo("myvalue");
	        assertThat(textInputById.getDomProperty("myprop")).isNull();
	        
	        //by class name
	        List<WebElement> byClassName = driver.findElements(By.className("form-control"));
	        assertThat(byClassName.size()).isPositive();
	        
	        assertThat(byClassName.get(0).getAttribute("name")).isEqualTo("my-text");
	        
	       
	    }

}
