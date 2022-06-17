package com.chat.TwilioChat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import com.chat.TwilioChat.model.Users;

@Entity
public class SoloChat {
	@Id
	@GeneratedValue(generator = "conversations_generator")
	@SequenceGenerator(
			name = "conversations_generator",
			sequenceName = "conversations_sequence",
			initialValue = 1
			)
	private long id;
	
	@OneToOne
	private Users sender;
	
	@OneToOne
	private Users receiver;
	
	private String conversationId;
	
	private String senderParticipantId;

	private String receiverParticipantId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Users getSender() {
		return sender;
	}

	public void setSender(Users sender) {
		this.sender = sender;
	}

	public Users getReceiver() {
		return receiver;
	}

	public void setReceiver(Users receiver) {
		this.receiver = receiver;
	}

	public String getConversationId() {
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

	public String getSenderParticipantId() {
		return senderParticipantId;
	}

	public void setSenderParticipantId(String senderParticipantId) {
		this.senderParticipantId = senderParticipantId;
	}

	public String getReceiverParticipantId() {
		return receiverParticipantId;
	}

	public void setReceiverParticipantId(String receiverParticipantId) {
		this.receiverParticipantId = receiverParticipantId;
	}

	public SoloChat(long id, Users sender, Users receiver, String conversationId, String senderParticipantId,
			String receiverParticipantId) {
		super();
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.conversationId = conversationId;
		this.senderParticipantId = senderParticipantId;
		this.receiverParticipantId = receiverParticipantId;
	}

	public SoloChat() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String toString() {
		return "SoloChat [conversationId=" + conversationId + ", id=" + id + ", receiver=" + receiver
				+ ", receiverParticipantId=" + receiverParticipantId + ", sender=" + sender + ", senderParticipantId="
				+ senderParticipantId + "]";
	}
}
