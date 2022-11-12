package com.jobs.model;

import java.io.Serializable;

/**
 * @author Aung Thiha Tun
 *
 */
public class MessageDTD implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String from;
	private String text;
	
	public MessageDTD() {
		
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
	
}
