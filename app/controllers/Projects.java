package controllers;

import models.Project;
import models.User;
import play.Logger;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.mvc.Before;
import play.mvc.Controller;

import java.util.Date;

public class Projects extends Controller {
	
	@Before(unless={"show", "list"})
	public static void checkConnected() {
		try {
			if (!Security.isConnected()) {
				Secure.login();
			} else {
				if (User.findByUsername(Security.connected()) == null) {
					Secure.logout();
					Secure.login();
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public static void add(@Required String name, String description) {
		if (Validation.hasError("name")) {
			Users.newProject();
		}
		String username = Security.connected();
		User user = User.findByUsername(username);
		Project project = new Project(name, user, description, new Date());
		project.save();
		show(user.login, project.name);
	}
	
	public static void show(String ownerName, String projectName) {
		Project project = Project.find("owner.login = ? and name = ?",
			ownerName, projectName).first();
		Logger.info("get project info: project = " + project + ", owner = " + ownerName);
		if (project == null) {
			notFound(ownerName + " / " + projectName);
		}
		render(project);
	}
	
	public static void list() {
		render();
	}

    public static void newRelease(String projectName) {
        render(projectName);
    }
}
