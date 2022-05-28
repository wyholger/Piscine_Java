package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User
{
	private final Long id;
	private final String login;
	private final String password;
	private final List<Chatroom> created_rooms;
	private final List<Chatroom> socializes_rooms;

	public User(Long id, String login, String password)
	{
		this.id = id;
		this.login = login;
		this.password = password;
		this.created_rooms = null;
		this.socializes_rooms = null;
	}

	public User(Long id, String login, String password, List<Chatroom> created_rooms, List<Chatroom> socializes_rooms)
	{
		this.id = id;
		this.login = login;
		this.password = password;
		this.created_rooms = created_rooms;
		this.socializes_rooms = socializes_rooms;
	}

	public Long getId()
	{
		return id;
	}

	public String getLogin()
	{
		return login;
	}

	public String getPassword()
	{
		return password;
	}

	public List<Chatroom> getCreated_rooms()
	{
		return created_rooms;
	}

	public List<Chatroom> getSocializes_rooms()
	{
		return socializes_rooms;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(id, user.id)
				&& Objects.equals(login, user.login)
				&& Objects.equals(password, user.password)
				&& Objects.equals(created_rooms, user.created_rooms)
				&& Objects.equals(socializes_rooms, user.socializes_rooms);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, login, password, created_rooms, socializes_rooms);
	}

	@Override
	public String toString()
	{
		return "{" +
				"id=" + id +
				", login=\"" + login + '\"' +
				", password=\"" + password + '\"' +
				", created_rooms=" + created_rooms +
				", rooms=" + socializes_rooms +
				'}';
	}
}
