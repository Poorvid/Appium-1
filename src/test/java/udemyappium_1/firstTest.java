package udemyappium_1;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class firstTest {

	//WebDriver driver;
	AndroidDriver<AndroidElement> driver;

	@BeforeTest

	public void setup() throws MalformedURLException , IOException{
		
		
		
	Runtime.getRuntime().exec("C:\\Users\\poorvid\\eclipse-workspace\\udemyappium_1\\src\\main\\resources\\startemulator.bat");
	
	 Runtime runtime = Runtime.getRuntime();
	    try {
	        runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 127.0.0.1 -p 4723 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\"");
	        Thread.sleep(10000);
	    } catch (IOException | InterruptedException e) {
	        e.printStackTrace();
	    }
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// capabilities.setCapability("BROWSER_NAME","Android");
		capabilities.setCapability("avd","sampleemulator_2");
		capabilities.setCapability("Version", "8.0");
		capabilities.setCapability("deviceName", "emulator-5554");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", "com.android.calculator2");
		capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
		//driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

	}

	@Test
	public void testCal() throws Exception {

		driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.view.ViewGroup[1]/android.widget.Button[4]")).click();
		driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"plus\"]")).click();
		driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.view.ViewGroup[1]/android.widget.Button[4]")).click();
		driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"equals\"]")).click();
		WebElement result=driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView"));
		assert result.getText().equals("8"):"actual value is :"+result.getText()+" did not match with expected result :8";

		
	}
	@AfterMethod
	public void teardown()
	{
		driver.closeApp();
		 Runtime runtime = Runtime.getRuntime();
		    try {
		        runtime.exec("taskkill /F /IM node.exe");
		        runtime.exec("taskkill /F /IM cmd.exe");
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	}
	
	
}
