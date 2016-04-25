import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.routes;
import org.junit.Before;
import org.junit.Test;
import play.libs.Json;
import play.mvc.Result;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.FORBIDDEN;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.*;
import static play.test.Helpers.fakeRequest;

/**
 * Created by mariahflaim on 4/15/16.
 */
public class GamePageTest {
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

    @Test
    public void test1(){
        gamePageTest(1);
    }
    @Test
    public void test2(){
        gamePageTest(2);
    }
    @Test
    public void test3(){
        gamePageTest(3);
    }
    @Test
    public void test4(){
        gamePageTest(4);
    }
    @Test
    public void test5(){
        gamePageTest(5);
    }
    @Test
    public void test6(){
        gamePageTest(6);
    }
    @Test
    public void test7(){
        gamePageTest(7);
    }
    @Test
    public void test8(){
        gamePageTest(8);
    }
    @Test
    public void test9(){
        gamePageTest(9);
    }

    public void gamePageTest(int testNumber) {

        running(fakeApplication(), new Runnable() {
            public void loadPageLoggedInRegularInGame(){

            }
            public void loadPageLoggedInRegularNoGame(){
            }
            public void loadPageNotLoggedIn(){

            }
            public void loadPageLoggedInModNoGame(){

            }
            public void loadPageLoggedInModInGame(){

            }
            public void loadSettingsMod(){

            }
            public void loadSettingsRegular(){

            }
            public void loadSettingsNotLoggedIn(){

            }
            public void logOutLoggedIn(){

            }
            public void logOutNotLoggedIn(){

            }
            public void deactivateAccountLoggedIn(){

            }
            public void deactivateAccountNotLoggedIn(){

            }
            public void deleteUser(String emailIn){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.deactivateAccount(),fakeRequest().withJsonBody(request).withSession("email",emailIn));
            }

            public void run() {
                Ebean.beginTransaction();
                try {
                    if(testNumber==1){

                    }
                    if(testNumber==2){


                    }
                    if(testNumber==3){

                    }
                    if(testNumber==4){

                    }
                    if(testNumber==5){

                    }
                    if(testNumber==6){

                    }
                    if(testNumber==7) {
                    }
                    if(testNumber==8){

                    }
                    if(testNumber==9){

                        deleteUser(email);
                    }

                } finally {
                    Ebean.rollbackTransaction();
                }
            }

        });
    }

}

