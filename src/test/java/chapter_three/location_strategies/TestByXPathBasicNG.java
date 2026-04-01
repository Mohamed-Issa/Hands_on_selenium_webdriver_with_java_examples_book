package chapter_three.location_strategies;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

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

public class TestByXPathBasicNG {
	
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
	    public void testByXPathAdvanced() {
	        driver.get(
	                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
	        
	        WebElement radioButton1 = driver.findElement(By.xpath("//*[@type='radio' and (@checked)]"));
	        assertThat(radioButton1.isSelected()).isTrue();
	        
	        WebElement radioButton2 = driver.findElement(By.xpath("//*[@type='radio' and not(@checked)]"));
	        assertThat(radioButton2.isSelected()).isFalse();
	  
	       
	    }

}
