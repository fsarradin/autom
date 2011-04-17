package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Account extends Controller {

	public static void settings() {
		render();
	}
	
	public static void update(String fullname, String email, String description) {
		User user = getCurrentUser();
		user.fullname = fullname;
		user.email = email;
		user.description = description;
		user.save();
		settings();
	}
	
	public static User getCurrentUser() {
		String username = Security.connected();
		return User.findByUsername(username);
	}
	
}
