package com.in28minutes.springboot.learnjpaandhibernate.course;

public class Course {
	private long id;
	private String name;
	private String author;
	
	// Constructor
	public Course() {
		
	}
	
	
	public Course(long id, String name, String author) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
	}


	// toString
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", author=" + author + "]";
	}
	
	// getters

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}
	
	
	
}
