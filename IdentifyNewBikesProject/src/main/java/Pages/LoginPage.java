package Pages;

import java.io.IOException;

import org.openqa.selenium.By;

import Base.Base;

public class LoginPage extends Base {

	By login = By.id("des_lIcon");
	By googleSignIn = By.xpath("//*[@id=\"myModal3-modal-content\"]/div[1]/div/div[2]/div[4]/div[2]");
	By email = By.xpath("(//input[@type='email'])");
	By submit = By.xpath("//span[text()='Next']");
	By error = By.xpath("(//div[@class='o6cuMc Jj6Lae'])");

	public void clickLogin() throws IOException // Method to click Login
	{
		logger = report.createTest("Displaying used car");
		try {
			driver.findElement(login).click();
			Thread.sleep(5000);
			String ver = driver.findElement(login).getText();
			Thread.sleep(5000);
			if (ver.contains("Login/Signup"))
				reportPass("Clicked on Login");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void clickGoogleSignIn() throws InterruptedException // Method to click Login
	, IOException
	{
		logger = report.createTest("Error Checking after signup");
		driver.findElement(googleSignIn).click();
		Thread.sleep(5000);
		for (String window : driver.getWindowHandles()) {
			driver.switchTo().window(window);
		}
		driver.manage().window().maximize();
		driver.findElement(email).sendKeys("xyz@gml.com");
		Thread.sleep(5000);
		driver.findElement(submit).click();
		Thread.sleep(5000);
		captureScreenshot();
	}

	public void captureErrorMessage() // Method to capture error message
	{

		System.out.println("*******************************************************");
		System.out.println("              Error Obtained during Signup:");
		System.out.println("*******************************************************");
		String errorMessage = driver.findElement(error).getText();
		System.out.println("Error Message = " + errorMessage);
	}

}
