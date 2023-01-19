package stepDefinition;

import com.coinHouse.base.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends Base {


    @Before
    public void beforeEveryTest() throws Exception {
        beforeClass();
    }

    @After
    public void afterEveryTest(){
        afterClass();
    }
}
