package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message
{
	private Long id;
	private User author;
	private Chatroom room;
	private String text;
	private LocalDateTime data_time;

	public Message(Long id, User author, Chatroom room, String text, LocalDateTime data_time)
	{
		this.id = id;
		this.author = author;
		this.room = room;
		this.text = text;
		this.data_time = data_time;
	}

	public Long getId()
	{
		return id;
	}

	public User getAuthor()
	{
		return author;
	}

	public Chatroom getRoom()
	{
		return room;
	}

	public String getText()
	{
		return text;
	}

	public LocalDateTime getData_time()
	{
		return data_time;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Message message = (Message) o;
		return Objects.equals(id, message.id)
				&& Objects.equals(author, message.author)
				&& Objects.equals(room, message.room)
				&& Objects.equals(text, message.text)
				&& Objects.equals(data_time, message.data_time);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, author, room, text, data_time);
	}

	@Override
	public String toString()
	{
		return "Message{" +
				"id=" + id +
				", author=" + author +
				", room=" + room +
				", text='" + text + '\'' +
				", data_time=" + data_time +
				'}';
	}
}
