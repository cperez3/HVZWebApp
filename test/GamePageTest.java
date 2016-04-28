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
    private String email2;
    private String uname2;
    private String gCode;
    private String isMod;
    private String isNotMod;
    private String badEmail;
    private String isActive;
    private String team;
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
        email2= "testEmail2@test.test";
        badPass1 = "notPassword1";
        badPass2="notPassword2";
        isActive="1";
        team="human";
        isNotMod="false";
        isMod="true";
        uname="testUser";
        uname2="testUser2";
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
    @Test
    public void test28(){
        gamePageTest(28);
    }
    @Test
    public void test29(){
        gamePageTest(29);
    }
    @Test
    public void test30(){
        gamePageTest(30);
    }
    @Test
    public void test31(){
        gamePageTest(31);
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

                result=callAction(routes.ref.GamePage.loadSettings(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode",gCode).withSession("is_mod",isMod).withSession("is_active",isActive).withSession("type",team));
                assertEquals(OK,status(result));
                assertEquals(true, contentAsString(result).contains(inGameHTML));

            }
            public void loadSettingsModNoGame(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.loadSettings(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode"," ").withSession("is_mod",isMod).withSession("is_active",isActive).withSession("type",team));
                assertEquals(OK,status(result));
                assertEquals(true, contentAsString(result).contains(htmlAfterEnd));


            }
            public void loadSettingsRegularInGame(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.loadSettings(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode",gCode).withSession("is_mod",isNotMod).withSession("is_active",isActive).withSession("type",team));
                assertEquals(OK,status(result));
                assertEquals(true, contentAsString(result).contains(settingsHTML));

            }
            public void loadSettingsRegularNoGame(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.loadSettings(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode"," ").withSession("is_mod",isNotMod).withSession("is_active",isActive).withSession("type",team));
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
            public void changeModStatusModLoggedInNotOnly(){
                request = Json.newObject()
                        .put("email", email)
                        .put("password1", pass)
                        .put("password1", pass)
                        .put("name", uname)
                        .put("mod", isMod);
                result=callAction(routes.ref.SignUp.addUser(email,pass,uname,isMod),fakeRequest().withJsonBody(request));

                request = Json.newObject()
                        .put("code", gCode);
                result=callAction(routes.ref.JoinGame.verifyCode(gCode),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode"," "));

                request = Json.newObject()
                        .put("email", email2)
                        .put("password1", pass)
                        .put("password1", pass)
                        .put("name", uname2)
                        .put("mod", isMod);
                result=callAction(routes.ref.SignUp.addUser(email2,pass,uname2,isMod),fakeRequest().withJsonBody(request));

                request = Json.newObject()
                        .put("code", gCode);
                result=callAction(routes.ref.JoinGame.verifyCode(gCode),fakeRequest().withJsonBody(request).withSession("uname",uname2).withSession("gCode"," "));

                request = Json.newObject();

                result=callAction(routes.ref.GamePage.verifyChangeModStatus(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode",gCode).withSession("is_mod",isMod));
                assertEquals(OK,status(result));
                assertEquals(true,contentAsString(result).contains(gamePageHTML));
                deleteUser(email2);


            }
            public void changeModStatusModLoggedInOnly(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.verifyChangeModStatus(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode",gCode).withSession("is_mod",isMod));
                assertEquals(FORBIDDEN,status(result));
                assertEquals("You are the only moderator, please add another moderator before switching your moderator status.",contentAsString(result));

                deleteUser(email);
            }
            public void changeModStatusModNoGame(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.verifyChangeModStatus(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode"," ").withSession("is_mod",isMod));
                assertEquals(FORBIDDEN,status(result));
                assertEquals(true,contentAsString(result).contains(joinGameHTML));

            }
            public void changeModStatusNotLoggedIn(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.verifyChangeModStatus(),fakeRequest().withJsonBody(request));
                assertEquals(FORBIDDEN,status(result));
                assertEquals(true,contentAsString(result).contains(logInHTML));

            }
            public void changeModStatusNotMod(){
                request = Json.newObject();

                result=callAction(routes.ref.GamePage.verifyChangeModStatus(),fakeRequest().withJsonBody(request).withSession("uname",uname).withSession("gCode",gCode).withSession("is_mod",isNotMod));
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
                    //TESTS MUST BE IN THIS EXACT ORDER FOR THEM TO WORK AND DATBASE MUST BE BLANK TO START
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
                        createGameNotMod();

                    }
                    if(testNumber==17){
                        endGameNotMod();

                    }
                    if(testNumber==18){
                        endGameMod();

                    }
                    if(testNumber==19){
                        changeModStatusModLoggedInNotOnly();

                    }
                    if(testNumber==20){

                        changeModStatusModLoggedInOnly();
                    }
                    if(testNumber==21){
                        changeModStatusModNoGame();

                    } if(testNumber==22){
                        changeModStatusNotLoggedIn();

                    }
                    if(testNumber==23){
                       changeModStatusNotMod();

                    }

                    if(testNumber==24){
                        getPlayersMod();

                    }
                    if(testNumber==25){
                        getPlayersModNoGame();

                    } if(testNumber==26){
                       getPlayersNotMod();

                    } if(testNumber==27){

                        getPlayersNotLoggedIn();
                    } if(testNumber==28){
                        addModerator();


                    } if(testNumber==29){
                        addModeratorWhoIsNotInGame();


                    } if(testNumber==30){
                        addModeratorNoGame();


                    }if(testNumber==31){
                        addModeratorNotLoggedIn();
                    }

                } finally {
                    Ebean.rollbackTransaction();
                }
            }

        });
    }

}

