import controllers.ModPage;
import org.junit.Before;
import org.junit.Test;
import play.mvc.Result;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.status;

/**
 * Created by mariahflaim on 4/15/16.
 */
public class ModPageTest {
    private String htmlAfterCreate;
    private String htmlAfterEnd;
    @Before
    public void setUp() {


        htmlAfterCreate="<h5 class=\"text-center\">Game Code: Game Code Here </h5>";
        htmlAfterEnd= "<h5 class=\"text-center\">You are not in a game right now. </h5>";


    }
    @Test
    public void endGame() {
        Result result7 = new controllers.ModPage().endGame();
        Result result8= new controllers.ModPage().loadPage();
        assertEquals(true, contentAsString(result8).contains(htmlAfterEnd));
    }
    @Test
    public void createGame() {
        Result result = new ModPage().createGame();
        Result result1=new ModPage().loadPage();
        assertEquals(OK,status(result1));
        assertEquals(true,contentAsString(result1).contains(htmlAfterCreate));
    }

}
