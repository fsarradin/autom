package controllers;

import models.*;
import play.Logger;
import play.data.validation.Validation;
import play.data.validation.ValidationPlugin;

import java.util.Date;

public class Tasks extends CRUD {
    public static void add(String project, String version, String title, String taskId, String description) {
        if (Validation.hasErrors()) {
            Releases.newTask(project, version);
        }
        String username = Security.connected();
        User user = User.findByUsername(username);
        Logger.info("New task from user:" + user + " project:" + project + " version:" + version);
        Project p = Project.find("owner.login = ? and name = ?", user.login, project).first();
        Release release = Release.find("project.id = ? and version = ?", p.id, version).first();

        Task task = new Task(title, taskId, Status.TODO, p, release, description, new Date(), null);
        task.save();

        Logger.info("New task: " + task);
        Releases.show(user.login, project, version);
    }

    public static void showJson(String ownerName, String projectName, String version, String taskId) {
        Task task = Task.find("release.project.owner.login = ? and release.project.name = ? and release.version = ? and taskId = ?",
                ownerName, projectName, version, taskId).first();
        renderJSON(new TaskWrapper(task));
    }

}
