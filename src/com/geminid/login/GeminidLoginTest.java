package com.geminid.login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Ritu Sameer
 *
 */

public class GeminidLoginTest {
	/**
	* 
	*/
	public static final String CSV_PATH = "C:\\GeminidLogin\\GeminidAutomation\\resources\\LoginData.csv";
	public static final String BASE_URL = "https://www.geminidsystems.com//home//";
	private static WebDriver driver = null;

	/**
	* 
	*/
	private void loginTest() {
		try {

			// Creating a new instance for Chrome driver
			driver = new ChromeDriver();

			// Navigate to URL
			driver.get(BASE_URL);

			// Maximize browser window
			driver.manage().window().maximize();

			// Click on Login Button
			driver.findElement(By.id("login_button")).click();

			// Select Production button from the pop up
			WebElement button = driver.findElement(By.xpath("//*[@id='logInForm_container']/button[contains(text(),'PRODUCTION')]"));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			button.click();

			// Read the data from the csv file
			String data = "";
			BufferedReader reader = new BufferedReader(new FileReader(new File(CSV_PATH)));

			while ((data = reader.readLine()) != null) {
				String[] loginDetails = data.split(",");
				// Enter UserName
				driver.findElement(By.id("username")).sendKeys(loginDetails[0]);

				// Enter Password
				driver.findElement(By.id("password")).sendKeys(loginDetails[1]);
				System.out.println("Login[userName="+loginDetails[0]+",Password="+loginDetails[1]+"]");
			}
			// Click on 'Log In' button
			driver.findElement(By.id("Login")).click();
			reader.close();

			//Wait for few seconds to load the page
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			
			// Close the browser.
			driver.close();

		} catch (Exception e) {
			System.out.println("Something went wrong!! " + e.toString());
		}
	}

	public static void main(String[] args) {
		// Creating class object
		GeminidLoginTest geminidLoginTest = new GeminidLoginTest();
		geminidLoginTest.loginTest();
	}

}
