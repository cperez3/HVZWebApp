package controllers;
import play.mvc.Controller;
import views.html.modPage;
import play.mvc.Result;
/**
 * Created by mariahflaim on 3/28/16.
 */
public class ModPage extends Controller{
    public static Result loadPage(){
        return ok(modPage.render());
    }
    public Result showGameSettings(){
        return ok();
    }
    public Result showAddModPage(){
        return ok();
    }
}

