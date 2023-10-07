package MobileScripts;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	
	public AppiumDriverLocalService service;
	public AndroidDriver driver;

	@BeforeClass (alwaysRun=true)
	public void androidLaunchTest() throws IOException{
		Properties prop= new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\data.properties");
		prop.load(fis);
		String ipAddress=System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		System.out.println(ipAddress);
		String port=prop.getProperty("port");
		String deviceName=prop.getProperty("deviceName");
		String devicePlatform=prop.getProperty("devicePlatform");

		//startAppiumServer(ipAddress, Integer.parseInt(port));
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName(devicePlatform);
		options.setDeviceName(deviceName);
		//options.setApp(System.getProperty("user.dir")+"\\src\\test\\resources\\ApiDemos-debug.apk");
		options.setApp(System.getProperty("user.dir")+"\\src\\test\\resources\\General-Store.apk");

		options.setChromedriverExecutable("C:\\Users\\aswin\\eclipse-workspace\\AppiumFramework\\src\\main\\resources\\driverfiles\\chromedriver.exe");
		driver= new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass (alwaysRun=true)
	public void tearDown() {
		driver.quit();
		//service.stop();
	}
	
	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
		service= new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\aswin\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
		.withIPAddress(ipAddress).usingPort(port).build();
		service.start();
		return service;
	}
	
	public String getScreenShotPath(String testCaseName, AppiumDriver driver) throws IOException {
		
		File src=driver.getScreenshotAs(OutputType.FILE);
		String destination=System.getProperty("user.dir")+"//reports"+testCaseName+".png";
		FileUtils.copyFile(src, new File(destination));
		return destination;
		
		
	}
}
