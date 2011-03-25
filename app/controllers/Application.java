package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
		List<Project> projects = Project.findAll();
        render(projects);
    }

}