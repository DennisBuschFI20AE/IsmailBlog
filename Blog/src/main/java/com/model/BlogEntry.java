package com.model;

import java.time.LocalDate;

public class BlogEntry{

	private String username;
	private LocalDate publishingDate;
	private String title;
	private String text;
	
	
	public BlogEntry() {}
	
		
	public BlogEntry(String username, LocalDate publishingDate, String title, String text) {
		super();
		this.username = username;
		this.publishingDate = publishingDate;
		this.title = title;
		this.text = text;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public LocalDate getPublishingDate() {
		return publishingDate;
	}
	public void setPublishingDate(LocalDate publishingDate) {
		this.publishingDate = publishingDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
}
