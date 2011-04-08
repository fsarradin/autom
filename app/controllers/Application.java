package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

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

	public static void project(String ownerName, String projectName) {
		Project project = Project.find("owner.login = ? and name = ?",
			ownerName, projectName).first();
		Logger.info("get project info: project = " + project + ", owner = " + ownerName);
		if (project == null) {
			notFound(ownerName + " / " + projectName);
		}
		render(project);
	}

	public static void release(String ownerName, String projectName, String version) {
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