package Com.Project.TestCase;



import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Com.Project.POM.DetailsPOM;
import Com.Project.POM.HomePagePOM;
import Com.Project.POM.SearchResultPOM;
import Com.Project.ResultHandling.CaptureScreenShot;
import Com.Project.Utility.Driver;
import Com.Project.Utility.DriverFactory;


public class TestCase {
	
	//Declaring Driver Variable to initialize the driver 
	
	private static WebDriver driver;
	
	// Declaring Property Variable to read property file
	
	private static Properties properties;
	
	// Declaring variables to allocate the values after reading the property file
	
	private static String BaseURL;
	private static String Category;
	private static String TexttoSearch;
	private String Title;
	private String Reviews;	
	private String Avail ;
	private String P_Price;
	private String P_Delivery;
	private ArrayList<WebElement> Author;
	private String Sold;
	private String Book,Used_Price;
	Boolean Exist,K_Exist,O_Exist;	 
	Boolean K_BuyNow,K_Sample;
	
	private static CaptureScreenShot ScreenShot;
	
	
	
	//Declaring Variable to implement the instance of each Page Object Model Class 
	
	private static HomePagePOM HomePage;
	
	private static SearchResultPOM SearchResult;
	
	private static DetailsPOM Details;
	
	
	
	//Reading the Property File and getting URL , Category , Data To search Etc.,
	
	@BeforeClass
	
	public static void setUpBeforeClass() throws IOException {
		
		properties = new Properties();
		
		FileInputStream inStream = new FileInputStream("./resources/General.properties");
		
		properties.load(inStream);
		
		driver = DriverFactory.getDriver(Driver.CHROMEBROWSER);
		
		HomePage = new HomePagePOM(driver);
		
		SearchResult = new SearchResultPOM(driver);
		
		Details = new DetailsPOM(driver);
		
		BaseURL = properties.getProperty("baseURL");
		
		Category = properties.getProperty("Category");
		
		TexttoSearch = properties.getProperty("Search");
		
		// open the browser 
		
		driver.get(BaseURL);		
		
		 ScreenShot = new CaptureScreenShot(driver);
			
	}

	
	@AfterClass
	
	public void Close() throws Exception {
		
		Thread.sleep(1000);
		
		driver.quit();
	}
	
	@AfterMethod
	
	public void Waitfor() throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	
	//Login to Amazon.com and verify the title
	
	@Test (priority=0)
		
	public void BasicCheck()
	{
		String Title = driver.getTitle();
		
		System.out.println(Title);
		
		ScreenShot.TakeScreenShot("TITLE");
		
	}
	
	
	@Test(priority=1)
	
	// Select DropDown Category to Book and search the field
	
	public void SearchCriteria()
	
	{
		
		
		HomePage.DropdownSelect(Category);
		
		HomePage.InputText(TexttoSearch);
		
		HomePage.Search();
		
		ScreenShot.TakeScreenShot("SEARCHRESULT");
	}
	
	@Test(priority=2)
	
	// Click On the first test result and retrieve the author details , customer reviews 
	
	public void GetBookDetails()
	{
		SearchResult.ClickFirstValue();
		
		Title = Details.GetTitle();
		
		System.out.println(Title);
		
		Author = Details.GetAuthor();
		
		Reviews = Details.CustomerReviews();
		
		System.out.println(Reviews);
		
		ScreenShot.TakeScreenShot("BOOKDETAILS");
		
				
	}
	
	@Test(priority=3)
	
	// Retrieve the Paper Back Results if the page contains in the format of Tab like Kindle, PaperBack Etc.,
	
	public void Get_PaperBack_Details()
	{
		
		Exist =Details.FieldExists();		
		
		if ( Exist.TRUE)
		{
			Avail= Details.PaperBack_BuyNew_Availability();
			
			Assert.assertEquals(Avail, "In Stock.");
			
			System.out.println("Availability is ===> "+Avail);
			
			P_Price=Details.PaperBack_BuyNew_Price();
			
			System.out.println("Paper Back Buy New Price is =====> "+ P_Price);
			
			Assert.assertTrue(P_Price.contains("$"));
			
			P_Delivery= Details.PaperBack_Delivery();
			
			System.out.println("Paper Back Delivery Date is ===>  "+  P_Delivery);		
			
			Assert.assertTrue(P_Delivery.contains("This item ships to"));
			
			Details.PaperBack_BuyUsed();
			
			Used_Price= Details.Buyused_Price();
			
			System.out.println("Paper Back Used Price is =====> "+ Used_Price);
		}
		
		else
		{
			System.out.println("Page Results not in Tabbed Format like Kindle , Paper back etc.,");
		}
		
		ScreenShot.TakeScreenShot("BOOKPAPERBACK");
	}	
	
	
	
	@Test (priority=4)
	
	//// Retrieve the Kindle Results if the page contains in the format of Tab like Kindle, PaperBack Etc.,
	
	public void Kindle() throws Exception
	{
				
		K_Exist =Details.FieldExists();			
		
		if (K_Exist.TRUE)
		{
			Details.Kindle_Tab();
			
			Sold =Details.Kindle_SoldBy_Details();
			
			System.out.println("Kindle Product Sold by is =====>  "+  Sold);
			
			P_Price=Details.PaperBack_BuyNew_Price();
			
			System.out.println("Kindle Product Price is ====> "+P_Price);
			
			Assert.assertTrue(P_Price.contains("$"));
			
		    Book = Details.Kindle_Book_Features();
			
			System.out.println("Kindle Book Feature contains =====> "+ Book);
			
			K_BuyNow=Details.Kindle_BuyNow();
			
			Assert.assertTrue(K_BuyNow);
			
			K_Sample= Details.Kindle_Sample();
			
			Assert.assertTrue(K_Sample);
		}
		
		 else
			{
				System.out.println("Page Results not in Tabbed Format like Kindle , Paper back etc.,");
			}
		
		ScreenShot.TakeScreenShot("KINDLE");
	}
	
	

	@Test (priority=5)
	
	//// Retrieve the Other Seller Results if the page contains in the format of Tab like Kindle, PaperBack Etc.,
	
	public void Other() throws Exception
	{
	
		//Other Sellers Related
		
		O_Exist =Details.FieldExists();	
			
	    if (O_Exist.TRUE)
	    {
	    	Details.Other_Tab();	    	
	    	
	    }
	    
	    else
	    {
	    	System.out.println("Page Results not in Tabbed Format like Kindle , Paper back etc.,");
	    }
	    
	    ScreenShot.TakeScreenShot("OTHERSELLERS");
    
	}
	

}
