package MobileScripts;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.FormPage;
import PageObjects.ProductCataloguePage;
import io.appium.java_client.AppiumBy;

public class EcommerceTestCases extends BaseTest{

	@Test(dataProvider="getData", groups= {"Smoke"})
	public void fillForm(String name, String gender, String country) throws InterruptedException {
		FormPage formPage= new FormPage(driver);
		ProductCataloguePage products= new ProductCataloguePage(driver);
		CartPage cart=new CartPage(driver);
	
		formPage.setNamefield(name);
		formPage.genderSelection(gender);	
		formPage.countrySelection(country);
		formPage.clickOnLetShop();
		products.addtoCartByIndex(0);
		products.addtoCartByIndex(0);
		cart.goToCart();
		double ProductsTotal=cart.getProductsTotal();
		double displayedTotalAmount=cart.getTotalAMount();
		Assert.assertEquals(ProductsTotal, displayedTotalAmount);
		cart.acceptTermsAndConditons();
		cart.submitOrder();
	}


	/*
	 * @BeforeMethod (alwaysRun=true) public void preSetup(){ formPage.setActivity(); }
	 */
	@DataProvider
	public  Object[][] getData() {
		return new Object[][] {{"Aswini", "female", "Argentina"}};
		//{"Ashy", "female", "Argentina"}};

	}

}
