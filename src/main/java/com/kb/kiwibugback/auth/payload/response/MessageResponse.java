package com.kb.kiwibugback.auth.payload.response;

public class MessageResponse {
	private String message;

	public MessageResponse(final String message) {
	    this.message = message;
	  }

	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}
}
