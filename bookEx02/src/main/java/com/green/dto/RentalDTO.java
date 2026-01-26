package com.green.dto;

import java.time.LocalDate;

public class RentalDTO {
	private String title;
	private String author;
	private String isbn;
	private String user;
	private LocalDate sDate;
	private LocalDate eDate;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public LocalDate getsDate() {
		return sDate;
	}
	public void setsDate(LocalDate sDate) {
		this.sDate = sDate;
	}
	public LocalDate geteDate() {
		return eDate;
	}
	public void seteDate(LocalDate eDate) {
		this.eDate = eDate;
	}
	
}
