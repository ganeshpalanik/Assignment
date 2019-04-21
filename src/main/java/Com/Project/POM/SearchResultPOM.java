package Com.Project.POM;


import java.util.Iterator;



import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


// This is to document the webelement of the search result page 

public class SearchResultPOM {
	
private WebDriver driver; 

WebDriverWait wait;


	
	public SearchResultPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	
	
	
			@FindBy(xpath="(//*[@class='a-link-normal a-text-normal']/span)[1]")
			private WebElement Firstlink;
	
	// Creating a method for clicking the first link from the search result
	
	public void ClickFirstValue()
	
	{
		Firstlink.click();
	}
}
