package Utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions {
	AndroidDriver driver;
	
	public AndroidActions(AndroidDriver driver) {

		this.driver= driver;
	}
	
	public void scrollToText(String text) {

		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));

	}

	public void swipeGesture(WebElement ele, String direction) {

		((JavascriptExecutor)driver).executeScript("mobile : swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement)ele).getId(),
				"direction", direction,
				"percent", 0.75
				));	

	}
	
	public void longPressGesture(WebElement ele) {
		
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", 
				ImmutableMap.of("elementId", ((RemoteWebElement)ele).getId(),"duration", 2000));
		
	}
	
}
