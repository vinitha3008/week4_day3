package week4.day3;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AppLauncher extends ProjectSpecificMethod {
	
	@Parameters({"company_name","Description"})
	@Test
	public void appLauncher(String c_name,String Desc) throws InterruptedException
	{
		//click on toogle menu button
		driver.findElement(By.xpath(" //div[@class='slds-icon-waffle']")).click();
		//click on view all
		WebElement viewall = driver.findElement(By.xpath("(//button[@class='slds-button'])[2]"));
		Actions a=new Actions(driver);
		a.moveToElement(viewall).click().perform();
		
		
		//click on legal entities
		driver.findElement(By.xpath("//div[@class='slds-col slds-scrollable slds-p-horizontal_large']//ul//li[73]")).click();

		//click on new button in new window
		driver.findElement(By.xpath("//a[@class='forceActionLink']")).click();
		//send company name
		driver.findElement(By.xpath("(//input[@class='slds-input'])[3]")).sendKeys(c_name);
		//send description
		driver.findElement(By.xpath("(//textarea[@class='slds-textarea'])[2]")).sendKeys(Desc);
		//click on status dropdown icon
		WebElement status_dd =driver.findElement(By.xpath("//button[@class='slds-combobox__input slds-input_faux fix-slds-input_faux slds-combobox__input-value']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", status_dd);
		
		Thread.sleep(2000);
		//click on active option
		driver.findElement(By.xpath("//div[@class='slds-listbox slds-listbox_vertical slds-dropdown slds-dropdown_fluid slds-dropdown_left slds-dropdown_length-with-icon-7']//lightning-base-combobox-item[2]")).click();
		
		//click on save button
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		
		//click on legal entity link in alert window
		driver.findElement(By.xpath("//a[text()='Legal Entity Name']")).click();
		driver.findElement(By.xpath("(//input[@class='slds-input'])[2]")).sendKeys("vinitha");
		
		//get the error msg disdlayed below the name text box
		String text = driver.findElement(By.xpath("//label[text()='Legal Entity Name']/following::div[contains(@class, 'slds-form-element__help')]")).getText();
		System.out.println("Errror message:"+text);
		
		//click on save button
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		
		List<WebElement> messages = driver.findElements(By.xpath(("//div[contains(@class, 'forceToastMessage')]//span[contains(@class, 'toastMessage')]")));

		if (!messages.isEmpty()) {
		    System.out.println("Success Message: " + messages.get(0).getText());
		} else {
		    System.out.println("No success message found.");
		}
		
			
	}
}

