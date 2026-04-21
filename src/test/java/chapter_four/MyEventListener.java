package chapter_four;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.formula.functions.Today;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;



public class MyEventListener implements WebDriverListener {
	
	static final Logger log = getLogger(lookup().lookupClass());
	
	
	@Override
	public void afterGet(WebDriver driver, String url) {
		
		WebDriverListener.super.afterGet(driver, url);
		takeScreenshot(driver);
	}

	@Override
	public void beforeQuit(WebDriver driver) {
		WebDriverListener.super.beforeQuit(driver);
		takeScreenshot(driver);
	}
	
	private void takeScreenshot(WebDriver driver) {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File file = screenshot.getScreenshotAs(OutputType.FILE);
		
		SessionId sessionId = ((RemoteWebDriver) driver).getSessionId();
		Date date = new Date();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.mm.dd_HH.mm.ss.SSS");
		
		String fileName = String.format("%s-%s.png", dateFormat.format(date), sessionId.toString());
		
		Path destination = Paths.get(fileName);
		
		try {
			Files.move(file.toPath(), destination);
		} catch (Exception e) {
			System.out.printf("Exception moving screenshot from %s to %s",file, destination);
		}
		
		
	}

}
