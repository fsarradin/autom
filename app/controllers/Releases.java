package controllers;

import com.google.gson.*;
import models.*;
import play.Logger;
import play.data.validation.Validation;
import play.mvc.Before;
import play.mvc.Controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Releases extends Controller {
	
	@Before(unless={"show", "list", "tasks"})
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
	
	public static void add(String projectName, String version, String name, String description) {
		if (Validation.hasErrors()) {
			Projects.newRelease(projectName);
		}
		String username = Security.connected();
		User user = User.findByUsername(username);
		Project project = Project.find("owner.login = ? and name = ?", user.login, projectName).first();
		Release release = new Release(name, version, project, description);
		release.save();
        Logger.info("New release: " + release);
		show(user.login, projectName, version);
	}
	
	public static void show(String ownerName, String projectName, String version) {
		Release release = getRelease(ownerName, projectName, version);
		if (release == null) {
			notFound(ownerName + " / " + projectName + " / " + version);
		}
		render(release);
	}

    public static void newTask(String projectName, String version) {
        String username = Security.connected();
        User user = User.findByUsername(username);
        Release release = getRelease(user.login, projectName, version);
        render(release);
    }

    private static Release getRelease(String ownerName, String projectName, String version) {
        return Release.find("project.owner.login = ? and project.name = ? and version = ?",
                ownerName, projectName, version).first();
    }

    public static void tasks(String ownerName, String projectName, String version) {
        Release release = getRelease(ownerName, projectName, version);
        List<TaskWrapper> taskList = new ArrayList<TaskWrapper>(release.tasks.size());
        for (Task task : release.tasks) {
            TaskWrapper wtask = new TaskWrapper(task);
            taskList.add(wtask);
        }
        renderJSON(taskList);
    }

}
