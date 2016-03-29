package controllers;

import play.db.DB;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;

import java.sql.ResultSet;
import java.sql.SQLException;

//import javax.inject.*;
//import play.api.UsefulException;
//import play.api.routing.Router;
//import play.http.DefaultHttpErrorHandler;


/**
 * Created by mariahflaim on 2/18/16.
 */
public class LogIn extends Controller{

    public static Result loadLogIn(){
        return ok(login.render());
    }


    public static Result validateLogIn(String email, String password) {
       String sql = "SELECT id, user_name FROM user WHERE email = '" + email + "' AND password = '" + password +"'";
        //sql query is not being case sensitive :O
        System.out.println(sql);
    //https://www.playframework.com/documentation/2.1.3/JavaEbean
        java.sql.Connection conn = DB.getConnection();
        try {
            //http://stackoverflow.com/questions/18546223/play-framework-execute-raw-sql-at-start-of-request

            java.sql.Statement stmt = conn.createStatement();
            try {
               ResultSet rst = stmt.executeQuery(sql);
                try {
                    while ( rst.next() ) {
                        int numColumns = rst.getMetaData().getColumnCount();
                        //this is where to throw an error I guess -- if one or more is invalid, it just sends back an empty result
                        for ( int i = 1 ; i <= numColumns ; i++ ) {
                            //this is where we'd take the stuff in each column and put it places!!!!!!!
                            // Column numbers start at 1.
                            // Also there are many methods on the result set to return
                            //  the column as a particular type. Refer to the Sun documentation
                            //  for the list of valid conversions.
                            System.out.println( "COLUMN " + i + " = " + rst.getObject(i) );
                        }
                    }
                } finally {
                    try { rst.close(); } catch (Throwable ignore) { /* Propagate the original exception
instead of this one that you may want just logged */ }
                }
            } finally {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        /*RawSql rawSql =
                RawSqlBuilder
                        .parse(sql)
                        .columnMapping("id",  "user.id")
                        .columnMapping("name",  "user.name")
                        .create();

        Query<User> query = Ebean.find(User.class);
        query.setRawSql(rawSql);*/

        if (true) {
            return ok();
        } else {

        }
        return ok();

    }

}

/*public class ErrorHandler extends DefaultHttpErrorHandler {

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
}*/


