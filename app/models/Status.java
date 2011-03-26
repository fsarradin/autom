package models;

public enum Status {
	TODO("TODO"),
	IN_PROGRESS("IN PROGRESS"),
	DONE("DONE");
	
	private final String name;
	
	private Status(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
