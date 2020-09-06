
package com.corona.tracher;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BaseClass extends TestBase {
	WebDriver driver;
	private String confirmed;
	private String recovered;
	private String deaths;

	@BeforeTest
	public void setupTest() {

		System.setProperty("webdriver.chrome.driver", ".\\src\\test\\java\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
	
	@Test
	public void Setup() {
		// System.setProperty("webdriver.gecko.driver","D:\\Automation\\geckodriver.exe");
		// driver=new FirefoxDriver();

		
		// ChromeOptions options = new ChromeOptions();
		// options.addArguments("--disable-extensions");
		// driver = new ChromeDriver(options);

		// System.setProperty("webdriver.ie.Driver",
		// "D:\\Automation\\IEDriverServer.exe");
		// driver = new InternetExplorerDriver();

		driver.navigate().to("https://www.coronatracker.com/country/india/");

		String strConfirmed = driver
				.findElement(By.xpath("//*[@id='__layout']/div/main/div/div/div[1]/div[2]/div/div[2]/div[1]/p[1]"))
				.getText();
		String strRecovered = driver
				.findElement(By.xpath("//*[@id='__layout']/div/main/div/div/div[1]/div[2]/div/div[2]/div[2]/p[1]"))
				.getText();
		String strDeaths = driver
				.findElement(By.xpath("//*[@id='__layout']/div/main/div/div/div[1]/div[2]/div/div[2]/div[3]/p[1]"))
				.getText();

		ConvertStringToInteger data = new ConvertStringToInteger();

		confirmed = data.extractInt(strConfirmed);
		recovered = data.extractInt(strRecovered);
		deaths = data.extractInt(strDeaths);

		System.out.println("Confirmed:" + confirmed);
		System.out.println("Recovered:" + recovered);
		System.out.println("Deaths:" + deaths);

	}

	@Test
	public void getDataFromDB() throws Exception {

		String connectionString = properties.getProperty("connectionString");

		String query = "Execute [Covid].[dbo].[spUpdateCovid19CoronaTracker] India, " + confirmed + ", " + recovered
				+ ", " + deaths + "";
		System.out.println("Query :" + query);

		// String query=" insert into [dbo].[Covid19CoronaTracker]
		// values(1,'India','100','200','300')";
		DBConnection dc = new DBConnection();

		dc.getDeatailsFromDB(query, connectionString);

	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
