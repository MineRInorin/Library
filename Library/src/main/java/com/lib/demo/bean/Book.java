package com.lib.demo.bean;

public class Book {

	@Override
	public String toString() {
		return "Book [name=" + name + ", id=" + id + ", author=" + author + ", total=" + total + ", type=" + type + "]";
	}

	private String name;
	private int id;
	private String author;
	private int total;
	private String type;
//	private Set<User> borrowedUsers;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
