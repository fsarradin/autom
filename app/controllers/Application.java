package controllers;

import models.Project;
import models.User;
import play.mvc.Controller;

import java.util.List;

public class Application extends Controller {

    public static void index() {
		if (Security.isConnected()) {
			User user = User.findByUsername(Security.connected());
			if (user == null) {
				try {
					Secure.logout();
				} catch (Throwable e) {
					e.printStackTrace();
				}
				render();
			} else {
				List<Project> projects = Project.find("byOwner", user).fetch();
                Users.show(user.login);
			}
		} else {
			render();
		}
    }
	
}