package com.comsatel.todoback.models;

import org.springframework.lang.Nullable;

public class Thing {
    private int id;
    private String description;
    private boolean state;
	@Nullable
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    public boolean getState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}

}
