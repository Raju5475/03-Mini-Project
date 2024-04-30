package com.raju.Dto;

import lombok.Data;

@Data
public class QuoteDto {

	private String text;
	private String author;
	
	
	 public String getText() {
	        return text;
	    }

	    public void setText(String text) {
	        this.text = text;
	    }
}
