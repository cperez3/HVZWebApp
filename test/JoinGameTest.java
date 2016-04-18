import org.junit.Before;
import org.junit.Test;
import play.mvc.Controller;
import controllers.JoinGame;
import play.mvc.Result;

import static org.junit.Assert.assertEquals;
import static play.test.Helpers.*;
import static play.test.Helpers.status;
import static play.mvc.Http.Status.FORBIDDEN;
/**
 * Created by mariahflaim on 4/15/16.
 */
public class JoinGameTest extends Controller{
    private String code;
    private String stringNotLoggedIn;
    private String stringLoggedIn;
    @Before
    public void setUp() {

        stringNotLoggedIn="<h1 class=\"text-center\">Login</h1>";
    }
    @Test
    public void loadPageNotLoggedIn(){

        Result result1= new controllers.JoinGame().loadPage();
        assertEquals(true, contentAsString(result1).contains(stringNotLoggedIn));
    }
    @Test
    public void loadPageLoggedIn(){


    }
    @Test
    public void verifyCodeWithValidCode(){

    }
    @Test
    public void verifyCodeWithInvalidCode(){

    }
}
