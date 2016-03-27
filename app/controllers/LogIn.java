package controllers;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;
import views.html.signup;
import javax.inject.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import play.api.OptionalSourceMapper;
import play.api.UsefulException;
import play.api.routing.Router;
import play.http.DefaultHttpErrorHandler;
import play.mvc.Http.*;
import play.*;


/**
 * Created by mariahflaim on 2/18/16.
 */
public class LogIn extends Controller{

    public static Result loadLogIn(){
        return ok(login.render());
    }

    public static Result validateLogIn(String email, String password) {
        String sql =  "select id, name from user where email = " + email + " and password = " + password;

        if (sql) {
            return ok();
        } else {

        }
    }

}

public class ErrorHandler extends DefaultHttpErrorHandler {

    @Inject
    public ErrorHandler(Configuration configuration, Environment environment,
                        OptionalSourceMapper sourceMapper, Provider<Router> routes) {
        super(configuration, environment, sourceMapper, routes);
    }

    protected CompletionStage<Result> onProdServerError(RequestHeader request, UsefulException exception) {
        return CompletableFuture.completedFuture(
                Results.internalServerError("A server error occurred: " + exception.getMessage())
        );
    }

    protected CompletionStage<Result> onForbidden(RequestHeader request, String message) {
        return CompletableFuture.completedFuture(
                Results.forbidden("You're not allowed to access this resource.")
        );
    }
}


