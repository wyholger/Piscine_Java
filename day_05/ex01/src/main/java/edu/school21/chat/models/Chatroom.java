package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom
{
	private final Long id;
	private final String name;
	private final String owner;
	private final List<Message> messages;

	public Chatroom(Long id, String name, String owner)
	{
		this.id = id;
		this.name = name;
		this.owner = owner;
		this.messages = null;
	}

	public Long getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String getOwner()
	{
		return owner;
	}

	public List<Message> getMessages()
	{
		return messages;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Chatroom chatroom = (Chatroom) o;
		return Objects.equals(id, chatroom.id)
				&& Objects.equals(name, chatroom.name)
				&& Objects.equals(owner, chatroom.owner)
				&& Objects.equals(messages, chatroom.messages);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, name, owner, messages);
	}

	@Override
	public String toString()
	{
		return "{" +
				"id=" + id +
				", name=\"" + name + '\"' +
				", creator=\"" + owner + '\"' +
				", messages=null" +
				'}';
	}
}
