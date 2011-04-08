package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;
import play.data.validation.*;

@Entity
public class Task extends Model {
	
	@Required
	public String title;
	
	@Required
	public String taskId;
	
	@Required
	public Status status = Status.TODO;
	
	@Required
	@ManyToOne
	public Project project;
	
	@Required
	@ManyToOne
	public Release release;
	
	@Lob
	@MaxSize(1000)
	public String description;
	
	@Required
	public Date startDate = new Date();
	
	public Date endDate;
	
	public Task(String title, String taskId, Status status, Project project,
			Release release, String description, Date startDate, Date endDate) {
		this.title = title;
		this.taskId = taskId;
		this.status = status;
		this.project = project;
		this.release = release;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String toString() {
		return taskId;
	}
}
