
import org.junit.*;

import play.mvc.*;

import controllers.SignUp;
import controllers.ModPage;
import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.FORBIDDEN;

import static play.test.Helpers.*;
import play.mvc.Result;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import models.Game;

import static org.fest.assertions.Assertions.assertThat;

/**
 *
 * Simple (JUnit) tests that can call all parts of a play app.
 * If you are interested in mocking a whole application, see the wiki for more details.
 *
 */
public class ApplicationTest {
    private String pass1;
    private String pass2;
    private String email;

    public String id;
    public String htmlAfterCreate;
    public String htmlAfterEnd;
    public String gameCode;
    public ArrayList humanUsers;
    public ArrayList zombieUsers;
    public ArrayList deletedUsers;
    public ArrayList moderatorMessages;
    public ArrayList humanMessages;
    public ArrayList zombieMessages;


    @Before
    public void setUp() {

        pass1 = "Password1";
        pass2 = "Password1";
        email = "npatel2@ithaca.edu";
        id = "103";
        gameCode = "2055";
        humanUsers = new ArrayList();
        zombieUsers = new ArrayList();
        deletedUsers = new ArrayList();
        moderatorMessages = new ArrayList();
        humanMessages = new ArrayList();
        zombieMessages = new ArrayList();
        htmlAfterCreate="<h5 class=\"text-center\">Game Code: Game Code Here </h5>";
        htmlAfterEnd= "<h5 class=\"text-center\">You are not in a game right now. </h5>";
        humanUsers.add("Evan");
        zombieUsers.add("Nikhil");
        deletedUsers.add("Ryan");
        moderatorMessages.add("Let the Games begin");
        humanMessages.add("I need backup");
        zombieMessages.add("Humans spotted");

    }

    //initial testing
    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

    @Test
    public void renderTemplate() {
        Content html = views.html.index.render("Your new application is ready.");
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Your new application is ready.");
    }

    //Login and SignUp Testing
    @Test
    public void ifEmailIsFalseForbiddenIsReturned() {
        Result result = new SignUp().validateSignUp("myemail", pass1, pass2, "npatel2", "true");
        assertEquals(FORBIDDEN, status(result));
        assertEquals("email", contentAsString(result));
    }

    @Test
    public void ifPasswordIsNotGoodForbiddenIsReturned() {
        Result result1 = new SignUp().validateSignUp(email, "password", "password", "npatel2", "true");
        assertEquals(FORBIDDEN, status(result1));
        assertEquals("password", contentAsString(result1));
    }

    @Test
    public void ifPasswordsDoNotMatchForbiddenIsReturned() {
        Result result2 = new SignUp().validateSignUp(email, "Password2", "pass2", "npatel2", "true");
        assertEquals(FORBIDDEN, status(result2));
        assertEquals("mismatch", contentAsString(result2));
    }
    @Test
    public void createGame() {
        Result result = new ModPage().createGame();
        Result result1=new ModPage().loadPage();
        assertEquals(OK,status(result1));
        assertEquals(true,contentAsString(result1).contains(htmlAfterCreate));
    }

    @Test
    public void ifAllSignUpConditionsAreMetOKIsReturned() {
        Result result3 = new SignUp().validateSignUp(email, pass1, pass2, "npatel2", "true");
        assertEquals(OK, status(result3));
    }

    @Test
    public void ifAccountDoesNotExistForbiddenIsResturned() {
        Result result4 = new controllers.LogIn().validateLogIn(email, pass1);
        assertEquals(FORBIDDEN, status(result4));
        assertEquals("not user", contentAsString(result4));
    }

    @Test
    public void ifAccountExistsOKIsResturned() {
        Result result5 = new controllers.LogIn().validateLogIn(email, pass1);
        assertEquals(OK, status(result5));
    }

    @Test
    public void endGame() {
        Result result7 = new controllers.ModPage().endGame();
        Result result8= new controllers.ModPage().loadPage();
        assertEquals(true, contentAsString(result8).contains(htmlAfterEnd));
    }

    /*public static Result addGame(String idIn, String gameCodeIn, ArrayList humanUsersIn,
                                 ArrayList zombieUsersIn, ArrayList deletedUsersIn, ArrayList moderatorMessagesIn,
                                 ArrayList humanMessagesIn, ArrayList zombieMessagesIn) {
    }*/


}




