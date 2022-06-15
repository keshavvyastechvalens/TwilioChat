package com.chat.TwilioChat.response;

public class StatusResponse implements RestResponse{
	
	protected int status;
	
	protected String message;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public StatusResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public StatusResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "StatusResponse [status=" + status + ", message=" + message + "]";
	}
	
	
	
}
