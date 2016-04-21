import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.SignUp;
import controllers.routes;
import org.junit.Before;
import org.junit.Test;
import play.libs.Json;
import play.mvc.Result;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.FORBIDDEN;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;

/**
 * Created by mariahflaim on 4/15/16.
 */
public class SignUpTest {
    private String pass1;
    private String pass2;
    private String email;
    private String badEmail;
    private String badPass;
    private String misMatchPass;
    private String name;
    private String mod;
    private Result result;
    private String signUpHTML;
    ObjectNode request;
    JsonNode content;
    @Before
    public void setUp() {

        pass1 = "Password1";
        pass2 = "Password1";
        email = "npatel2@ithaca.edu";
        badEmail="email";
        badPass="password";
        misMatchPass="Password2";
        name="npatel1";
        mod="true";
        signUpHTML="<h1 class=\"text-center\">Sign Up</h1>";
    }
    public void fakeRequest1(String email, String password1,String password2,String name,String mod){
        request = Json.newObject()
                .put("email", email)
                .put("password1", password1)
                .put("password1", password2)
                .put("name", name)
                .put("mod", mod);
        result=callAction(routes.ref.SignUp.validateSignUp(email,password1,password2,name,mod),fakeRequest().withJsonBody(request));
    }
    @Test
    public void test1(){
        signUpTest(1);
    }
    @Test
    public void test2(){
        signUpTest(2);
    }
    @Test
    public void test3(){
        signUpTest(3);
    }
    @Test
    public void test4(){
        signUpTest(4);
    }
    @Test
    public void test5(){
        signUpTest(5);
    }
    @Test
    public void test6(){
        signUpTest(6);
    }
    @Test
    public void test7(){
        signUpTest(7);
    }
    @Test
    public void test8(){
        signUpTest(8);
    }
    @Test
    public void test9(){
        signUpTest(9);
    }

    public void signUpTest(int testNumber) {
        running(fakeApplication(), new Runnable() {

            public void loadPage(){
                request = Json.newObject();
                result=callAction(routes.ref.SignUp.loadSignUp(),fakeRequest().withJsonBody(request));
                assertEquals(OK, status(result));
                assertEquals(true, contentAsString(result).contains(signUpHTML));

            }
            //TO DO: add test to make sure duplicate emails/ usernames are being caught
            public void addUser(){
                request = Json.newObject()
                        .put("email", email)
                        .put("password1", pass1)
                        .put("password1", pass2)
                        .put("name", name)
                        .put("mod", mod);
                result=callAction(routes.ref.SignUp.addUser(email,pass1,name,mod),fakeRequest().withJsonBody(request));
                request = Json.newObject()
                        .put("email", email)
                        .put("password", pass1);
                result=callAction(controllers.routes.ref.LogIn.validateLogIn(email,pass1),fakeRequest().withJsonBody(request));
                assertEquals(OK, status(result));
            }

            public void addUserRepeatSignUpEmail(){
                Result result4;
                //assertEquals(FORBIDDEN, status(result4));
                //assertEquals("repeatE", contentAsString(result4));
                // ^^ asserts are accurate assuming an appropriate query can be made
                //not sure how to write these tests as they rely on checking against existing database entries
            }


            public void addUserRepeatSignUpUname(){
                Result result5;
                //assertEquals(FORBIDDEN, status(result5));
                //assertEquals("repeatU", contentAsString(result5));
                // ^^ asserts are accurate assuming an appropriate query can be made
                //not sure how to write these tests as they rely on checking against existing database entries
            }

            public void addUserRepeatSignUpUnameEmail(){
                Result result6;
                //assertEquals(FORBIDDEN, status(result6));
                //assertEquals("repeatUE", contentAsString(result6));
                // ^^ asserts are accurate assuming an appropriate query can be made
                //not sure how to write these tests as they rely on checking against existing database entries
            }

            public void validateSignUpWithBadEmail() {
                fakeRequest1(badEmail, pass1, pass2, name, mod);
                assertEquals(FORBIDDEN, status(result));
                assertEquals("email", contentAsString(result));
            }


            public void validateSignUpWithBadPassword() {
                fakeRequest1(email, badPass, badPass, name, mod);
                assertEquals(FORBIDDEN, status(result));
                assertEquals("password", contentAsString(result));
            }


            public void validateSignUpWithMistmatchPasswords() {
                 fakeRequest1(email, pass1, misMatchPass, name, mod);
                assertEquals(FORBIDDEN, status(result));
                assertEquals("mismatch", contentAsString(result));
            }

            public void validateSignUpGoodInput() {
                fakeRequest1(email, pass1, pass2, name, mod);
                assertEquals(OK, status(result));
            }

            public void run() {
                Ebean.beginTransaction();
                try {
                    if(testNumber==1){
                        loadPage();
                    }
                    if(testNumber==2){
                        addUser();
                    }
                    if(testNumber==3){
                        addUserRepeatSignUpEmail();
                    }
                    if(testNumber==4){
                        addUserRepeatSignUpUname();
                    }
                    if(testNumber==5){
                       addUserRepeatSignUpUnameEmail();
                    }
                    if(testNumber==6){
                        validateSignUpWithBadEmail();
                    }
                    if(testNumber==7){
                        validateSignUpWithBadPassword();
                    }
                    if(testNumber==8){
                        validateSignUpWithMistmatchPasswords();
                    }
                    if(testNumber==9){
                        validateSignUpGoodInput();
                    }

                } finally {
                    Ebean.rollbackTransaction();
                }
            }

        });
    }

}
