package Com.Project.POM;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePagePOM {
	
private WebDriver driver; 

WebDriverWait wait;


	
	public HomePagePOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	//@FindBy(id="searchDropdownBox")
	
	@FindBy(xpath="//*[@id='searchDropdownBox']")
	private WebElement Dropdown;
	

	
	@FindBy(id="twotabsearchtextbox")
	private WebElement SearchText;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement SearchButton;
	
	
	// Creating a method for DropDown Field 
	
	public void DropdownSelect(String Value)
	
	{
		
		Select Drop = new Select(Dropdown);
		
		Drop.selectByVisibleText(Value);
		
		
		
		
	}
	
	// Creating a Method for Search Text Box

	public void InputText(String Search)
	{
		
		SearchText.sendKeys(Search);
				
	}
	
	// Creating a Method to search button

		public void Search()
		{
			
			SearchButton.click();
					
		}
}
