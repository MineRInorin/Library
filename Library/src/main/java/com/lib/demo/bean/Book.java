package com.lib.demo.bean;

import java.util.Set;

public class Book {
	private String name;
	private int id;
	private int total;
	private Set<User> borrowedUsers;

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

	public Set<User> getBorrowedUsers() {
		return borrowedUsers;
	}

	public void setBorrowedUsers(Set<User> borrowedUsers) {
		this.borrowedUsers = borrowedUsers;
	}
}
