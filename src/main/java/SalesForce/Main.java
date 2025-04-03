package SalesForce;

import org.testng.annotations.Test;

public class Main{

    @Test
    public void main(){
        SFLogin login = new SFLogin();
        login.setup();
        login.LoginSF();
    }
}
