package models;

import play.data.validation.Email;
import play.data.validation.MaxSize;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import java.util.Date;

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
	
	@Required
	public Date since = new Date();
	
	public User(String login, String password, String email, String fullname, String description, Date since) {
		this.login = login;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
		this.description = description;
		if (since == null) {
			this.since = new Date();
		} else {
			this.since = since;
		}
	}
	
	public String toString() {
		return login;
	}
	
	public static User findByUsername(String username) {
		return User.find("login = ? or email = ?", username, username).first();
	}
	
}
