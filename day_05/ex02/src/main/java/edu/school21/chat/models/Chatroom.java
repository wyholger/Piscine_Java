package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

import static edu.school21.chat.util.Color.*;

public class Chatroom
{
	private final Long id;
	private final String name;
	private final User owner;
	private final List<Message> messages;

	public Chatroom(Long id, String name, User owner)
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

	public User getOwner()
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
		return RESET + "{" +
				"id=" + YELLOW + id + RESET +
				", name=\"" + YELLOW + name + RESET + '\"' +
				", creator=\"" + YELLOW + owner + RESET + '\"' +
				", messages=" + YELLOW + "null" + RESET +
				'}';
	}
}
