package Com.Project.ResultHandling;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

public class CaptureScreenShot {
	
	
private WebDriver driver; 
	
	// the driver information will be given by selenium test case 

			public CaptureScreenShot(WebDriver driver)
			{
				this.driver = driver; 
			}
			
			
			
			
			public void TakeScreenShot(String fileName){
				
				String path = "./Reports/ScreenShots/";
				
				GregorianCalendar calendar = new GregorianCalendar(); 
				
				int date =  calendar.get(Calendar.DATE); 
				int minute = calendar.get(Calendar.MINUTE);
				int second = calendar.get(Calendar.SECOND); 
				
				
				fileName = fileName+"-"+new Integer(date).toString() + "-" + new Integer(minute).toString() +"-" +
							new Integer(second).toString() +".png"; 
			
			
				// 1. create file 
				// 2. capture screenshot from selenium 
				// 3. store it in physical driver 
				
			
				
				try 
				{
					TakesScreenshot takeScreenShot  = (TakesScreenshot) driver; 
					
					File file = takeScreenShot.getScreenshotAs(OutputType.FILE);
					
					FileUtils.copyFile(file, new File(path +fileName));
				} 
				catch (WebDriverException e) 
				{
					e.printStackTrace();
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
				
				
		}

}
