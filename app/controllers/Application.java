package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
		List<Project> projects = Project.findAll();
        render(projects);
    }
	
	public static void user(String userName) {
		User user = User.find("byLogin", userName).first();
		List<Project> projects = Project.find("byOwner", user).fetch();
		render(user, projects);
	}

	public static void project(String ownerName, String projectName) {
		Project project = Project.find("owner.login = ? and name = ?",
			ownerName, projectName).first();
		Logger.info("get project info: project = " + project + ", owner = " + ownerName);
		render(project);
	}

	public static void release(String ownerName, String projectName, String version) {
		Release release = Release.find("project.owner.login = ? "
			+ " and project.name = ? "
			+ " and version = ?",
			ownerName, projectName, version).first();
		render(release);
	}
	
}