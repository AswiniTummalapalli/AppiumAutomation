package MobileScripts;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.FormPage;
import PageObjects.ProductCataloguePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;

public class EcommerceErrorValidationTestCases extends BaseTest{
	//FormPage formPage= new FormPage(driver);

	/*
	 * @SuppressWarnings("deprecation")
	 * 
	 * @BeforeMethod public void preSetup() { Activity activity = new
	 * Activity("com.androidsample.generalstore",
	 * "com.androidsample.generalstore.MainActivity");
	 * activity.setAppWaitPackage("com.androidsample.generalstore");
	 * activity.setAppWaitActivity("com.androidsample.generalstore.MainActivity");
	 * driver.startActivity(activity);
	 * 
	 * }
	 */


	/*
	 * @BeforeMethod public void preSetup(){ formPage.setActivity(); }
	 */
	@Test  // Error Validation 
	public void fillFormErrorValidation() throws InterruptedException {
		FormPage formPage= new FormPage(driver);
		Thread.sleep(3000);
		formPage.clickOnLetShop();
		String toastMessage=formPage.geTtoastMessage();
		Assert.assertEquals(toastMessage, "Please enter your name");

	}
	@Test
	public void fillFormHappyPath() throws InterruptedException {
		FormPage formPage= new FormPage(driver);
		formPage.setNamefield("Aswini");
		formPage.genderSelection("female");	
		formPage.countrySelection("Argentina");
		formPage.clickOnLetShop();

	}
}
