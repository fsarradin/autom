package controllers;

import models.Project;
import models.User;

import java.util.List;

public class Users extends CRUD {
/*	public static void create() {
		render();
	} */

    public static void show(String username) {
        User user = User.findByUsername(username);
        if (user == null) {
            notFound(username);
        }
        List<Project> projects = Project.find("byOwner", user).fetch();
        render(user, projects);
    }

    public static void newProject() {
        render();
    }

}
