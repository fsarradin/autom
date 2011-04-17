package models;

import java.util.Date;

public class TaskWrapper {
    public String taskId;
    public String title;
    public String description;
    public Status status;
    public String owner;
    public String project;
    public String release;
    public Date startDate;
    public Date endDate;

    public TaskWrapper(Task task) {
        taskId = task.taskId;
        title = task.title;
        description = task.description;
        status = task.status;
        release = task.release.version;
        project = task.release.project.name;
        owner = task.release.project.owner.login;
        startDate = task.startDate;
        endDate = task.endDate;
    }

}
