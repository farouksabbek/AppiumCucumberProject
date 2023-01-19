package com.coinHouse.base;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public  static AndroidDriver<MobileElement> driver;
    protected FileInputStream inputStream;
    protected Properties prop;


   public Base ( ) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @BeforeTest
    public void beforeClass() throws Exception {

        //To define device properties
        String platformName = "Android";
        String deviceName = "test";
        String platformVersion ="10";

        //To define prop in the file config.properties
        File propFile = new File("src/main/resources/config/config.properties");
        inputStream = new FileInputStream(propFile);
        prop = new Properties();
        prop.load(inputStream);

        //To check OS device to use
        if (platformName.equalsIgnoreCase("Android")) {

            File androidApp = new File(prop.getProperty("androidAppPath"));
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("androidAutomationName"));
            caps.setCapability(MobileCapabilityType.APP, androidApp.getAbsolutePath());
            driver = new AndroidDriver <MobileElement> (new URL(prop.getProperty("appiumServerLink")), caps);
        }

        if(platformName.equalsIgnoreCase("ios")) {
            File iosApp = new File(prop.getProperty("iosAppPath"));
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("iosAutomationName"));
            caps.setCapability(MobileCapabilityType.APP, iosApp.getAbsolutePath());
            driver = new AndroidDriver <MobileElement> (new URL (prop.getProperty("appiumServerLink")), caps);
        }

        //To add implicity wait to all the project
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void afterClass() {
        driver.quit();

    }
}
