package com.chat.TwilioChat.requestdto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MessageDto {

	@NotEmpty(message = "CONVERSATION ID CAN NOT BE EMPTY")
	private String conversationId;
	@NotEmpty(message = "MESSAGE CONTENT CAN NOT BE EMPTY")
	private String messageContant;
	public MessageDto(String conversationId, String messageContant) {
		super();
		this.conversationId = conversationId;
		this.messageContant = messageContant;
	}
	public MessageDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MessageDto [conversationId=" + conversationId + ", messageContant=" + messageContant + "]";
	}
	public String getConversationId() {
		return conversationId;
	}
	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}
	public String getMessageContant() {
		return messageContant;
	}
	public void setMessageContant(String messageContant) {
		this.messageContant = messageContant;
	}
	
}
