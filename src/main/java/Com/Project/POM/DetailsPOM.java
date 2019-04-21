package Com.Project.POM;



import java.util.ArrayList;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import Com.Project.Utility.GenericMethods;

// This Class is to retrieve the book details


public class DetailsPOM {
	
		private WebDriver driver; 
		
		String TitleText =null ;
		
		String Reviews = null;
		
		String Ratings =null;
		
		String Avail = null;
		
		String P_Price;
		
		String P_Delivery;
		
		boolean Displayed;
		
		boolean K_Displayed;
		
		ArrayList<WebElement> AuthorList = new ArrayList<WebElement>();
		
		WebElement Exist;
		
		String SoldBy;
		
		String Book;
		
		String Used_Price;
		
		boolean K_Buy,K_Sample;
		
		
		
		
	public DetailsPOM(WebDriver driver) {
		
		this.driver = driver; 
		
		PageFactory.initElements(driver, this);
	}
	
	GenericMethods GM= new GenericMethods(driver);
	
	
	//Variable to find the title of the book.
	
	@FindBy(xpath="//*[@id='productTitle']")
	private WebElement Title;
	
	//Variable to find the Author of the book.
	
	@FindBy(xpath="//*[@class='a-link-normal contributorNameID']")
	private List<WebElement> Author;
	
	// Variable to find the # of Customer Reviews
	
	@FindBy(xpath="//*[@id='acrCustomerReviewText']")
	private WebElement CustomerReview;
	
	
	
	// Variable to find the Paperbook
	
	@FindBys({@FindBy(xpath="//*[@id='mediaTab_heading_1']")})
	private List<WebElement> Paper;
	
	// Variable to check if the Buy New Radio Button is clicked
	
	//@FindBy(xpath="//*[@id='newOfferAccordionRow']")
	@FindBy(linkText="Buy New")
	private WebElement BuyNew;
	
	// Variable to check the availability of Paperbook
	
	@FindBy(xpath="//*[@id='availability']")
	private WebElement Paper_availability;
	
	// Variable to check the price of Paperbook
	
	@FindBy(xpath="//*[@class='a-size-medium a-color-price header-price']")
	private WebElement Paper_Price;
	
	//Variable to find Shipment Delivery Details
	
	@FindBy(xpath="//*[@id='delivery-message']")
	private WebElement Paper_Delivery;
	
	
	// Variable to check Buy Used Radio Button 
	
	@FindBy(xpath=("(//*[@class='a-size-medium header-text'])[1]"))
	private WebElement BuyUsed;
	
	// Variable to check the price When Buy Used
	
	@FindBy(xpath="//*[@class='a-size-medium header-price a-color-price']")
	private WebElement BuyUsed_Price;
	
	
	// **************** KINDLE RELATED VARAIBLES ******************
	
	// Variable to go to the Kindle Tab
	
	@FindBy(xpath="//*[@id='mediaTab_heading_0']")
	private WebElement Kindle;
	
	// Variable to get the sold by details
	
	@FindBy(xpath="//*[@class='a-color-base']")
	private WebElement Kindle_SoldBy;
	
	// Variable to find Ebook Features
	
		@FindBy(xpath="//*[@id='eTextbookBulletList']")
		private WebElement Kindle_Features;
		
		
    // Variable to find Buy Now Button in Kindle Tab
		
		@FindBy(id="checkout-button")
		private WebElement BuyNowBtn;
		
	// Variable to find Send mE Free Sample Button
		
		@FindBy(xpath="//*[@value='Send a free sample']")
		private WebElement SampleBtn;
		
	// *************** OTHER SELLERS TAB********************
		
	// Variable to go Other Sellers tab
		
		@FindBy(xpath="//*[@id='mediaTab_heading_2']")
		private WebElement OtherSellers;
	
	
	// ************ METHODS FOR EACH VARIRABLES *****************
	
	// Getting the Title of the Book	
	
	public String GetTitle()
	
	{
		TitleText =Title.getText();
		
		return TitleText;
	}
	
	// Get Author
	
	public ArrayList<WebElement> GetAuthor()
	{
		for(WebElement Authorname : Author){
			
			AuthorList.add(Authorname);
			
          //  System.out.println(Authorname.getText());
            
        } 
		
		System.out.println("Size of Author is ====>  "+ AuthorList.size());
		
		for ( int i =0 ; i<AuthorList.size();i ++)
		{
			System.out.println("Authors Name ==> " + AuthorList.get(i).getText() );
		}
		
		return AuthorList;
		
	}
	
	//Method to return Customer Reviews
	
	public String CustomerReviews() {
		
		Reviews = CustomerReview.getText();
		
		return Reviews;
	}
	
	// Verify if Results in Tabbed Format or not
	
	public boolean FieldExists()
	{
		if (Paper.size()==0)
			
		{
			Displayed = false;
			
		}
		
		else
		{
			Displayed= true;
		}
		
		return Displayed;
	}
	
	// Verify the PaperBack availability Status for Buy New Option
	
	public String PaperBack_BuyNew_Availability()
	{
		
		Avail =Paper_availability.getText();
		 
		return Avail;
	}
	
	// Verify the PaperBack Price Details for Buy New Option
	
	public String PaperBack_BuyNew_Price()
	{
		
		P_Price =Paper_Price.getText();
		 
		return P_Price;
	}
	
	// Verify the PaperBack Delivery Details for Buy New Option
	
	public String PaperBack_Delivery()
	{
		
		P_Delivery =Paper_Delivery.getText();
		 
		return P_Delivery;
	}
	
	// Navigate to the Paper Back Buy Used 
	
	public void PaperBack_BuyUsed()
	{
		BuyUsed.click();
	}
	
	// Verify the PaperBack Delivery Details for Buy Used Option
	
	public String Buyused_Price()
	{
		Used_Price=BuyUsed_Price.getText();
		return Used_Price;
	}
	
	// Navigate to Kindle Tab
	
	public void  Kindle_Tab()
	{
		Kindle.click();
	}
	
	// Retrieve the Sold By Details on Kindle Tab
	
	public String  Kindle_SoldBy_Details()
	{
		SoldBy=Kindle_SoldBy.getText();
		
		return SoldBy;
	}
	
	// Retrieve the Ebook Feature Details on Kindle Tab
	
	public String Kindle_Book_Features()
	{
		
		Book= Kindle_Features.getText();
		
		return Book;
	}
	
	// Check if Buy Now Available exists or not
	
	public boolean Kindle_BuyNow()
	{
		K_Buy =BuyNowBtn.isDisplayed();
		
		return K_Buy;
	}
	
	// Check if Sample Button Details exists or not
	
	public boolean Kindle_Sample()
	{
		K_Sample =SampleBtn.isDisplayed();
		
		return K_Sample;
	}
	
	// Navigate to other Seller Feature Tab
	
	public void  Other_Tab()
	{
		OtherSellers.click();
	}
	
	
}
