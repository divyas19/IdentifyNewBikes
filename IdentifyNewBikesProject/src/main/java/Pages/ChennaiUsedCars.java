package Pages;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Base.Base;

public class ChennaiUsedCars extends Base
{
	By ucars=By.linkText("Used Cars");
	By chennai=By.linkText("Chennai");
	By lclose=By.id("alternate-login-close");
	By popularmodels=By.xpath("//ul[contains(@class,'usedCarMakeModelList')]");
	By list=By.tagName("li");

	public void clickUsedCars() throws IOException  // Method to click used_cars
	{
		logger = report.createTest("Used Cars and Popular Model");
		try{
			driver.findElement(By.xpath("//a[contains(text(),'Used Cars')]"));
			System.out.println("Moved to Used Cars");
			Actions act=new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Used Cars')]"))).perform();
			//Selecting Chennai
			driver.findElement(By.xpath("//span[contains(text(),'Chennai')]")).click();
			System.out.println("Clicked on Chennai");

			String usedCars = driver.findElement(By.xpath("//h1[@id='usedcarttlID']")).getText();
			if (usedCars.contains("Used Cars in Chennai")) 
				reportPass("Used Cars in chennai are displayed");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}

	}
	public void clickPopularModels() throws IOException // Method to click popular_models
	{
		logger = report.createTest("Obtaining Popular Models");
		try {
			WebElement w1=driver.findElement(popularmodels);
			System.out.println("*******");
			System.out.println("            Popular Used Cars in Chennai:");
			System.out.println("*******");
			List<WebElement> ls= w1.findElements(list);
			for(int i=0;i<10;i++)
			{
				System.out.println(ls.get(i).getText());
			}

			reportPass("Popular models are printed");
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}


}
