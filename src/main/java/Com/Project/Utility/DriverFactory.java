package Com.Project.Utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import Com.Project.Utility.Driver;


public class DriverFactory {
	
private static WebDriver driver; 
	
	public static WebDriver getDriver(String driverName){

		if(driverName.equals("CHROME"))
		{
			System.setProperty(Driver.CHROME, Driver.CHROME_PATH);
									
			driver = new ChromeDriver();
		
		}
		
		else if(driverName.equals("FIREFOX"))
		{
			System.setProperty(Driver.FIREFOX, Driver.FIREFOX_PATH);
			
			FirefoxOptions options = new FirefoxOptions();
			
			options.setCapability("marionette", true);
			
			driver = new FirefoxDriver(options);
			
		}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		
		return driver;
		
	}

}
