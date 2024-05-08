package com.Confiance_Test_Script;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.Confiance_Test_POM.POM_OTP_SIGN_UP;
import com.mailslurp.apis.InboxControllerApi;
import com.mailslurp.apis.WaitForControllerApi;
import com.mailslurp.clients.ApiClient;
import com.mailslurp.clients.ApiException;
import com.mailslurp.models.Email;
import com.mailslurp.models.InboxDto;

import okhttp3.OkHttpClient;

//In that test case we extends a Base_Class which contains the a method for opening the application in browser. 
public class OTP_SIGN_UP extends Base_Class 
{

	/*We Importing a page object class which contains the WebElements required for the Sign Up process and also 
	the methods which will be used for sign_Up*/
	
	//creating a object of POM class
	POM_OTP_SIGN_UP p1;

	private static ApiClient mailslurpClient;
	private static final Long TIMEOUT_MILLIS = 30000L;
	private static InboxDto inbox;
	private static Email email;
	private static String confirmation_Code;
	private static final boolean UNREAD_ONLY = true;
	
	String Password="Testing@12345";

	@Test
	void Sign_Up_Process() throws ApiException, InterruptedException {

		p1 = new POM_OTP_SIGN_UP(driver);

		// set timeout for the http client
		OkHttpClient httpClient = new OkHttpClient.Builder().connectTimeout(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
				.writeTimeout(TIMEOUT_MILLIS, TimeUnit.SECONDS).readTimeout(TIMEOUT_MILLIS, TimeUnit.SECONDS).build();

		mailslurpClient = com.mailslurp.clients.Configuration.getDefaultApiClient();
		mailslurpClient.setApiKey("5bf93a29e5924c5905e2e4bb6b07da81b4064f1cddc4e5f65f0139d79925c0d0");
		mailslurpClient.setConnectTimeout(TIMEOUT_MILLIS.intValue());
		mailslurpClient.setWriteTimeout(TIMEOUT_MILLIS.intValue());
		mailslurpClient.setReadTimeout(TIMEOUT_MILLIS.intValue());

		// Below line of code will be created a temprory Email address for signup process
		InboxControllerApi inboxControllerApi = new InboxControllerApi(mailslurpClient);
		inbox = inboxControllerApi.createInboxWithDefaults();
		String email_Address = inbox.getEmailAddress();

		//Calling a Sign_Up method from page object class
		/*The Below method allow user to insert a mail address and set password for Sign_Up process 
		and after click on create account button the otp will send to the given mail address.*/
		
		 p1.Sign_Up(email_Address,Password);

		// by using the below line of code we can extract a verification code from the inbox of temprory mail account.

		WaitForControllerApi waitForControllerApi = new WaitForControllerApi(mailslurpClient);
		email = waitForControllerApi.waitForLatestEmail(inbox.getId(), TIMEOUT_MILLIS, UNREAD_ONLY, null, null, null,
				null);

		Pattern p = Pattern.compile(".*Your Demo verification code is (\\d+).*");
		Matcher matcher = p.matcher(email.getBody());

		// find first occurrence and extract
		assertTrue(matcher.find());
		confirmation_Code = matcher.group(1);

		System.out.println(confirmation_Code);
		
		//The below method will alow user to insert a OTP in confirmation code box  and click on the confirm button it will redirect to sign in page

		p1.Confirm_Sign_Up(confirmation_Code);
		
		//The Below Method will allow User to Sign In After the Sign In process completed
		
		Thread.sleep(3000);
		
		p1.SignIn(email_Address, Password);
		
		Thread.sleep(3000);
	}
}
