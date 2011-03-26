package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;
import play.data.validation.*;

@Entity
public class Project extends Model {
	
	@Required
	public String name;
	
	@Required
	@ManyToOne
	public User owner;
	
	@Lob
	@MaxSize(1000)
	public String description;
	
	public Project(String name, User owner, String description) {
		this.name = name;
		this.owner = owner;
		this.description = description;
	}
	
	public Status getStatus() {
		List<Release> releases = Release.find("byProject", this).fetch();
		if (releases.size() == 0) {
			return Status.TODO;
		}
		for (Release release : releases) {
			if (release.getStatus() != Status.DONE) {
				return Status.IN_PROGRESS;
			}
		}
		return Status.DONE;
	}
	
	public String toString() {
		return name;
	}
}
