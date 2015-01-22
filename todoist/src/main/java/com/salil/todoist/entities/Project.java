package com.salil.todoist.entities;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Project implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4574260542146095749L;
	private int id;
	private String name;
	
	public Project(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
