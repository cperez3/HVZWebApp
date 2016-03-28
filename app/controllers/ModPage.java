package controllers;
import play.mvc.Controller;
import views.html.inGameModSettings;
import views.html.noGameModSettings;
import play.mvc.Result;
/**
 * Created by mariahflaim on 3/28/16.
 */
public class ModPage extends Controller{
    public static Result loadPage(){
        //TO DO: check if moderator is in a game or not then load page based off if they are
        return ok(noGameModSettings.render());
    }

}

