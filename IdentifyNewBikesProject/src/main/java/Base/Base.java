package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.ExtentReportManager;

public class Base {
	public static WebDriver driver;
	public static Properties prop;

	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;

	@BeforeSuite
	public void driverSetup() throws InterruptedException {
		prop = new Properties();
		try {
			prop.load(new FileInputStream("src/main/java/Config/Config.properties")); // Loading the properties
		} catch (Exception e) {
			e.printStackTrace();
		}


		//Launching Drivers
		if (prop.getProperty("browserName").matches("chrome")) {
			System.setProperty("WebDriver.driver","Drivers/chromedriver.exe");
			driver = new ChromeDriver(); 
		}
		else if (prop.getProperty("browserName").matches("firefox")) {
			System.setProperty("WebDriver.driver","Drivers/geckodriver.exe");
			driver = new FirefoxDriver(); 
		}
		else {
			System.out.println("Unable to launch driver");
		}
		Thread.sleep(60);
		driver.manage().window().maximize(); // To maximize the window
		System.out.println("Driver Launched:");


	}

	public void openUrl() throws IOException // Method to open URL for smoke test
	{
		logger = report.createTest("Opening Url");
		try {
			driver.get(prop.getProperty("url"));

			reportPass("URL opened, URL is :" + prop.getProperty("url"));
		} catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	// Function to show the failed test cases in the report
	public void reportFail(String report) throws IOException {
		logger.log(Status.FAIL, report);
		captureScreenshot();
	}

	// Function to show the passed test cases in the report
	public void reportPass(String report) {
		logger.log(Status.PASS, report);
	}


	public void captureScreenshot() throws IOException // Method to take screenshot
	{
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot screen=(TakesScreenshot)driver;
		File source=screen.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File("Screenshot/"+timeStamp+"_screenshot.png"));
		System.out.println("Screenshot is successfully captured");
	}


	@AfterSuite
	public void closeBrowser() // method to close the browser
	{
		driver.quit(); // To close the browser
		report.flush(); 
		try {
			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
		} catch (Exception e) {
		}
	}
}
