package stepDefinition;

import com.coinHouse.base.Base;
import com.coinHouse.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;




import org.openqa.selenium.By;


public class LoginSteps extends Base {

    LoginPage loginPage;

    @Given("I go to the app")
    public void openSite()  {
        System.out.println("-------------------------Test-Start-------------------------");
    }

    @When("I add {string} and {string}")
    public void iAddAnd(String email, String password) throws InterruptedException {
        loginPage = new LoginPage();
        loginPage.fillEmailAndPassword(email,password);
        Thread.sleep(2000);
    }


    @And("click on login")
    public void clickOnLogin() {
        System.out.println("-------------------------Step2-------------------------");
        loginPage.clickOnLoginButton();
    }



}

