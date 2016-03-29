
import org.junit.*;

import play.mvc.*;

import controllers.SignUp;

import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.FORBIDDEN;
import static play.test.Helpers.*;
import play.mvc.Result;
import static org.junit.Assert.assertEquals;

import static org.fest.assertions.Assertions.assertThat;






/**
 *
 * Simple (JUnit) tests that can call all parts of a play app.
 * If you are interested in mocking a whole application, see the wiki for more details.
 *
 */
public class ApplicationTest {

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
    @Test
    public void validateSignUp(){
        String pass1 = "Password1";
        String pass2 = "Password1";
        String email= "npatel2@ithaca.edu";
        Result result= new SignUp().validateSignUp("myemail", pass1, pass2, "npatel2", "true");
        Result result1= new SignUp().validateSignUp(email, "password", "password", "npatel2", "true");
        Result result2= new SignUp().validateSignUp(email, "Password2", "pass2", "npatel2", "true");
        Result result3= new SignUp().validateSignUp(email,pass1, pass2, "npatel2", "true");

        assertEquals(FORBIDDEN, status(result));
        assertEquals("email", contentAsString(result));
        assertEquals(FORBIDDEN, status(result1));
        assertEquals("password", contentAsString(result1));
        assertEquals(FORBIDDEN, status(result2));
        assertEquals("mismatch", contentAsString(result2));
        assertEquals(OK, status(result3));
    }








}
