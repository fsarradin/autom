package controllers;
 
import models.*;
 
public class Security extends Secure.Security {
	
    static boolean authenticate(String username, String password) {
		User user = User.findByUsername(username);
        return user != null;
    }
	
	static void onDisconnected() {
		Application.index();
	}
    
}
