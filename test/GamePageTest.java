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
    private String htmlAfterEnd;
    private String pass;
    private String badPass1;
    private String badPass2;
    private String email;
    private String uname;
    private String gCode;
    private String isMod;
    private String isNotMod;
    private String badEmail;
    private Result result;
    ObjectNode request;
    private String logInHTML;
    private String gamePageHTML;
    private String joinGameHTML;
    private String inGameHTML;
    private String settingsHTML;
    JsonNode content;
    @Before
    public void setUp() {

        pass = "TestPassword1";
        email = "testEmail@test.test";
        badPass1 = "notPassword1";
        badPass2="notPassword2";
        isNotMod="false";
        isMod="true";
        uname="testUser";
        badEmail="badEmail@test.test";
        gCode="12345";
        joinGameHTML="<h1 class=\"text-center\">Join Game</h1>";
        gamePageHTML="<div class=\"col-xs-12\"><h3>testUser</h3></div>";
        logInHTML="<h1 class=\"text-center\">Login</h1>";
        htmlAfterEnd= "<h5 class=\"text-center\">You are not in a game right now. </h5>";
        inGameHTML=" <!-- Tab panes -->\n" +
                "              <div class=\"tab-content\">\n" +
                "                <div role=\"tabpanel\" class=\"tab-pane fade in active\" id=\"modStuff\">";
       settingsHTML="<title>Settings</title>";

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
    public void test4(){gamePageTest(4);}
    @Test
    public void test5(){gamePageTest(5);}

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
    @Test
    public void test10(){
        gamePageTest(10);
    }
    @Test
    public void test11(){
        gamePageTest(11);
    }
    @Test
    public void test12(){
        gamePageTest(12);
    }
    @Test
    public void test13(){
        gamePageTest(13);
    }
    @Test
    public void test14(){
        gamePageTest(14);
    }
    @Test
    public void test15(){
        gamePageTest(15);
    }
    @Test
    public void test16(){
        gamePageTest(16);
    }
    @Test
    public void test17(){
        gamePageTest(17);
    }
    @Test
    public void test18(){
        gamePageTest(18);
    }
    @Test
    public void test19(){
        gamePageTest(19);
    }
    @Test
    public void test20(){
        gamePageTest(20);
    }
    @Test
    public void test21(){
        gamePageTest(21);
    }
    @Test
    public void test22(){
        gamePageTest(22);
    }
    @Test
    public void test23(){
        gamePageTest(23);
    }
    @Test
    public void test24(){
        gamePageTest(24);
    }
    @Test
    public void test25(){
        gamePageTest(25);
    }
    @Test
    public void test26(){
        gamePageTest(26);
    }
    @Test
    public void test27(){
        gamePageTest(27);
    }

    public void gamePageTest(int testNumber) {

        running(fakeApplication(), new Runnable() {
            public void loadPageLoggedInRegularInGame(){
                request = Json.newObject();
                result=callAction(routes.ref.GamePage.loadPage(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode",gCode).withSession("is_mod",isNotMod));
                assertEquals(OK,status(result));
                assertEquals(true, contentAsString(result).contains(gamePageHTML));
            }
            public void loadPageLoggedInRegularNoGame(){
                request = Json.newObject();
                //Http.Cookie cookie= new Http.Cookie("email",emailIn,1,"ds","dffsd",true,true);
                result=callAction(routes.ref.GamePage.loadPage(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode"," ").withSession("is_mod",isNotMod));
                assertEquals(FORBIDDEN,status(result));
                assertEquals(true, contentAsString(result).contains(joinGameHTML));
            }
            public void loadPageNotLoggedIn(){
                request = Json.newObject();
                result=callAction(routes.ref.GamePage.loadPage(),fakeRequest().withJsonBody(request));
                assertEquals(FORBIDDEN,status(result));
                assertEquals(true, contentAsString(result).contains(logInHTML));
            }
            public void loadPageLoggedInModNoGame(){
                request = Json.newObject();
                result=callAction(routes.ref.GamePage.loadPage(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode"," ").withSession("is_mod",isMod));
                assertEquals(OK,status(result));
                assertEquals(true, contentAsString(result).contains(htmlAfterEnd));

            }
            public void loadPageLoggedInModInGame(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.loadPage(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode",gCode).withSession("is_mod",isMod));
                assertEquals(OK,status(result));
                assertEquals(true, contentAsString(result).contains(gamePageHTML));

            }
            public void loadSettingsModInGame(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.loadSettings(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode",gCode).withSession("is_mod",isMod));
                assertEquals(OK,status(result));
                assertEquals(true, contentAsString(result).contains(inGameHTML));

            }
            public void loadSettingsModNoGame(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.loadSettings(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode"," ").withSession("is_mod",isMod));
                assertEquals(OK,status(result));
                assertEquals(true, contentAsString(result).contains(htmlAfterEnd));


            }
            public void loadSettingsRegularInGame(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.loadSettings(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode",gCode).withSession("is_mod",isNotMod));
                assertEquals(OK,status(result));
                assertEquals(true, contentAsString(result).contains(settingsHTML));

            }
            public void loadSettingsRegularNoGame(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.loadSettings(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode"," ").withSession("is_mod",isNotMod));
                assertEquals(OK,status(result));
                assertEquals(true, contentAsString(result).contains(settingsHTML));

            }
            public void loadSettingsNotLoggedIn(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.loadSettings(),fakeRequest().withJsonBody(request));
                assertEquals(FORBIDDEN,status(result));
                assertEquals(true, contentAsString(result).contains(logInHTML));
            }
            public void logOutLoggedIn(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.logOut(),fakeRequest().withJsonBody(request).withSession("uname",uname));
                assertEquals(OK,status(result));
                assertEquals(true, contentAsString(result).contains(logInHTML));

            }
            public void logOutNotLoggedIn(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.logOut(),fakeRequest().withJsonBody(request));
                assertEquals(FORBIDDEN,status(result));
                assertEquals(true, contentAsString(result).contains(logInHTML));

            }
            public void deactivateAccountLoggedIn(){
                request = Json.newObject()
                        .put("email", email)
                        .put("password1", pass)
                        .put("password1", pass)
                        .put("name", uname)
                        .put("mod", isMod);
                result=callAction(routes.ref.SignUp.addUser(email,pass,uname,isMod),fakeRequest().withJsonBody(request));

                request = Json.newObject();

                result=callAction(routes.ref.GamePage.deactivateAccount(),fakeRequest().withJsonBody(request).withSession("email",email));
                assertEquals(OK,status(result));


                request = Json.newObject()
                        .put("email", email)
                        .put("password", pass);
                result=callAction(controllers.routes.ref.LogIn.validateLogIn(email,pass),fakeRequest().withJsonBody(request));
                assertEquals(FORBIDDEN, status(result));
                assertEquals("not user", contentAsString(result));
            }
            public void deactivateAccountNotLoggedIn(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.deactivateAccount(),fakeRequest().withJsonBody(request));
                assertEquals(FORBIDDEN,status(result));
                assertEquals(true, contentAsString(result).contains(logInHTML));


            }

            //Moderator functions

            public void createGameMod() {
                /*request = Json.newObject();

                result=callAction(routes.ref.GamePage.createGame(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode"," ").withSession("is_mod",isMod));
                assertEquals(OK,status(result));
                assertEquals(true,contentAsString(result).contains(gamePageHTML));*/
            }
            public void createGameNotMod() {
                /*request = Json.newObject();

                result=callAction(routes.ref.GamePage.createGame(),fakeRequest().withJsonBody(request));
                assertEquals(FORBIDDEN,status(result));
                assertEquals(true,contentAsString(result).contains(logInHTML));*/

            }
            public void endGameNotMod(){
                /*request = Json.newObject();

                result=callAction(routes.ref.GamePage.endGame(),fakeRequest().withJsonBody(request));
                assertEquals(FORBIDDEN,status(result));
                assertEquals(true,contentAsString(result).contains(logInHTML));*/

            }
            public void endGameMod() {
                /*request = Json.newObject();

                result=callAction(routes.ref.GamePage.endGame(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode",gCode).withSession("is_mod",isMod));
                assertEquals(OK,status(result));
                assertEquals(true,contentAsString(result).contains(htmlAfterEnd));*/

            }
            public void changeStatusModLoggedIn(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.changeStatus(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode"," ").withSession("is_mod",isMod));
                assertEquals(FORBIDDEN,status(result));
                assertEquals(true,contentAsString(result).contains(joinGameHTML));

            }
            public void changeStatusNotLoggedIn(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.changeStatus(),fakeRequest().withJsonBody(request));
                assertEquals(FORBIDDEN,status(result));
                assertEquals(true,contentAsString(result).contains(logInHTML));

            }
            public void changeStatusNotMod(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.changeStatus(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode",gCode).withSession("is_mod",isNotMod));
                assertEquals(FORBIDDEN,status(result));
                assertEquals(true,contentAsString(result).contains(logInHTML));

            }
            public void getPlayersMod(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.getPlayers(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode",gCode).withSession("is_mod",isMod));
                assertEquals(OK,status(result));
                //TO DO: assert equals that is equal to the see players page with names in it


            }
            public void getPlayersModNoGame(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.getPlayers(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode"," ").withSession("is_mod",isMod));
                assertEquals(FORBIDDEN,status(result));
                assertEquals(true,contentAsString(result).contains(logInHTML));

            }
            public void getPlayersNotMod(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.getPlayers(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode"," ").withSession("is_mod",isNotMod));
                assertEquals(FORBIDDEN,status(result));
                assertEquals(true,contentAsString(result).contains(logInHTML));

            }
            public void getPlayersNotLoggedIn(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.getPlayers(),fakeRequest().withJsonBody(request));
                assertEquals(FORBIDDEN,status(result));
                assertEquals(true,contentAsString(result).contains(logInHTML));

            }
            public void addModerator(){
                request = Json.newObject()
                        .put("email", email)
                        .put("password1", pass)
                        .put("password1", pass)
                        .put("name", uname)
                        .put("mod", isNotMod);
                result=callAction(routes.ref.SignUp.addUser(email,pass,uname,isNotMod),fakeRequest().withJsonBody(request));

                request = Json.newObject()
                        .put("code", gCode);
                result=callAction(routes.ref.JoinGame.verifyCode(gCode),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode"," "));


                request = Json.newObject()
                        .put("email",email);

                result=callAction(routes.ref.GamePage.addModerator(email),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode",gCode).withSession("is_mod",isMod));
                assertEquals(OK,status(result));

                deleteUser(email);



            }
            public void addModeratorWhoIsNotInGame(){
                request = Json.newObject()
                        .put("email", email)
                        .put("password1", pass)
                        .put("password1", pass)
                        .put("name", uname)
                        .put("mod", isNotMod);
                result=callAction(routes.ref.SignUp.addUser(email,pass,uname,isNotMod),fakeRequest().withJsonBody(request));
                request = Json.newObject()
                        .put("email",email);

                result=callAction(routes.ref.GamePage.addModerator(email),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode",gCode).withSession("is_mod",isMod));
                assertEquals(FORBIDDEN,status(result));
                assertEquals("user not in game",contentAsString(result));

                deleteUser(email);


            }
            public void addModeratorNoGame(){
                request = Json.newObject()
                        .put("email",email);

                result=callAction(routes.ref.GamePage.addModerator(email),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode"," ").withSession("is_mod",isMod));
                assertEquals(FORBIDDEN,status(result));
                assertEquals(true,contentAsString(result).contains(logInHTML));

            }
            public void addModeratorNotLoggedIn(){
                request = Json.newObject()
                    .put("email",email);

                result=callAction(routes.ref.GamePage.addModerator(email),fakeRequest().withJsonBody(request));
                assertEquals(FORBIDDEN,status(result));
                assertEquals(true,contentAsString(result).contains(logInHTML));

            }

            public void deleteUser(String emailIn){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.deactivateAccount(),fakeRequest().withJsonBody(request).withSession("email",emailIn));
            }

            public void run() {
                Ebean.beginTransaction();
                try {
                    if(testNumber==1){
                        loadPageLoggedInRegularInGame();
                    }
                    if(testNumber==2){
                        loadPageLoggedInRegularNoGame();

                    }
                    if(testNumber==3){
                        loadPageNotLoggedIn();

                    }
                    if(testNumber==4){
                        loadPageLoggedInModNoGame();

                    }
                    if(testNumber==5){
                        loadPageLoggedInModInGame();
                    }
                    if(testNumber==6){
                        loadSettingsModInGame();
                    }
                    if(testNumber==7) {
                        loadSettingsModNoGame();
                    }
                    if(testNumber==8){
                        loadSettingsRegularInGame();
                    }
                    if(testNumber==9){
                        loadSettingsRegularNoGame();
                    }
                    if(testNumber==10){
                        loadSettingsNotLoggedIn();

                    }
                    if(testNumber==11){
                        logOutLoggedIn();

                    }
                    if(testNumber==12){
                        logOutNotLoggedIn();

                    }
                    if(testNumber==13){
                        deactivateAccountLoggedIn();
                    }
                    if(testNumber==14){
                        deactivateAccountNotLoggedIn();
                    }
                    if(testNumber==15){
                        createGameMod();

                    }
                    if(testNumber==16){
                        endGameNotMod();

                    }
                    if(testNumber==17){
                        endGameMod();

                    }
                    if(testNumber==18){
                        changeStatusModLoggedIn();

                    }
                    if(testNumber==19){

                        changeStatusNotLoggedIn();
                    }
                    if(testNumber==20){
                        changeStatusNotMod();

                    }
                    if(testNumber==21){
                        getPlayersMod();

                    }
                    if(testNumber==22){
                        getPlayersModNoGame();

                    } if(testNumber==23){
                       getPlayersNotMod();

                    } if(testNumber==24){

                        getPlayersNotLoggedIn();
                    } if(testNumber==25){
                        addModerator();


                    } if(testNumber==26){
                        addModeratorWhoIsNotInGame();


                    } if(testNumber==27){
                        addModeratorNoGame();


                    }if(testNumber==28){
                        addModeratorNotLoggedIn();
                    }

                } finally {
                    Ebean.rollbackTransaction();
                }
            }

        });
    }

}

