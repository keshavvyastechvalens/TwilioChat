package com.chat.TwilioChat.response;

public class DataResponse extends StatusResponse  {
	protected Object data;
	

	public DataResponse(int status, String message, Object data) {
		super.status = status;
		this.message = message;
		this.data = data;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}


	public DataResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
