package controllers;

import models.Project;
import models.Release;
import models.User;

import play.*;
import play.data.validation.*;
import play.mvc.*;

public class Releases extends Controller {
	
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
	
	public static void create(String projectName) {
		render(projectName);
	}
	
	public static void add(String project, String version, String name, String description) {
		if (Validation.hasErrors()) {
			create(project);
		}
		String username = Security.connected();
		User user = User.findByUsername(username);
		Project p = Project.find("owner.login = ? and name = ?", user.login, project).first();
		Release release = new Release(name, version, p, description);
		release.save();
		show(user.login, project, version);
	}
	
	public static void show(String ownerName, String projectName, String version) {
		Release release = Release.find("project.owner.login = ? "
			+ " and project.name = ? "
			+ " and version = ?",
			ownerName, projectName, version).first();
		if (release == null) {
			notFound(ownerName + " / " + projectName + " / " + version);
		}
		render(release);
	}
}
