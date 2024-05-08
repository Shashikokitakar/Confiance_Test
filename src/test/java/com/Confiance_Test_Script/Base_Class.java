package com.Confiance_Test_Script;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class Base_Class {
	//The Base_class will be created to launch a web-browsers like Chrome, FireFox, Windows Edge on Windows operating System
	WebDriver driver;
		
	@Parameters("browser")
	@BeforeMethod
	 public void Setup(String browser) 
	 {
		 switch(browser)
		 {
		 case "Chrome":
			 driver=new ChromeDriver();
			 System.out.println("chrome launch");
			 driver.manage().window().maximize();
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			 driver.get("https://playground.mailslurp.com");
			 break;
			  
		 case "firefox":
			 driver=new FirefoxDriver();
			 System.out.println("firefox launch");
			 driver.manage().window().maximize();
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			 driver.get("https://playground.mailslurp.com");
			 break;
		
		 case "edge":
			 driver=new EdgeDriver();
			 driver.manage().window().maximize();
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			 driver.get("https://playground.mailslurp.com");
			 break;
		  
			  default:
			  System.out.println("not recomend");
			  }
		  }
		
		@AfterMethod
		public void Teardown()
		{
			driver.quit();
		}
			 

}
