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
		User user = User.find("byLogin", ownerName).first();
		Project project = Project.find("byOwnerAndName", user, projectName).first();
		Logger.info("get project info: project = " + project + ", owner = " + user);
		render(user, project);
	}
	
}