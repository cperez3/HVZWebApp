/**
 * Created by mariahflaim on 4/15/16.
 */
package models;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Ebean;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import play.api.db.DBApi;
import play.api.db.evolutions.Evolution;
import play.api.db.evolutions.Evolutions;
import play.test.FakeApplication;
import play.test.Helpers;
import scala.collection.Seq;
import scala.collection.immutable.HashMap;
import scala.collection.immutable.Map;
import java.io.IOException;

import com.avaje.ebean.config.ServerConfig;


import java.io.IOException;


/**
 * Base class for Model testing
 */
public class BaseModelTest {
    public static FakeApplication app;
    public static String createDdl = "";
    public static String dropDdl = "";

    @BeforeClass
    public static void startApp() throws IOException {
       /* Map<String, String> settings = new HashMap<String, String>();
        settings.put("db.default.driver", "org.h2.Driver");
        settings.put("db.default.user", "sa");
        settings.put("db.default.password", "");
        settings.put("db.default.url", "jdbc:h2:mem:play-test-351881363;MODE=MySQL"); // TODO: use config for url
        settings.put("db.default.jndiName", "DefaultDS");
        app = Helpers.fakeApplication(settings);
        Helpers.start(app);

        databaseTester = new JndiDatabaseTester("DefaultDS");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(play.Play.application()
                .resourceAsStream("/resources/dataset.xml"));
        databaseTester.setDataSet(expectedDataSet);
        databaseTester.onSetup();*/
    }

    @AfterClass
    public static void stopApp() {
        Helpers.stop(app);
    }

    @Before
    public void createCleanDb() {
        Ebean.execute(Ebean.createCallableSql(dropDdl));
        Ebean.execute(Ebean.createCallableSql(createDdl));
    }
}