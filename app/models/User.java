package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;
import play.data.validation.*;

@Entity
public class User extends Model {
	
	@Required
	public String login;
	
	@Password
	@Required
	public String password;
	
	@Email
	@Required
	public String email;
	
	public String fullname;
	
	@Lob
	@MaxSize(1000)
	public String description;
	
	public User(String login, String password, String email, String fullname, String description) {
		this.login = login;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
		this.description = description;
	}
	
	public String toString() {
		return login;
	}

}
