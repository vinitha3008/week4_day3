package week4.day3;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import testcases.ReadExcel;

public class ExternalDataBaseClass {
	public ChromeDriver driver;
	public String filename;
	public ChromeOptions options;
	
	
	
	@DataProvider
	public String[][] sendData() throws IOException
	{
		return ReadExcel.readData(filename);
	}
	
	@Parameters({"url","name","pwd"})
	@BeforeMethod
	public void preConditions(String URL,String uname,String PWD) {
		//Create a instance of ChromeOptions class
		options = new ChromeOptions();

		//Add chrome switch to disable notification - "**--disable-notifications**"
		options.addArguments("--disable-notifications");
		        
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("username")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(PWD);
		driver.findElement(By.id("Login")).click();
		//driver.switchTo().alert().dismiss();
	}
	/*@AfterMethod
	public void postConditions() {
		driver.close();

	}*/
}


