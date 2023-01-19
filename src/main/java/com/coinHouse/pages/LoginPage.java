package com.coinHouse.pages;

import com.coinHouse.base.Base;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LoginPage extends Base {



    @AndroidFindBy(xpath= "//android.widget.EditText[@index='1']")
    private MobileElement emailfield;

    @AndroidFindBy (xpath= "//android.widget.EditText[@index='2']")
    private MobileElement passwordFieled;

    @AndroidFindBy (xpath= "//*[@text='Login']")
    private MobileElement loginButton;


    public void fillEmailAndPassword (String email, String password) {
        emailfield.sendKeys(email);
        passwordFieled.sendKeys(password);
    }


    public void clickOnLoginButton () {
        loginButton.click();
    }
}

