package com.salil.todoist.entities;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Label implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7933316349803592775L;
	
	private String name;
	private long id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

}
