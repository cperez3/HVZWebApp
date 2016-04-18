import models.BaseModelTest;
import org.junit.Before;
import org.junit.Test;
import play.mvc.Result;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.FORBIDDEN;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.status;
import com.avaje.ebean.Ebean;

import static play.test.Helpers.*;

/**
 * Created by mariahflaim on 4/15/16.
 */
public class LoginTest {
    private String pass;
    private String badPass1;
    private String badPass2;
    private String email;
    private String uname;
    private String isMod;
    private String badEmail;
   // private Result result;


    @Before
    public void setUp() {

        pass = "Password1";
        badPass1 = "password1";
        badPass2="eoifewjpefjfe";
        email = "npatel2@ithaca.edu";
        isMod="false";
        uname="nikhil";
        badEmail="badEmail@email.com";
        //result=new controllers.SignUp().addUser(email,pass,uname,isMod);




    }
    //WORKING ON IT
    @Test
    public void loginTest() {
        running(fakeApplication(), new Runnable() {
            public void loadPage() {

            }

            public void validateLoginBadEmail() {
                Result result4 = new controllers.LogIn().validateLogIn(badEmail, pass);
                assertEquals(FORBIDDEN, status(result4));
                assertEquals("not user", contentAsString(result4));
            }

            public void validateLoginBadPassword() {
                Result result4 = new controllers.LogIn().validateLogIn(email, badPass2);
                assertEquals(FORBIDDEN, status(result4));
                assertEquals("not user", contentAsString(result4));
            }

            public void validateLoginBadPasswordCase() {
                Result result4 = new controllers.LogIn().validateLogIn(email, badPass1);
                assertEquals(FORBIDDEN, status(result4));
                assertEquals("not user", contentAsString(result4));
            }


            public void validateLoginGoodAccount() {

                Result result5 = new controllers.LogIn().validateLogIn(email, pass);
                assertEquals(OK, status(result5));
            }
            @Override
            public void run() {
                Ebean.beginTransaction();
                try {
                    validateLoginBadEmail();
                    validateLoginBadPassword();
                    validateLoginBadPasswordCase();
                    validateLoginGoodAccount();
                } finally {
                    Ebean.rollbackTransaction();
                }
            }

        });
    }


}
