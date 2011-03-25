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
	
	public String toString() {
		return name;
	}
}
