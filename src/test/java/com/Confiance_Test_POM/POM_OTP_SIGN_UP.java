package com.Confiance_Test_POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class POM_OTP_SIGN_UP{
	WebDriver driver;

	public POM_OTP_SIGN_UP(WebDriver driver1) {
		PageFactory.initElements(driver1, this);
		driver = driver1;
	}
	// Locating a webelements for SignUp process.
	@FindBy(xpath = "//*[@id=\"root\"]/div/div/div/form/div[2]/span[2]/a")
	WebElement create_account;

	@FindBy(name = "email")
	WebElement UserName;

	@FindBy(name = "password")
	WebElement Password;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div[3]/span[1]/button")
	WebElement Create_Button;
	
	@FindBy(name="code")
	WebElement Verification_Code;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div/div/div[3]/span[1]/button")
	WebElement confirm_Button;
	
	// Locating a webelements for SignIn process.
	
	@FindBy(name = "username")
	WebElement SignIn_UserName;

	@FindBy(name = "password")
	WebElement SignIN_Password;
	
	@FindBy(name="username")
	WebElement SignIN_Button;
	
	//Method for sign in process
	
	public void Sign_Up(String Email, String password)
	{
		create_account.click();
		UserName.sendKeys(Email);
		Password.sendKeys(password);
		Create_Button.click();
	}
	
	public void Confirm_Sign_Up(String code)
	{
		Verification_Code.sendKeys(code);
		confirm_Button.click();
	}
	
	//Method for Sign In Process
	public void SignIn(String Email, String password)
	{
		SignIn_UserName.sendKeys(Email);
		SignIN_Password.sendKeys(password);
		SignIN_Button.click();
	}
	

}
