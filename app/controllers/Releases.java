package controllers;

import models.Project;
import models.Release;
import models.User;
import play.Logger;
import play.data.validation.Validation;
import play.mvc.Before;
import play.mvc.Controller;

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
	
	public static void add(String project, String version, String name, String description) {
		if (Validation.hasErrors()) {
			Projects.newRelease(project);
		}
		String username = Security.connected();
		User user = User.findByUsername(username);
        Logger.info("New release from user:" + user + " project:" + project);
		Project p = Project.find("owner.login = ? and name = ?", user.login, project).first();
		Release release = new Release(name, version, p, description);
		release.save();
        Logger.info("New release: " + release);
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

    public static void newTask(String projectName, String version) {
        String username = Security.connected();
        User user = User.findByUsername(username);
        Project project = Project.find("owner.login = ? and name = ?", user.login, projectName).first();
        Release release = Release.find("project.id = ? and version = ?", project.id, version).first();

        render(project, release);
    }

}
