package TestSuite;

import java.io.IOException;

import org.testng.annotations.Test;

import Base.Base;
import Pages.ChennaiUsedCars;
import Pages.HondaDetails;
import Pages.LoginPage;

public class AllTest extends Base{
	HondaDetails hd= new HondaDetails(); 
	ChennaiUsedCars chennaiUsedCars = new ChennaiUsedCars();
	LoginPage l= new LoginPage();
	@Test
	public void test() throws InterruptedException, IOException {
		hd.openUrl();
		hd.clickUpcomingBikes();
		hd.selectManufacturer();
		hd.viewMore();
		hd.printDetails();
		chennaiUsedCars.openUrl();
		chennaiUsedCars.clickUsedCars();
		chennaiUsedCars.clickPopularModels();
		l.openUrl();
		l.clickLogin();
		l.clickGoogleSignIn();
		l.captureErrorMessage();
		hd.closeBrowser();
	}
}
