import models.User;
import play.Logger;
<<<<<<< HEAD
import play.jobs.Job;
import play.jobs.OnApplicationStart;

import java.util.Date;
=======
import play.Play;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

@OnApplicationStart
public class Bootstrap extends Job {

    @Override
    public void doJob() {
        if (Play.mode == Play.Mode.DEV) {
            if (User.count() == 0) {
                Logger.info("DEV mode: load data...");
                Fixtures.loadModels("test-data.yml");
            }
        }
    }

}
