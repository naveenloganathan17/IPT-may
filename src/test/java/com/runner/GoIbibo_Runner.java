package com.runner;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.baseclass.Base_Class;
import com.pom.GoIbibo_Login;

import java.util.Scanner;

public class GoIbibo_Runner extends Base_Class {
	public static GoIbibo_Login gl;

	@BeforeSuite
	public static void launchBrowser() throws Exception {
		browserLaunch("chrome");
		maximize();
		implicitWait(10);
		getUrl("https://www.goibibo.com");

	}

	@BeforeTest
	public static void login() throws InterruptedException {
		gl = new GoIbibo_Login(driver);
		inputFeed(gl.getPhoneno(), "8668110233");
		actionClick(gl.getContinuebutton());
		Scanner s = new Scanner(System.in);
		String otp = s.next();
		String otp1 = s.next();
		String otp2 = s.next();
		String otp3 = s.next();
		inputFeed(gl.getOtpdigit1(), otp);
		Thread.sleep(2000);
		inputFeed(gl.getOtpdigit2(), otp1);
		Thread.sleep(2000);
		inputFeed(gl.getOtpdigit3(), otp2);
		Thread.sleep(2000);
		inputFeed(gl.getOtpdigit4(), otp3);
//		inputFeed(gl.getEmail(), "naveenloganathan17@gmail.com");
//		javaScriptClick(gl.getSubmit());

	}

	@BeforeClass
	public static void userValidation() throws InterruptedException {
		gl = new GoIbibo_Login(driver);
		Thread.sleep(5000);
		mouseHover(gl.getProfilebutton());
		Thread.sleep(2000);
		mouseHover(gl.getViewprofile());
		actionClick(gl.getViewprofile());
		Thread.sleep(2000);
		String expectedname = "naveenkumar";
		String expectedno = "8668110233";
		String expectedemail = "naveenloganathan17@gmail.com";
		String actualname = gl.getUsername().getText();
		String actualno = gl.getUsermobile().getText();
		String actualemail = gl.getUseremail().getText();
		Assert.assertEquals(actualname, expectedname);
		Assert.assertEquals(actualno, expectedno);
		Assert.assertEquals(actualemail, expectedemail);

	}

	@Parameters("lastname")
	@Test
	public static void aDetails(String lastname) throws InterruptedException {
		gl = new GoIbibo_Login(driver);
		javaScriptClick(gl.getLastname());
		javaScriptSendKeys("Last Name", lastname);
		Thread.sleep(2000);
		javaScriptClick(gl.getMale());
		Thread.sleep(2000);
		javaScriptClick(gl.getDob());
		Thread.sleep(2000);
		dropDown(gl.getYear(), "value", "1992");
		Thread.sleep(2000);
		dropDown(gl.getMonth(), "index", "4");
		Thread.sleep(2000);
		javaScriptClick(gl.getDate());
		isDisplay(gl.getFemale());
		isDisplay(gl.getCancel());
		Thread.sleep(2000);
		javaScriptScrollElement(gl.getSave());
		javaScriptClick(gl.getSave());
		Thread.sleep(5000);

	}

	@DataProvider(name = "address", indices = 0)
	public Object[][] data() {
		Object[][] data = new Object[2][2];
		data[0][0] = "no:4 5th street narasimman nagar, kodungaiyur, chennai";
		data[0][1] = "600118";
		return data;
	}

	@Test(dataProvider = "address")
	public static void billingDetails(String address, String pincode) throws InterruptedException {
		gl = new GoIbibo_Login(driver);
		javaScriptClick(gl.getBillingaddress());
		Thread.sleep(2000);
		javaScriptSendKeys("Billing Address", address);

		javaScriptClick(gl.getPin());
		Thread.sleep(2000);
		javaScriptSendKeys("Pincode", pincode);

		javaScriptClick(gl.getState());
		Thread.sleep(2000);
		javaScriptClick(gl.getTamilnadu());

		javaScriptClick(gl.getSavebutton());
		Thread.sleep(2000);
		isDisplay(gl.getCancelbutton());

	}

	@AfterSuite
	public static void signOut() throws InterruptedException {
		gl = new GoIbibo_Login(driver);
		javaScriptClick(gl.getSignout());
		javaScriptClick(gl.getLogoutbutton());
		Thread.sleep(2000);
		close();

	}

}
