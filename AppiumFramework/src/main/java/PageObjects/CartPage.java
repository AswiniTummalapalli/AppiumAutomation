package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import Utils.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions{

	AndroidDriver driver;

	public CartPage(AndroidDriver driver)  {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement goToCart;


	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmountLabel;


	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productsList;


	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	private WebElement termsAndConditionsBtn;

	@AndroidFindBy(id="android:id/button1")
	private WebElement closeBtn;


	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement submitBtn;


	@AndroidFindBy(className = "android.widget.CheckBox")
	private WebElement checkBox;
	public void goToCart() throws InterruptedException {
		goToCart.click();
		Thread.sleep(3000);
	}

	public Double getTotalAMount() {

		return getFormattedAMount(totalAmountLabel.getText());

	}

	public double getProductsTotal() {
		double totalSum=0;
		int count=productsList.size();
		for(int i=0;i<count; i++) {
			String priceText=productsList.get(i).getText();
			Double price=getFormattedAMount(priceText);
			totalSum=totalSum+price;
		}
		return totalSum;

	}
	public Double getFormattedAMount(String amount) {

		Double price= Double.parseDouble(amount.substring(1));

		return price;

	}

	public void acceptTermsAndConditons() {
		longPressGesture(termsAndConditionsBtn);
		closeBtn.click();
	}

	public void submitOrder() {
		checkBox.click();
		submitBtn.click();
	}

}
