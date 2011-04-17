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
				renderTemplate("Application/user.html", user, projects);
			}
		} else {
			render();
		}
    }
	
	public static void user(String username) {
		User user = User.findByUsername(username);
		if (user == null) {
			notFound(username);
		}
		List<Project> projects = Project.find("byOwner", user).fetch();
		render(user, projects);
	}

}