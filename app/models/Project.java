package models;

import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	public Date createDate = new Date();
	
	public Project(String name, User owner, String description, Date createDate) {
		this.releases = new ArrayList<Release>();
		this.name = name;
		this.owner = owner;
		this.description = description;
		if (createDate == null) {
			this.createDate = new Date();
		} else {
			this.createDate = createDate;
		}
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
