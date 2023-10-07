package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utils.AndroidActions;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidActions{
	AndroidDriver driver;

	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Aswini");


	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleRadioBtn;


	@AndroidFindBy(id="com.androidsample.generalstore:id/radiomale")
	private WebElement maleRadioBtn;

	@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countryDropdown;

	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement letsShopBtn;

	@AndroidFindBy(xpath="(//android.widget.Toast)[1]")
	private WebElement toastMessage;


	public void setNamefield(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}


	public void genderSelection(String gender) {
		if(gender.contains("female")) {
			femaleRadioBtn.click();
		}
		else
			maleRadioBtn.click();
	}

	public void countrySelection(String country) {
		countryDropdown.click();
		scrollToText(country);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+country+"']")).click();

	}

	public void clickOnLetShop() {
		letsShopBtn.click();

	}

	public String geTtoastMessage() {
		String Message= null;
		Message=toastMessage.getAttribute("name");

		return Message;

	}

	public void setActivity() {

		Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
		activity.setAppWaitPackage("com.androidsample.generalstore");
		activity.setAppWaitActivity("com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
		
		


	}
}