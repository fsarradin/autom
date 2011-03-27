package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Admin extends Controller {

	public static void login() {
		render();
	}
	
	public static void authenticate(String login, String password) {
		Logger.info("authenticate " + login);
		login();
	}

}
