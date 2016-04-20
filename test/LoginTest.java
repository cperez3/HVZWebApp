import controllers.routes;

import org.junit.Before;
import org.junit.Test;
import play.mvc.Result;



import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.FORBIDDEN;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.status;
import com.avaje.ebean.Ebean;
import static play.test.Helpers.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.api.test.FakeRequest;
import play.Logger;
import play.libs.Json;
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
    private Result result;
    private String logInHTML;
    ObjectNode request;
    JsonNode content;


    @Before
    public void setUp() {

        pass = "Password1";
        badPass1 = "password1";
        badPass2="eoifewjpefjfe";
        email = "npatel2@ithaca.edu";
        isMod="false";
        uname="nikhil";
        badEmail="badEmail@email.com";
        logInHTML="<h1 class=\"text-center\">Login</h1>";

    }
    public void fakeRequest1(String email, String password){
        request = Json.newObject()
                .put("email", email)
                .put("password", password);
        result=callAction(controllers.routes.ref.LogIn.validateLogIn(email,password),fakeRequest().withJsonBody(request));


    }
    @Test
    public void test1(){
        loginTest(1);
    }
    @Test
    public void test2(){
        loginTest(2);
    }
    @Test
    public void test3(){
        loginTest(3);
    }
    @Test
    public void test4(){
        loginTest(4);
    }@Test
    public void test5(){
        loginTest(5);
    }
    public void loginTest(int testNumber) {
        running(fakeApplication(), new Runnable() {

            public void setUp2() {
                request = Json.newObject()
                        .put("email",email)
                        .put("password1", pass)
                        .put("name", uname)
                        .put("mod", isMod);
                result=callAction(controllers.routes.ref.SignUp.addUser(email,pass,uname,isMod),fakeRequest().withJsonBody(request));
                assertEquals(OK,status(result));

            }

            public void loadPage() {
                request = Json.newObject();
                result=callAction(controllers.routes.ref.LogIn.loadLogIn(),fakeRequest().withJsonBody(request));
                assertEquals(OK, status(result));
                assertEquals(true, contentAsString(result).contains(logInHTML));


            }
            public void validateLoginBadEmail() {

                fakeRequest1(badEmail,pass);
               // Result result4 = new controllers.LogIn().validateLogIn(badEmail, pass);
                System.out.println(result);
                assertEquals(FORBIDDEN, status(result));
                assertEquals("not user", contentAsString(result));
            }

            public void validateLoginBadPassword() {
                fakeRequest1(email,badPass2);
                assertEquals(FORBIDDEN, status(result));
                assertEquals("not user", contentAsString(result));
            }

            public void validateLoginBadPasswordCase() {
                fakeRequest1(email,badPass1);
                assertEquals(FORBIDDEN, status(result));
                assertEquals("not user", contentAsString(result));
            }

            public void validateLoginGoodAccount() {
                fakeRequest1(email,pass);
                assertEquals(OK, status(result));
            }
            public void deleteUser(){
                request = Json.newObject()
                    .put("email",email);
                result=callAction(routes.ref.GamePage.deleteUser(email),fakeRequest().withJsonBody(request));
            }
            @Override
            public void run() {
                Ebean.beginTransaction();
                try {
                    if(testNumber==1){
                        setUp2();
                        loadPage();
                    }
                    if(testNumber==2){
                        validateLoginBadEmail();

                    } if(testNumber==3){
                        validateLoginBadPassword();

                    } if(testNumber==4){
                        validateLoginBadPasswordCase();
                    } if(testNumber==5){
                        validateLoginGoodAccount();
                        //deleteUser();
                    }


                } finally {
                    Ebean.rollbackTransaction();
                }
            }

        });
    }


}
