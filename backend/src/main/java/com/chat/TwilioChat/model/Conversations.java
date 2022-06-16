package com.chat.TwilioChat.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import com.chat.TwilioChat.model.Users;

@Entity
public class Conversations {
	@Id
	@GeneratedValue(generator = "conversations_generator")
	@SequenceGenerator(
			name = "conversations_generator",
			sequenceName = "conversations_sequence",
			initialValue = 1
			)
	private long id;
	
	@OneToMany
	private Users users;
	
	private String identity;
	
	private String conversationId;
	
	private String participantId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getConversationId() {
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

	public String getParticipantId() {
		return participantId;
	}

	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}

	public Conversations(long id, Users users, String identity, String conversationId, String participantId) {
		super();
		this.id = id;
		this.users = users;
		this.identity = identity;
		this.conversationId = conversationId;
		this.participantId = participantId;
	}

	public Conversations() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
}
