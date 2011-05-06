import models.User;
import play.Logger;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

import java.util.Date;

@OnApplicationStart
public class Bootstrap extends Job {

    @Override
    public void doJob() {
        if (User.count() == 0) {
            User admin = new User("admin", "admin", "", "Administrator", "Administrator", new Date());
            admin.save();
            Logger.info("admin account created");
        }
    }

}
