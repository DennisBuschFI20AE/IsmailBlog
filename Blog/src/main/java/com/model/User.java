package com.model;

public class User {

	private long id;
	private String name;
	private BlogEntry blogEntry;
	
	public User() {
		
	}
	
	public User(long id, String name, BlogEntry blogEntry) {
		super();
		this.id = id;
		this.name = name;
		this.blogEntry = blogEntry;
	}
	
	public User(String name, BlogEntry blogEntry) {
		super();
		this.name = name;
		this.blogEntry = blogEntry;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BlogEntry getBlogEntry() {
		return blogEntry;
	}

	public void setBlogEntry(BlogEntry blogEntry) {
		this.blogEntry = blogEntry;
	}
	
	
}
