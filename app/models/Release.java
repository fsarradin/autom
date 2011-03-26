package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;
import play.data.validation.*;

@Entity
public class Release extends Model {
	
	@Required
	public String name;
	
	@Required
	public String version;
	
	@Required
	@ManyToOne
	public Project project;
	
	@Lob
	@MaxSize(1000)
	public String description;
	
	public Release(String name, String version, Project project, String description) {
		this.name = name;
		this.version = version;
		this.project = project;
		this.description = description;
	}
	
	public String toString() {
		return project.name + "_v" + version;
	}
}
