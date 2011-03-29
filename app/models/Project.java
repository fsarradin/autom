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
	
	@OneToMany(mappedBy="project")
	public List<Release> releases;
	
	public Project(String name, User owner, String description) {
		this.releases = new ArrayList<Release>();
		this.name = name;
		this.owner = owner;
		this.description = description;
	}
	
	public Status getStatus() {
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
	
	public int getTaskCount() {
		int count = 0;
		for (Release release : releases) {
			count += release.tasks.size();
		}
		return count;
	}
	
	public String toString() {
		return name;
	}
}
